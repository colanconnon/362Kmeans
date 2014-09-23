import java.util.ArrayList;


public class pixel {
	public ArrayList<Integer> getAvgints() {
		return Avgints;
	}

	public void setAvgints(ArrayList<Integer> Avgints) {
		this.Avgints = Avgints;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getClustervalue() {
		return clustervalue;
	}

	public void setClustervalue(int clustervalue) {
		this.clustervalue = clustervalue;
	}

	public int getRGB() {
		return RGB;
	}

	public void setRGB(int RGB) {
		this.RGB = RGB;
	}

	private int RGB;
	private int height;
	private int width;
	private int clustervalue;
	private int distance;
	private ArrayList<Integer> Avgints =  new ArrayList<Integer>();
	
}
