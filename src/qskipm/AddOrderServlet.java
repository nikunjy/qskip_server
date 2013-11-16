package qskipm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qskipm.model.Order;
import qskipm.model.Owner;
import qskipm.model.User;
import qskipm.utils.DbUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AddOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			String userId = req.getParameter("userId");
			String ownerId = req.getParameter("ownerId");
			String productId = req.getParameter("productId");
			String lat = req.getParameter("lat");
			String lon = req.getParameter("long");
			if (userId == null || ownerId == null || productId == null) { 
				throw new Exception("");
			}
			System.out.println(userId+" "+productId+" "+ownerId+" "+lat+" "+lon);
			double latitude = Double.parseDouble(lat); 
			double longitude = Double.parseDouble(lon);
			Order order = new Order(userId,productId);
			order.latitude = latitude; 
			order.longitude = longitude;
			User user = DbUtils.getUser(userId); 
			Owner owner = DbUtils.getOwner(ownerId);
			owner.activeOrders+=1;
			if (user.orders == null) { 
				user.orders = new ArrayList<String>();
			}
			user.orders.add(order.orderId);
			if (owner.orders == null) { 
				owner.orders = new ArrayList<String>();
			}
			owner.orders.add(order.orderId);
			
			DbUtils.saveOwner(owner); 
			DbUtils.saveUser(user);
			order.seq = owner.activeOrders;
			order.productId = productId;
			DbUtils.saveOrder(order);
			Gson g = new Gson(); 
			resp.getWriter().println(g.toJson(order));
		} catch(Exception e) { 
			e.printStackTrace();
			JsonObject json = new JsonObject(); 
			json.addProperty("result", "error");
			resp.getWriter().println(json);
		}
	}
}
