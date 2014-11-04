package sp_db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

class ByteArrayAdapter extends XmlAdapter<int[], byte[]> {
	@Override
	public int[] marshal(byte[] arg0) throws Exception {
		int[] arr = new int[arg0.length];
		for(int i=0; i<arg0.length; i++)
			arr[i] = arg0[i];
		return arr;
	}

	@Override
	public byte[] unmarshal(int[] arg0) throws Exception {
		byte[] arr = new byte[arg0.length];
		for(int i=0; i<arg0.length; i++)
			arr[i] = (byte)arg0[i];
		return arr;
	}
}

@XmlAccessorType(XmlAccessType.FIELD)
public class StudentSubjMark {
	protected int idStudent;
	protected String subj;
	@XmlJavaTypeAdapter(ByteArrayAdapter.class)
	protected byte [] marks;
	
	public StudentSubjMark() { marks = new byte[3]; }
	
	public StudentSubjMark(int id, String subject, int m1, int m2, int m3) {
		this();
		idStudent = id;
		subj = subject;
		marks[0] = (byte)m1;
		marks[1] = (byte)m2;
		marks[2] = (byte)m3;
	}
	
	//TODO delete 
	public void print() {
		System.out.println("student: " + idStudent + ", subj: " + subj);
		System.out.println("  marks. 1: " + marks[0] + ", 2: " + marks[1] + ", 3: " + marks[2]);
	}
}
