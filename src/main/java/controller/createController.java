package controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Dao.MapDao;
import model.Request;
import model.Response;
import model.User;
import mvc.Controller;

public class createController extends Controller {
		private static final Logger logger = LoggerFactory.getLogger(createController.class);
	
	public Response render(Request request, Response response) {
		Map<String, String> m = request.getParameters();
		User user = new User(m.get("userId"), m.get("password"), m.get("name"), m.get("email"));
		MapDao.insert(user);
		logger.debug("user : {}", user);
		response.setView("redirect:/index.html");
		return response;
	}
}
