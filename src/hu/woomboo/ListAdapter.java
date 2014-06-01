package hu.woomboo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import android.content.Context;
import android.widget.SimpleAdapter;


public class ListAdapter extends SimpleAdapter {
	private static final String NO = "no";
	private static final String NAME = "name";
	private static final String LENGTH = "length";
	private final static List<HashMap<String, String>> trackItems = new ArrayList<HashMap<String, String>>();
	private static Map<Integer, Track> tracks = new HashMap<Integer, Track>();


	public ListAdapter(Context context) {
		super(context, createList(), R.layout.track, new String[] { NO, NAME, LENGTH }, new int[] { R.id.trackNo, R.id.trackName, R.id.trackLength });
	}


	private static List<? extends Map<String, ?>> createList() {
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
	
	public static Track getTrack(int no) {
		return tracks.get(no);
	}

}
