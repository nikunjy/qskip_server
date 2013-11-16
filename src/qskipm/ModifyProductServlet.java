package qskipm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qskipm.model.Owner;
import qskipm.model.Product;
import qskipm.utils.DbUtils;

import com.google.gson.Gson;

public class ModifyProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Product ret = null;
		String arg = req.getParameter("arg");
		String ownerId = req.getParameter("ownerId");
		if (arg.equalsIgnoreCase("add")) {
			String name = req.getParameter("name");
			Product product = new Product(ownerId);  
			Owner owner = DbUtils.getOwner(ownerId);
			if (owner.products == null) { 
				owner.products = new ArrayList<String>();
			}
			owner.products.add(product.productId);
			DbUtils.saveOwner(owner);
			DbUtils.saveProduct(product);
			ret = product;
		} else if (arg.equalsIgnoreCase("list")) {
			Owner owner = DbUtils.getOwner(ownerId);
			System.out.println(owner+" "+owner.products);
			List<Product> products = DbUtils.getProducts(owner, false);
			resp.getWriter().println(new Gson().toJson(products));
			return;
		}else {
			String productId = req.getParameter("productId");
			List<Product> products = DbUtils.getProducts(ownerId, false);
			for (Product product : products) {
				if (product.productId.equals(productId)) { 
					product.isActive = false;
					ret = product;
					break;
				}
			}
			DbUtils.saveProduct(ret);
		}
		resp.getWriter().println(new Gson().toJson(ret));
	}

}
