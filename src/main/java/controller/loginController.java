package controller;

import model.Header;
import mvc.Controller;

public class loginController extends Controller {

	public String render(Header header) {
		return "/index.html";
	}

}
