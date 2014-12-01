package sp_db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import sp_entities.Semester;
import sp_entities.Semesters;
import sp_entities.UserRole;

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
}
