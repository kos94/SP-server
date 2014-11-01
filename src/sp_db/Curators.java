package sp_db;

import java.util.ArrayList;
import java.util.List;

import sp_entities.Semester;

public class Curators {
	private List<Curator> curators;
	
	public Curators() {
		curators = new ArrayList<>();
	}
	
	public void addCurator(Curator cur) {
		curators.add(cur);
	}
	
	//TODO delete
	public void tempInit() {
		Semester sem1_2012 = new Semester((byte)1, 2012);
		Semester sem2_2012 = new Semester((byte)2, 2012);
		Semester sem1_2013 = new Semester((byte)1, 2013);
		Semester sem2_2013 = new Semester((byte)2, 2013);
		Semester sem1_2014 = new Semester((byte)1, 2014);
		Semester sem2_2014 = new Semester((byte)2, 2014);
		
		Curator kav = new Curator(3);
		kav.addWork(new CuratorWork(sem1_2012, "ภั-111"));
		kav.addWork(new CuratorWork(sem2_2012, "ภั-111"));
		kav.addWork(new CuratorWork(sem1_2013, "ภั-111"));
		kav.addWork(new CuratorWork(sem2_2013, "ภั-111"));
		curators.add(kav);
		
		Curator pau = new Curator(4);
		pau.addWork(new CuratorWork(sem1_2014, "ภั-111"));
		pau.addWork(new CuratorWork(sem2_2014, "ภั-111"));
		curators.add(pau);
	}
	
	//TODO delete
	public void print() {
		System.out.println("================\nCurators");
		for(Curator c: curators) {
			c.print();
		}
	}
}
