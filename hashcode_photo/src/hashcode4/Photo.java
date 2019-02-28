package hashcode4;

import java.util.ArrayList;

public class Photo {
	
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
	
	
	

}
