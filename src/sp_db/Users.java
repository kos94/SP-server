package sp_db;

import java.util.*;
import javax.xml.bind.annotation.*;
import sp_entities.UserStatus;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
	@XmlElement(name="user")
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
	
	public String getUserName(int idUser) {
		User user = users.get(idUser);
		if(user == null) return "";
		return user.getName();
	}
	
	public User getUser(int idUser) {
		return users.get(idUser);
	}
	
	Map<Integer, String> getUsersNames(Set<Integer> idUsers) {
		Map<Integer, String> userNames = new HashMap<>();
		for(Integer id : idUsers) {
			userNames.put(id, getUserName(id));
		}
		return userNames;
	}
	
	//TODO delete
	public void tempInit() {
		int c = 0;
		addUser(++c, new User("Пригожев А.С.", UserStatus.TEACHER, "aaa"));
		addUser(++c, new User("Кунгурцев А.Б.", UserStatus.TEACHER, "bbb"));
		addUser(++c, new User("Кавицкая В.С.", UserStatus.CURATOR, "ccc"));
		addUser(++c, new User("Паулин О.Н.", UserStatus.CURATOR, "ddd"));
		addUser(++c, new User("Иванов И.И.", UserStatus.DEPWORKER, "eee"));
		
		addUser(++c, new User("Михайлов С.В.", UserStatus.DEPWORKER, "fff"));
		addUser(++c, new User("Чебан К.В.", UserStatus.STUDENT, "ggg"));
		addUser(++c, new User("Берлизов Е.В.", UserStatus.STUDENT, "hhh"));
		addUser(++c, new User("Беловзоров А.А.", UserStatus.STUDENT, "iii"));
		addUser(++c, new User("Гапяк В.М.", UserStatus.STUDENT, "jjj"));
		
		addUser(++c, new User("Малярозов П.А.", UserStatus.STUDENT, "kkk"));
		addUser(++c, new User("Садовый Д.Д.", UserStatus.STUDENT, "lll"));
		
		addUser(++c, new User("Иванов 131", UserStatus.STUDENT, "mmm"));
		addUser(++c, new User("Петров 132", UserStatus.STUDENT, "nnn"));
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
