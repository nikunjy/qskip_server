package qskipm.model;

import java.util.UUID;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Product {
	@Index
	public String ownerId;
	@Id
	public String productId;
	public String name; 
	public String description; 
	public String price;
	public boolean isActive;
	public Product(String ownerId) { 
		UUID id = UUID.randomUUID(); 
		this.productId = id.toString();  
		this.isActive = true; 
		this.ownerId = ownerId;
	}
	public Product() { 
		
	}
}
