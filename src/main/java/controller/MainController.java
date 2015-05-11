package controller;

import model.Request;
import model.Response;
import mvc.Controller;

public class MainController extends Controller{

	public Response render(Request request, Response response) {
		response.setView(request.getUrl());
		return response;
	}
	
}
