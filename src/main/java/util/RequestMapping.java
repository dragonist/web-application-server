package util;

import java.util.HashMap;
import java.util.Map;

import mvc.Controller;
import controller.MainController;
import controller.createController;
import controller.loginController;

public class RequestMapping {
	Map<String, Controller> controllers  = new HashMap<String, Controller>();
	
	public RequestMapping() {
		controllers.put("/index.html",new MainController());
		controllers.put("/login",new loginController());
		controllers.put("/create",new createController());
	}
	
	public Controller getController(String key){
		Controller c = controllers.get(key);
		if(c == null) c = controllers.get("/index.html");
		return c;
	}
	
}
