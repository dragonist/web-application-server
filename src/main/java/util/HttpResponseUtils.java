package util;

import java.util.Map;

public class HttpResponseUtils {
	public static String parseToQueryString(Map<String, String> map) {
		String result = "";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			result += entry.getKey();
			result += "=";
			result += entry.getValue();
			result += "&";
		}
		result = result.replaceAll(".$", "");
		return result;
	}
}
