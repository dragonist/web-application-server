package Dao;

import java.util.HashMap;
import java.util.Map;

import model.User;

public class MapDao{
	private static Map<String, User> users =new HashMap<String, User>();
	
	public static void insert(User user){
		users.put(user.getUserId(), user);
	}
	public static User select(String id){
		return users.get(id);
	}
}
