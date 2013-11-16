package qskipm.utils;

import java.util.ArrayList;
import java.util.List;

import qskipm.model.Order;
import qskipm.model.Owner;
import qskipm.model.Product;
import qskipm.model.User;
public class DbUtils {
	public static void saveUser(User user) { 
		OfyService.ofy().save().entity(user);
	}
	public static void saveOrder(Order order) { 
		OfyService.ofy().save().entity(order);
	}
	public static void saveOwner(Owner owner) { 
		OfyService.ofy().save().entity(owner);
	}
	public static List<Owner> getAllStores() { 
		return OfyService.ofy().load().type(Owner.class).list();
	}
	public static void saveProduct(Product product) { 
		OfyService.ofy().save().entity(product);
	}
	public static Owner getOwner(String ownerId) { 
		 return OfyService.ofy().load().type(Owner.class).id(ownerId).get();
	}
	public static List<Order> getUsersOrders(String userId,boolean onlyActive) { 
		User user = getUser(userId);
		return getUsersOrders(user,onlyActive);	
	}
	public static List<Order> getUsersOrders(User user, boolean onlyActive) {
		List<Order> orders = new ArrayList<Order>();  
		for (String orderId : user.orders) { 
			Order order = getOrderById(orderId);
			if (!onlyActive || order.isActive == onlyActive) { 
				orders.add(order);
			}
		}
		return orders;
	}
	public static List<Order> getActiveOrders(String ownerId,boolean onlyActive) { 
		Owner owner = getOwner(ownerId); 
		return getOrders(owner,onlyActive);
	}
	
	public static List<Order> getOrders(Owner owner, boolean onlyActive) { 
		List<Order> orders = new ArrayList<Order>(); 
		for (String orderId : owner.orders) {
			Order order =  getOrderById(orderId);
			if (!onlyActive || order.isActive == onlyActive) { 
				orders.add(order);
			}
		}
		return orders;
	}
	public static Order getOrderById(String orderId) { 
		 return OfyService.ofy().load().type(Order.class).id(orderId).get();
	}
	
	public static User getUser(String userId) { 
		 return OfyService.ofy().load().type(User.class).id(userId).get();
	}
	public static List<Product> getProducts(String ownerId, boolean onlyActive) {
		Owner owner = getOwner(ownerId);
		return getProducts(owner,onlyActive);
	}
	public static List<Product> getProducts(Owner owner, boolean onlyActive) { 
		List<Product> products = new ArrayList<Product>();  
		for (String productId : owner.products) { 
			Product product = getProduct(productId);
			if (!onlyActive || product.isActive) { 
				products.add(product);
			}
		}
		return products;
	}
	public static Product getProduct(String productId) { 
		return OfyService.ofy().load().type(Product.class).id(productId).get();
	}
}
