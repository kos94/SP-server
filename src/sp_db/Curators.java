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
	
	//TODO delete
	public void tempInit() throws IOException {
		FileInputStream in = new FileInputStream(DB.DB_PATH + "curators.txt");
		BufferedReader br = new BufferedReader(
				new InputStreamReader(in, "utf8"));

		String strLine;
		int id = -1;

		while ((strLine = br.readLine()) != null) {
			try {
				id = Integer.parseInt(strLine);
			} catch (Exception e) {
				List<CuratorWork> works= new ArrayList<>();
				do {
					if(strLine.equals("")) break;
					
					String[] a = strLine.split(",");
					String[] sem = a[0].trim().split(" ");
					int sInd = Integer.parseInt(sem[0]);
					int sYear = Integer.parseInt(sem[1]);
					String group = a[1].trim();
					CuratorWork w = new CuratorWork(new Semester(sInd, sYear), group);
					works.add(w);
				} while ((strLine = br.readLine()) != null);
				addCurator(id, works);
			}
		}
		in.close();
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
