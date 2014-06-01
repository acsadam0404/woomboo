package hu.woomboo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import android.content.Context;
import android.widget.SimpleAdapter;


public class ListAdapter extends SimpleAdapter {
	private final static List<HashMap<String, String>> trackItems = new ArrayList<HashMap<String, String>>();
	private final static Map<Integer, Track> tracks = new HashMap<Integer, Track>();
	private static final String NO = "no";
	private static final String NAME = "name";
	private static final String LENGTH = "length";


	public ListAdapter(Context context) {
		super(context, createList(), R.layout.track, new String[] { NO, NAME, LENGTH }, new int[] { R.id.trackNo, R.id.trackName, R.id.trackLength });
	}


	private static List<? extends Map<String, ?>> createList() {
		Scanner sc = null;
		try {
			sc = new Scanner(ListAdapter.class.getResourceAsStream("tracks.txt"));
			while (sc.hasNext()) {
				String line = sc.nextLine();
				String[] split = line.split(";");
				Track track = new Track();
				track.setNo(split[0]);
				track.setName(split[1]);
				track.setLength(split[2]);
				track.setAudio(split[3]);
				tracks.put(Integer.parseInt(track.getNo()), track);
			}
		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}

		for (Track t : tracks.values()) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put(NO, t.getNo());
			temp.put(NAME, t.getName());
			temp.put(LENGTH, t.getLength());
			trackItems.add(temp);
		}

		return trackItems;
	}
	
	public static Track getTrack(int no) {
		return tracks.get(no);
	}

}
