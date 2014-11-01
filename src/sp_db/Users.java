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
		addUser(++c, new User("aaa", "�������� �.�.", UserStatus.TEACHER));
		addUser(++c, new User("bbb", "��������� �.�.", UserStatus.TEACHER));
		addUser(++c, new User("���", "�������� �.�.", UserStatus.CURATOR));
		addUser(++c, new User("ddd", "������ �.�.", UserStatus.CURATOR));
		addUser(++c, new User("eee", "������ �.�.", UserStatus.DEPWORKER));
		
		addUser(++c, new User("fff", "�������� �.�.", UserStatus.DEPWORKER));
		addUser(++c, new User("ggg", "����� �.�.", UserStatus.STUDENT));
		addUser(++c, new User("hhh", "�������� �.�.", UserStatus.STUDENT));
		addUser(++c, new User("iii", "���������� �.�.", UserStatus.STUDENT));
		addUser(++c, new User("jjj", "����� �.�.", UserStatus.STUDENT));
		
		addUser(++c, new User("kkk", "��������� �.�.", UserStatus.STUDENT));
		addUser(++c, new User("lll", "������� �.�.", UserStatus.STUDENT));
		
		addUser(++c, new User("mmm", "������ 131", UserStatus.STUDENT));
		addUser(++c, new User("nnn", "������ 132", UserStatus.STUDENT));
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
