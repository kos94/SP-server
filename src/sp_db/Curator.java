package sp_db;

import java.util.ArrayList;
import java.util.List;

public class Curator {
	protected int id;
	protected List<CuratorWork> works;
	
	public Curator() {
		works = new ArrayList<>();
	}
	
	public Curator(int idCurator) {
		this();
		id = idCurator;
	}
	
	public void addWork(CuratorWork w) {
		works.add(w);
	}
	
	//TODO delete
	public void print() {
		System.out.println("Curator id: " + id + ". works: ");
		for(CuratorWork cw : works) {
			cw.print();
		}
	}
}
