package qskipm;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qskipm.model.Owner;
import qskipm.utils.DbUtils;

import com.google.gson.JsonObject;

public class RegisterOwnerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
		String ownerName = req.getParameter("name");
		String lat = req.getParameter("lat");
		String lon = req.getParameter("long");
		String payId = req.getParameter("payId");
		double latitude = 0.0; 
		double longitude = 0.0; 
		if (lat != null) { 
			latitude = Double.parseDouble(lat);
		}
		if (lon != null) { 
			longitude = Double.parseDouble(lon);
		}
		if (ownerName == null) { 
			throw new Exception("");
		}
		Owner owner = new Owner(ownerName,latitude, longitude);
		owner.payId = payId;
		DbUtils.saveOwner(owner);
		JsonObject json = new JsonObject(); 
		json.addProperty("result", "success");
		resp.getWriter().println(json);
		} catch(Exception e) { 
			
			JsonObject json = new JsonObject();
			json.addProperty("result", "error");
			resp.getWriter().println(json);
		}
		
	}
}
