package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import model.Request;
import model.Response;
import mvc.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.RequestMapping;

public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	private Socket connection;
	private RequestMapping rm;
	
	public RequestHandler(Socket connectionSocket, RequestMapping rm) {
		this.connection = connectionSocket;
		this.rm = rm;
	}

	public void run() {
		log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());

		try (InputStream in = connection.getInputStream(); 
				OutputStream out = connection.getOutputStream(); 
				BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				DataOutputStream dos = new DataOutputStream(out)) {
			Request request = new Request(br);
			Controller c = rm.getController(request.getUrl());
			Response response = c.render(request, new Response());
			response.setCookies(request.getCookies());
			response.send(dos);
//			log.debug("url : {}",request.getUrl());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	


}
