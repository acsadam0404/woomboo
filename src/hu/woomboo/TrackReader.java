package hu.woomboo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TrackReader {
	private String fileName;

	public TrackReader(String fileName) {
		this.fileName = fileName;

	}

	public Map<Integer, Track> read() {
		Map<Integer, Track> tracks = new HashMap<Integer, Track>();
		Scanner sc = null;
		try {
			sc = new Scanner(ListAdapter.class.getResourceAsStream(fileName));
			while (sc.hasNext()) {
				String line = sc.nextLine();
				String[] split = line.split(";");
				Track track = new Track();
				track.setNo(split[0]);
				track.setAuthor(split[1]);
				track.setTitle(split[2]);
				track.setYear(split[3]);
				track.setLength(split[4]);
				track.setAudio(split[5]);
				tracks.put(Integer.parseInt(track.getNo()), track);
			}
		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}		
		return tracks;
	}

}
