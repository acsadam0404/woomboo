package hu.woomboo;

public class Track {
	private String no;
	private String author;
	private String title;
	private String year;
	private String length;
	private String audio;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((audio == null) ? 0 : audio.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		if (audio == null) {
			if (other.audio != null)
				return false;
		}
		else if (!audio.equals(other.audio))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		}
		else if (!author.equals(other.author))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		}
		else if (!length.equals(other.length))
			return false;
		if (no == null) {
			if (other.no != null)
				return false;
		}
		else if (!no.equals(other.no))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		}
		else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		}
		else if (!year.equals(other.year))
			return false;
		return true;
	}
	
	

}
