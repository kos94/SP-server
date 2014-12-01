package sp_db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import sp_entities.Semester;

@XmlAccessorType(XmlAccessType.FIELD)
public class CuratorWork {
	protected Semester sem;
	protected String group;
	
	public CuratorWork() {}
	
	public CuratorWork(Semester semester, String group) {
		sem = semester;
		this.group = group;
	}
}
