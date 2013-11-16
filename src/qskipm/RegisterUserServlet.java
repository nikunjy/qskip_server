package qskipm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qskipm.model.User;
import qskipm.utils.DbUtils;
import qskipm.utils.OfyService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RegisterUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			String arg = req.getParameter("arg");
			if (arg!=null) { 
				if (arg.equalsIgnoreCase("all")) {
					List<User> users = new ArrayList<User>();
					users = OfyService.ofy().load().type(User.class).list();
					Gson g = new Gson(); 
					resp.getWriter().println(g.toJson(users));
					return;
				}
			}
			String name = req.getParameter("name");
			User user = new User();
			user.userName = name;
			DbUtils.saveUser(user);
			Gson g = new Gson(); 
			resp.getWriter().println(g.toJson(user));
		} catch(Exception e) { 
			JsonObject json = new JsonObject(); 
			json.addProperty("result", "error");
			resp.getWriter().println(json);

		}
	}

}
