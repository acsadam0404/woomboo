package hu.woomboo;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
	private static final String TAG = "MainActivity";
	private MediaPlayer mp = new MediaPlayer();
	private TrackInfo trackInfo;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		trackInfo = new TrackInfo(this, mp);
		createTracksView();
		setupPlayButtons();
		setupMedia(ListAdapter.getTrack(1));
	}


	private void setupPlayButtons() {
		final ImageButton play = (ImageButton) findViewById(R.id.btnPlay);
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (mp.isPlaying()) {
					mp.pause();
				}
				else {
					mp.start();
				}
				refreshPlayButton(mp.isPlaying());
			}
		});

		final ImageButton prev = (ImageButton) findViewById(R.id.btnPrev);
		prev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mp.isPlaying()) {
					int seekTo = mp.getCurrentPosition() - 5000;
					if (seekTo > 0) {
						mp.seekTo(seekTo);
					}

				}
			}
		});

		final ImageButton next = (ImageButton) findViewById(R.id.btnNext);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mp.isPlaying()) {
					int seekTo = mp.getCurrentPosition() + 5000;
					if (seekTo < mp.getDuration()) {
						mp.seekTo(seekTo);
					}
				}
			}
		});
	}


	private void refreshPlayButton(boolean playing) {
		final ImageButton play = (ImageButton) findViewById(R.id.btnPlay);
		if (playing) {
			play.setImageDrawable(getResources().getDrawable(R.drawable.pause_button));
		}
		else {
			play.setImageDrawable(getResources().getDrawable(R.drawable.play_button));
		}
	}


	private void createTracksView() {
		ListView lv = (ListView) findViewById(R.id.tracks);
		lv.setAdapter(new ListAdapter(this));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				setupMedia(ListAdapter.getTrack(position + 1));
				Intent intent = new Intent(MainActivity.this, ImageActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				mp.start();
				trackInfo.onPlay();
				refreshPlayButton(true);
			}

		});
	}


	private void setupMedia(Track track) {
		int audioResourceId = getResources().getIdentifier(track.getAudio(), "raw", getPackageName());
		AssetFileDescriptor afd = getResources().openRawResourceFd(audioResourceId);
		try {
			mp.reset();
			mp.setDataSource(afd.getFileDescriptor());
			mp.prepare();
			trackInfo.refresh(track);
		}
		catch (Exception e) {
			Log.e(TAG, e.toString());
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_about:
				aboutAction();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	private void aboutAction() {
		Intent intent = new Intent(this, AboutActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);
	}

}
