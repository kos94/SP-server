package sp_db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.bind.annotation.*;

import sp_entities.UserRole;

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
		System.out.println("login" + id + " " + pass);
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
	public void tempInit() throws IOException{
		FileInputStream in = new FileInputStream(DB.DB_PATH + "users.txt");
	    BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf8"));

	    String strLine;
	    UserRole role = null;
	    int c = 0;

	    while ((strLine = br.readLine()) != null) {
	    	strLine = strLine.trim();
	    	try {
	    		role = UserRole.valueOf(strLine);
	    	} catch(Exception e) {
	    		String pass = UUID.randomUUID().toString().substring(0, 4);
	    		addUser(++c, new User(strLine, role, pass));
	    	}
	    }
	    in.close();
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
