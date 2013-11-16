package qskipm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class User {
	@Id
	public String userId;
	@Index
	public String userName;
	public String phoneNumber;  
	@Index 
	public String payId;
	public Date creationDate;
	@Load  public List<String> orders;
	public User() { 
		UUID id = UUID.randomUUID();
		this.userId = id.toString();
		this.orders = new ArrayList<String>();
		this.creationDate = new Date();
	}
}
