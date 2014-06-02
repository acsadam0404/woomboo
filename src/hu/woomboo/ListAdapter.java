package hu.woomboo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleAdapter;

/**
 * nem származhat SimpleAdapter-bõl mert konstruktor idõben nem tudjuk még a listát
 *
 */
public class ListAdapter {
	private static final String NO = "no";
	private static final String NAME = "name";
	private static final String LENGTH = "length";
	private final List<HashMap<String, String>> trackItems = new ArrayList<HashMap<String, String>>();
	private static Map<Integer, Track> tracks = new HashMap<Integer, Track>();
	private SimpleAdapter adapter;


	public ListAdapter(Context context) {
		adapter = new SimpleAdapter(context, createList(), R.layout.track, new String[] { NO, NAME, LENGTH }, new int[] { R.id.trackNo, R.id.trackName, R.id.trackLength });
	}


	private List<? extends Map<String, ?>> createList() {
		tracks = new TrackReader("tracks.txt").read();

		for (Track t : tracks.values()) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put(NO, t.getNo());
			temp.put(NAME, t.getTitle());
			temp.put(LENGTH, t.getLength());
			trackItems.add(temp);
		}

		return trackItems;
	}


	public Track getTrack(int no) {
		return tracks.get(no);
	}


	public android.widget.ListAdapter getAdapter() {
		return adapter;
	}

}
