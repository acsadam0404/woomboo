package hu.woomboo;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

public class TrackInfo {
	private Track track;
	private ViewGroup layout;
	private Context context;
	private TextView author;
	private TextView title;
	private TextView full;
	private TextView year;

	public TrackInfo(Context context, ViewGroup layout) {
		this.context = context;
		this.layout = layout;
		
	}
	
	public void build() {
		author = new TextView(context);
		layout.addView(author);
		title = new TextView(context);
		layout.addView(title);
		full = new TextView(context);
		layout.addView(full);
		year = new TextView(context);
		layout.addView(year);
	}
	
	public void refresh(Track track) {
		this.track = track;
		author.setText(track.getAuthor());
		title.setText(track.getTitle());
		year.setText(track.getYear());
		/* TODO mi az a hosszú? */
		full.setText("TEST");
	}
}
