package sp_db;

import java.util.ArrayList;
import java.util.List;

public class Curators {
	private List<Curator> curators;
	
	public Curators() {
		curators = new ArrayList<>();
	}
	
	public void addCurator(Curator cur) {
		curators.add(cur);
	}
	
	//TODO delete
	public void print() {
		System.out.println("================\nCurators");
		for(Curator c: curators) {
			c.print();
		}
	}
}
