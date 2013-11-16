package qskipm.model;

import java.util.Date;
import java.util.UUID;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Order {
	@Id 
	public String orderId;
	@Index
	public String userId;
	public double longitude; 
	public double latitude;
	public boolean isActive;
	public long seq;
	public String productId;
	public Date creationDate;
	public String ownerId;
	public Order(String userId,String productId) { 
		UUID id = UUID.randomUUID(); 
		orderId = id.toString();
		creationDate = new Date(); 
		this.userId = userId;
		this.isActive = true;
	}
	public Order() { 
		
	}
}
