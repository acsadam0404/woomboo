package hu.woomboo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class TrackInfo implements OnSeekBarChangeListener {
	private Track track;
	private ViewGroup layout;
	private Activity context;
	private TextView author;
	private TextView title;
	private TextView full;
	private TextView year;
	private SeekBar seekBar;
	private TextView currentTime;
	private TextView fullTime;

	private Handler mHandler = new Handler();
	private MediaPlayer mp;


	public TrackInfo(Activity context, MediaPlayer mp) {
		this.context = context;
		this.mp = mp;

		author = (TextView) context.findViewById(R.id.author);
		title = (TextView) context.findViewById(R.id.title);
		full = (TextView) context.findViewById(R.id.full);
		year = (TextView) context.findViewById(R.id.year);
		currentTime = (TextView) context.findViewById(R.id.currentTime);
		fullTime = (TextView) context.findViewById(R.id.fullTime);
		seekBar = (SeekBar) context.findViewById(R.id.seekBar);
		seekBar.setOnSeekBarChangeListener(this);

	}


	public void refresh(Track track) {
		this.track = track;
		author.setText(track.getAuthor());
		title.setText(track.getTitle());
		year.setText(track.getYear());
		/* TODO mi az a hosszú? */
		full.setText("TEST");
		currentTime.setText("10:22");
		fullTime.setText("10:43");
	}

	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {
			long totalDuration = mp.getDuration();
			long currentDuration = mp.getCurrentPosition();

			// Displaying Total Duration time
			fullTime.setText(Utilities.milliSecondsToTimer(totalDuration));
			// Displaying time completed playing
			currentTime.setText(Utilities.milliSecondsToTimer(currentDuration));

			// Updating progress bar
			int progress = (int) (Utilities.getProgressPercentage(currentDuration, totalDuration));
			//Log.d("Progress", ""+progress);
			seekBar.setProgress(progress);

			// Running this thread after 100 milliseconds
			mHandler.postDelayed(this, 100);
		}
	};


	public void onPlay() {
		seekBar.setProgress(0);
		seekBar.setMax(100);
		updateProgressBar();
	}


	public void updateProgressBar() {
		mHandler.postDelayed(mUpdateTimeTask, 100);
	}


	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

	}


	/**
	 * When user starts moving the progress handler
	 * */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// remove message Handler from updating progress bar
		mHandler.removeCallbacks(mUpdateTimeTask);
	}


	/**
	 * When user stops moving the progress handler
	 * */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = mp.getDuration();
		int currentPosition = Utilities.progressToTimer(seekBar.getProgress(), totalDuration);

		// forward or backward to certain seconds
		mp.seekTo(currentPosition);

		// update timer progress again
		updateProgressBar();
	}
}
