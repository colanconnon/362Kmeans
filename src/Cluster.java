import java.util.ArrayList;


public class Cluster {
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public ArrayList<Integer> getDistancelist() {
		return distancelist;
	}
	public void setDistancelist(ArrayList<Integer> distancelist) {
		this.distancelist = distancelist;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public ArrayList<pixel> getArray() {
		return array;
	}
	public void setArray(ArrayList<pixel> array) {
		this.array = array;
	}
	private int value;
	protected ArrayList<pixel> array = new ArrayList<pixel>();
	private int distance;
	protected ArrayList<Integer> distancelist = new ArrayList<Integer>();
	private int average;
	
}
