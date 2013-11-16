package qskipm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qskipm.model.Owner;
import qskipm.model.Stores;
import qskipm.utils.DbUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GetStoresServlet extends HttpServlet {

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
	private double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			String arg = req.getParameter("arg"); 
			if (arg!=null) { 
				if (arg.equalsIgnoreCase("all")) {
					List<Owner> stores = DbUtils.getAllStores();
					List<Stores> ret = new ArrayList<Stores>();
					for (Owner store : stores) { 
						double slon = store.longitude;
						double slat = store.latitude;
						Stores s = new Stores();  
						s.address = store.address;
						s.storeId = store.ownerId;
						s.latitude = store.latitude; 
						s.longitude = store.longitude;
						s.name  = store.ownerName;
						s.seq = store.activeOrders;
						ret.add(s); 
					}
					Gson g = new Gson(); 
					resp.getWriter().println(ret);
					return;
				}
			}
			double latitude = Double.parseDouble(req.getParameter("lat"));
			double longitude = Double.parseDouble(req.getParameter("long"));
			System.out.println(latitude+" "+longitude);
			List<Owner> stores = DbUtils.getAllStores();
			List<Stores> ret = new ArrayList<Stores>();
			for (Owner store : stores) { 
				double slon = store.longitude;
				double slat = store.latitude;
				double dist = distance(latitude,longitude,slat,slon);
				if (dist <= 2.0) {
					Stores s = new Stores();  
					s.address = store.address;
					s.distance = dist;
					s.latitude = store.latitude;
					s.storeId = store.ownerId;
					s.longitude = store.longitude;
					s.name  = store.ownerName;
					s.seq = store.activeOrders;
					ret.add(s); 
				}
				Gson g = new Gson(); 
				System.out.println(g.toJson(store) +" "+dist);
			}
			Gson g = new Gson(); 
			resp.getWriter().println(g.toJson(ret));			
		} catch(Exception e) { 
			JsonObject json = new JsonObject(); 
			json.addProperty("result", "error");
			resp.getWriter().println(json);
		}
	}

}
