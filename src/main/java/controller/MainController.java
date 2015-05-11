package controller;

import model.Header;
import mvc.Controller;

public class MainController extends Controller{

	public String render(Header header) {
		return header.getUrl();
	}


	
}
