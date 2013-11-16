package qskipm.model;

public class Stores implements Comparable<Stores>{
	public String storeId;
	public String name; 
	public double latitude; 
	public double longitude; 
	public String address;
	public String imageUrl;
	public double distance;
	public long seq;
	@Override
	public int compareTo(Stores o) {
		if (this.distance == o.distance) { 
			return 0;
		}
		if (this.distance > o.distance){  
			return 1;
		} else 
			return -1;
	}
}
