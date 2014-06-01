package hu.woomboo;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
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
	private MediaPlayer mp = new MediaPlayer();
	private TrackInfo trackInfo;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		createTrackInfo();
		createTracksView();
		setupPlayButtons();
	}


	private void createTrackInfo() {
		trackInfo = new TrackInfo(this, (ViewGroup) findViewById(R.id.trackInfo));
		trackInfo.build();
	}


	private void setupPlayButtons() {
		ImageButton play = (ImageButton) findViewById(R.id.btnPlay);
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				if (mp.isPlaying()) {
					mp.pause();
				}
				else {
					mp.start();
				}
			}
		});
	}


	private void createTracksView() {
		ListView lv = (ListView) findViewById(R.id.tracks);
		lv.setAdapter(new ListAdapter(this));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Track track = ListAdapter.getTrack(position + 1);

				int audioResourceId = getResources().getIdentifier(track.getAudio(), "raw", getPackageName());
				AssetFileDescriptor afd = getResources().openRawResourceFd(audioResourceId);
				try {
					mp.reset();
					mp.setDataSource(afd.getFileDescriptor());
					mp.prepare();
					trackInfo.refresh(track);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
