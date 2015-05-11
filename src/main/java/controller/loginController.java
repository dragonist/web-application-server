package controller;

import model.Request;
import mvc.Controller;

public class loginController extends Controller {

	public String render(Request request) {
		return "/index.html";
	}

}
