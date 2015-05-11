package controller;

import model.Request;
import model.Response;
import mvc.Controller;

public class loginController extends Controller {

	public Response render(Request request, Response response) {
		response.setView(request.getUrl());
		return response;
	}

}
