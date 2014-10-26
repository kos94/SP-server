package sp_entities;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLSerializer {
	
	public static void testSemesters() {
		Semesters sems = new Semesters();
		sems.addSemester(1,  2012);
		sems.addSemester(2,  2012);
		sems.addSemester(1,  2013);
		sems.addSemester(2,  2014);
		saveObject(sems, "C:\\ws\\semesters.xml");
		sems = null;
		sems = (Semesters)loadObject("C:\\ws\\semesters.xml", Semesters.class);
		sems.print();
	}
	
	public static void testGroupSubjectMarks() {
		GroupSubjectMarks sm = new GroupSubjectMarks();
		sm.addMark("Розенбаум", 10, 20, 30);
		sm.addMark("Шуфутинский", 20 , 30, 60);
		sm.addMark("Михайлов", 25 , 30, 50);
		saveObject(sm, "C:\\ws\\subjmarks.xml");
		sm = null;
		sm = (GroupSubjectMarks)loadObject("C:\\ws\\subjmarks.xml", GroupSubjectMarks.class);
		sm.print();
	}
	
	public static void testGroupStageMarks() {
		GroupStageMarks stm = new GroupStageMarks();
		stm.addSubject("ООП");
		stm.addSubject("ОПИ");
		stm.addSubject("Дискретная математика");
		
		ArrayList<Integer> marks = new ArrayList<>();
		marks.add(10); marks.add(45); marks.add(90);
		stm.addMark("Стетхем", marks);
		marks = new ArrayList<>();
		marks.add(20); marks.add(30); marks.add(60);
		stm.addMark("Джонсон", marks);
		marks = new ArrayList<>();
		marks.add(30); marks.add(30); marks.add(75);
		stm.addMark("Шварцнегер", marks);
		saveObject(stm, "C:\\ws\\stagemarks.xml");
		stm = null;
		stm = (GroupStageMarks)loadObject("C:\\ws\\stagemarks.xml", GroupStageMarks.class);
		stm.print();
		
	}
	
	public static void testStudentMarks() {
		StudentSemMarks ssm = new StudentSemMarks();
		ssm.addMark("ПОАС", 20, 30, 60);
		ssm.addMark("Политология", 45, 50, 90);
		saveObject(ssm, "C:\\ws\\studentmarks.xml");
		ssm = null;
		ssm = (StudentSemMarks)loadObject("C:\\ws\\studentmarks.xml", StudentSemMarks.class);
		ssm.print();
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ПЕРЕПИСАТЬ КАК В КОНСПЕКТЕ
	public static void saveObject(Object obj, String filePath) {
        try {
            File file = new File(filePath);
            JAXBContext jaxb = JAXBContext.newInstance(obj.getClass());
            Marshaller mar = jaxb.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.marshal(obj, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ПЕРЕПИСАТЬ КАК В КОНСПЕКТЕ
    public static Object loadObject(String filePath, Class<?> objClass) {
        try {
            File file = new File(filePath);
            JAXBContext jaxb = JAXBContext.newInstance(objClass);
            Unmarshaller unmar = jaxb.createUnmarshaller();
            Object user = unmar.unmarshal(file);
            return user;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
