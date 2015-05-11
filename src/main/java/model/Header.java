package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import util.HttpRequestUtils;

public class Header {

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

	Map<String, String> headerInfo = new HashMap<String, String>();

	public Header() {
	}

	public Header(BufferedReader br) throws IOException {
		String line = br.readLine();
		String[] tokens = line.split(" ");
		method = tokens[0];
		url = tokens[1];
		protocol = tokens[2];

		while (!"".equals(line = br.readLine())) {
			HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(line);
			headerInfo.put(pair.getKey(), pair.getValue());
		}
	}
	

}
