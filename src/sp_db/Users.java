package sp_db;

import java.util.*;
import sp_entities.UserStatus;

public class Users {
	private Map<Integer, User> users;
	
	public Users() {
		users = new HashMap<>();
	}
	
	public void addUser(int id, User user) {
		users.put(id, user);
	}
	
	public User login(int id, String pass) {
		User user = users.get(id);
		if(user == null || !user.isRightPass(pass)) return null;
		return user;
	}
	
	public String getUserName(int userId) {
		User user = users.get(userId);
		if(user == null) return "";
		return user.getName();
	}
	
	//TODO delete
	public void tempInit() {
		int c = 0;
		addUser(++c, new User("aaa", "Пригожев А.С.", UserStatus.TEACHER));
		addUser(++c, new User("bbb", "Кунгурцев А.Б.", UserStatus.TEACHER));
		addUser(++c, new User("ссс", "Кавицкая В.С.", UserStatus.CURATOR));
		addUser(++c, new User("ddd", "Паулин О.Н.", UserStatus.CURATOR));
		addUser(++c, new User("eee", "Иванов И.И.", UserStatus.DEPWORKER));
		
		addUser(++c, new User("fff", "Михайлов С.В.", UserStatus.DEPWORKER));
		addUser(++c, new User("ggg", "Чебан К.В.", UserStatus.STUDENT));
		addUser(++c, new User("hhh", "Берлизов Е.В.", UserStatus.STUDENT));
		addUser(++c, new User("iii", "Беловзоров А.А.", UserStatus.STUDENT));
		addUser(++c, new User("jjj", "Гапяк В.М.", UserStatus.STUDENT));
		
		addUser(++c, new User("kkk", "Малярозов П.А.", UserStatus.STUDENT));
		addUser(++c, new User("lll", "Садовый Д.Д.", UserStatus.STUDENT));
		
		addUser(++c, new User("mmm", "Иванов 131", UserStatus.STUDENT));
		addUser(++c, new User("nnn", "Петров 132", UserStatus.STUDENT));
	}
	
	//TODO delete
	public void print() {
		System.out.println("===========\nUsers: ");
		for (Map.Entry<Integer, User> user : users.entrySet())
		{
			System.out.print("idUser: " + user.getKey() + ", ");
		    user.getValue().print();
		}
	}
}
