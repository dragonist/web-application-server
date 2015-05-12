package model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.HttpResponseUtils;

public class Response {
	private static final Logger logger = LoggerFactory.getLogger(Response.class);
	String view;
	byte[] body;
	Map<String, String> cookies = new HashMap<String, String>();
	
	public void setCookie(String key, String value) {
		cookies.put(key, value);
	}

	public void setView(String view) {
		this.view = view;
	}

	public void send(DataOutputStream dos) throws IOException {
		if (view.contains("redirect:")) {
			view = view.replace("redirect:", "");
			response302Header(dos, view);
		}else{
			byte[] body = Files.readAllBytes(new File("./webapp" + view).toPath());
			response200Header(dos, body.length);
			responseBody(dos, body);
		}
		dos.flush();
	}
	
	private void response302Header(DataOutputStream dos, String view) {
		try{
			dos.writeBytes("HTTP/1.1 302 Found \r\n");
			dos.writeBytes("Location: http://localhost:8080"+view+" \r\n");
			if (!cookies.isEmpty()) {
				dos.writeBytes("Set-Cookie: "+HttpResponseUtils.parseToQueryString(cookies));
			}
			dos.writeBytes("\r\n");
		}catch(IOException e){
			logger.error(e.getMessage());
		}
	}

	private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
		try {
			dos.writeBytes("HTTP/1.1 200 Document Follows \r\n");
			dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
			if (!cookies.isEmpty()) {
				dos.writeBytes("Set-Cookie: "+HttpResponseUtils.parseToQueryString(cookies));
			}
			dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	private void responseBody(DataOutputStream dos, byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies.putAll(cookies);
	}

}
