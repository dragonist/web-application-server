package mvc;

import model.Request;
import model.Response;

public abstract class Controller {

	public abstract Response render(Request request, Response response);

}
