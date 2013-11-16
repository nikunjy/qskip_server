package qskipm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Owner {
	@Id 
	public String ownerId;
	public double longitude; 
	public double latitude;
	public String address;
	public String ownerName;
	public Date creationDate;
	public String payId;
	public String imageUrl;
	public int activeOrders;
	@Load public List<String> orders;
	@Load  public List<String> products;
	public Owner(String ownerName,double latitude, double longitude) {
		UUID id = UUID.randomUUID(); 
		this.ownerName = ownerName;
		this.ownerId = id.toString(); 
		this.creationDate = new Date();
		this.longitude = longitude;
		this.latitude = latitude;
		this.orders = new ArrayList<String>();
		this.products = new ArrayList<String>();
		this.activeOrders = 0;
	}
	public Owner() { 
		
	}
}
