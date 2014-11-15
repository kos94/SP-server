package sp_db;

import java.util.*;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import sp_entities.Semester;
import sp_entities.Semesters;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Curators {
	@XmlJavaTypeAdapter(CuratorsMapAdapter.class)
	private Map<Integer, List<CuratorWork>> curators;
	
	public Curators() {
		curators = new HashMap<>();
	}
	
	public void addCurator(int idCur, List<CuratorWork> works) {
		curators.put(idCur, works);
	}
	
	public Set<Semester> getCuratorSemesters(int idCur) {
		List<CuratorWork> works = curators.get(idCur);
		if(works == null) return null;
		Set<Semester> sems = new HashSet<>();
		for(CuratorWork w : works) {
			sems.add(w.sem);
		}
		return sems;
	}
	
	public String getCuratorGroup(int idCur, Semester semester) {
		List<CuratorWork> works = curators.get(idCur);
		if(works == null) return null;
		for(CuratorWork w : works) {
			if(w.sem.equals(semester)) {
				return w.group;
			}
		}
		return null;
	}
	
	//TODO delete
	public void tempInit() {
		Semester sem1_2012 = new Semester((byte)1, 2012);
		Semester sem2_2012 = new Semester((byte)2, 2012);
		Semester sem1_2013 = new Semester((byte)1, 2013);
		Semester sem2_2013 = new Semester((byte)2, 2013);
		Semester sem1_2014 = new Semester((byte)1, 2014);
		Semester sem2_2014 = new Semester((byte)2, 2014);
		
		List<CuratorWork> works= new ArrayList<>();
		works.add(new CuratorWork(sem1_2012, "ภั-111"));
		works.add(new CuratorWork(sem2_2012, "ภั-111"));
		works.add(new CuratorWork(sem1_2013, "ภั-111"));
		works.add(new CuratorWork(sem2_2013, "ภั-111"));
		addCurator(3, works);
		
		works= new ArrayList<>();
		works.add(new CuratorWork(sem1_2014, "ภั-111"));
		works.add(new CuratorWork(sem2_2014, "ภั-111"));
		addCurator(4, works);
	}
	
	//TODO delete
	public void print() {
		System.out.println("================\nCurators");
		for (Map.Entry<Integer, List<CuratorWork>> cur: curators.entrySet())
		{
			System.out.println("idCurator: " + cur.getKey());
		    for(CuratorWork w : cur.getValue()) {
		    	w.print();
		    }
		}
	}
}
