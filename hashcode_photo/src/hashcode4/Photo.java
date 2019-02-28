package hashcode4;

import java.util.ArrayList;

public class Photo {
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String orientation;
	
	private int nbTags;
	
	private ArrayList<String> tags = new ArrayList<String>();

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public int getNbTags() {
		return nbTags;
	}

	public void setNbTags(int nbTags) {
		this.nbTags = nbTags;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	public Photo(Orientation orientation, int id, Set<String> tags) {
        this.orientation = orientation;
        this.id = id;
        this.tags = tags;
    }

	@Override
    public String toString() {
        return "Photo{" +
                "orientation=" + orientation +
                ", id='" + id + '\'' +
                ", tags=" + tags +
                '}';
    }

    public boolean isHorizontal() {
        return orientation == Orientation.HORIZONTAL;
    }

    public boolean isVertical() {
        return orientation == Orientation.VERTICAL;
    }
}
