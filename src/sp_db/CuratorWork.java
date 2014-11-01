package sp_db;

import sp_entities.Semester;

public class CuratorWork {
	protected Semester sem;
	protected String group;
	
	public CuratorWork() {}
	
	public CuratorWork(Semester semester, String group) {
		sem = semester;
		this.group = group;
	}
	
	//TODO delete
	public void print() {
		System.out.println("sem: " + sem + ", group: " + group);
	}
}
