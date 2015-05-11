package controller;

import model.Request;
import mvc.Controller;

public class MainController extends Controller{

	public String render(Request request) {
		return request.getUrl();
	}


	
}
