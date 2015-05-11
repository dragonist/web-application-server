package mvc;

import model.Request;

public abstract class Controller {

	public abstract String render(Request request);

}
