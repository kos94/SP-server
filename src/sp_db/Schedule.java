package sp_db;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
	List<SchedRecord> records;
	
	public Schedule() {
		records = new ArrayList<>();
	}
	
	public void addRecord(SchedRecord rec) {
		records.add(rec);
	}
	
	//TODO delete
	public void print() {
		System.out.println("===============\nSchedule: ");
		for(SchedRecord r : records) {
			r.print();
		}
	}
}
