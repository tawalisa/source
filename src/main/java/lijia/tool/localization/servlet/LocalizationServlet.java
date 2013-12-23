package lijia.tool.localization.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class LocalizationServlet  extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charset=UTF-8");
		String resource = req.getParameter("resource");
		ResourceBundle re = ResourceUtils.getResourceInstance(req.getLocale(),resource);
		Enumeration e = re.getKeys();
		JSONObject jsonObject = new JSONObject();
		while(e.hasMoreElements()){
			Object o = e.nextElement();
			try {
				jsonObject.put(o.toString(), re.getString(o.toString()));
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		resp.getWriter().print(jsonObject.toString());
	}

}
