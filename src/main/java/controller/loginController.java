package controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Dao.MapDao;
import model.Request;
import model.Response;
import model.User;
import mvc.Controller;

public class loginController extends Controller {
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);

	public Response render(Request request, Response response) {
		Map<String, String> m = request.getParameters();
		User user = MapDao.select(m.get("userId"));
		logger.debug("user : {}", user);
		if(user.login(m.get("userId"), m.get("password"))){
			response.setCookie("logined", "true");
		}
		response.setView("redirect:/index.html");
		return response;
	}

}
