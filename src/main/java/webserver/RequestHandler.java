package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;

import model.Request;
import model.Response;
import mvc.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.util.logging.resources.logging;
import util.HttpRequestUtils;
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
				BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
			Request request = new Request(br);
			Controller c = rm.getController(request.getUrl());
			// TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
			DataOutputStream dos = new DataOutputStream(out);
			log.debug("url : {}",request.getUrl());
			Response response = c.render(request, new Response());
			response.send(dos);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	


}
