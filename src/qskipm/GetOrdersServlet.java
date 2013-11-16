package qskipm;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qskipm.model.Order;
import qskipm.utils.DbUtils;
import qskipm.utils.OfyService;

import com.google.gson.Gson;

public class GetOrdersServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String ownerId = req.getParameter("ownerId");
		String arg = req.getParameter("arg"); 
		if (arg!=null) { 
			if (arg.equalsIgnoreCase("all")) { 
				List<Order> orders = OfyService.ofy().load().type(Order.class).list();
				Gson g = new Gson(); 
				resp.getWriter().println(g.toJson(orders));
				return;
			}
		}
		String onlyActive = req.getParameter("active");
		boolean active = true;
		if (onlyActive != null && onlyActive.equalsIgnoreCase("false")) { 
			active = false;
		}
		List<Order> orders = DbUtils.getActiveOrders(ownerId, true);
		Gson g = new Gson(); 
		String json = g.toJson(orders);
		resp.getWriter().println(json);
	}

}
