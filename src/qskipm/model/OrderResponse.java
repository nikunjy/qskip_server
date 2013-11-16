package qskipm.model;

import java.util.Date;

import qskipm.utils.DbUtils;

public class OrderResponse implements Comparable<OrderResponse> {
	public String customerName; 
	public String orderId; 
	public long seq;
	public double latitude; 
	public double longitude;
	public String productName;
	public Date creationDate;
	public OrderResponse(Order o) {
		this.creationDate = o.creationDate; 
		this.orderId = o.orderId;
		this.seq = o.seq;
		Product product = DbUtils.getProduct(o.productId);
		System.out.println(o.productId+" "+product);
		this.productName = product.name;
		User user = DbUtils.getUser(o.userId);
		this.customerName = user.userName;		
		this.latitude = o.latitude;
		this.longitude = o.longitude;
	}
	@Override
	public int compareTo(OrderResponse o) {
		return this.creationDate.compareTo(o.creationDate);
	}
	
}
