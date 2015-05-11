package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.HttpRequestUtils;

public class Header {
	private static final Logger logger = LoggerFactory.getLogger(Header.class);

	// GET /index HTTP/1.1
	// Host: localhost:8080
	// Connection: keep-alive
	// Cache-Control: max-age=0
	// Accept:
	// text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
	// User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5)
	// AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36
	// Accept-Encoding: gzip, deflate, sdch
	// Accept-Language: ko,en-US;q=0.8,en;q=0.6
	// Cookie: JSESSIONID=FCFF77E04C8C31F33A1680BB3C61EBD5

	String method;
	String url;
	String protocol;

	Map<String, String> parameters;
	Map<String, String> headerInfo;

	public Header() {
	}

	public Header(BufferedReader br) throws IOException {
		headerInfo = new HashMap<String, String>();
		String line = br.readLine();
		if (line == null) return;
		String[] tokens = line.split(" ");
		method = tokens[0];
		url = tokens[1];
		protocol = tokens[2];
	
		if (url.contains("?")) {
			tokens = url.split("\\?");
			url = tokens[0];
			parameters = HttpRequestUtils.parseQueryString(tokens[1]);			
		}
		
		while (!"".equals(line = br.readLine())) {
			HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(line);
			headerInfo.put(pair.getKey(), pair.getValue());
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
