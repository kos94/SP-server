package sp_entities;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLSerializer {

	public static void testSemesters() {
		Semesters sems = new Semesters();
		sems.addSemester(1, 2012);
		sems.addSemester(2, 2012);
		sems.addSemester(1, 2013);
		sems.addSemester(2, 2014);
		saveObject(sems, "C:\\ws\\semesters.xml");
		sems = null;
		sems = (Semesters) loadObject("C:\\ws\\semesters.xml", Semesters.class);
		sems.print();
	}

	public static void testGroupSubjectMarks() {
		GroupSubjectMarks sm = new GroupSubjectMarks();
		sm.addMark("Розенбаум", 10, 20, 30);
		sm.addMark("Шуфутинский", 20, 30, 60);
		sm.addMark("Михайлов", 25, 30, 50);
		saveObject(sm, "C:\\ws\\subjmarks.xml");
		sm = null;
		sm = (GroupSubjectMarks) loadObject("C:\\ws\\subjmarks.xml",
				GroupSubjectMarks.class);
		sm.printMarks();
	}

	public static void testGroupStageMarks() {
		GroupStageMarks stm = new GroupStageMarks();
		stm.addSubject("ООП");
		stm.addSubject("ОПИ");
		stm.addSubject("Дискретная математика");

		ArrayList<Byte> marks = new ArrayList<>();
		marks.add((byte)10);
		marks.add((byte)45);
		marks.add((byte)90);
		stm.addMark("Стетхем", marks);
		marks = new ArrayList<>();
		marks.add((byte)20);
		marks.add((byte)30);
		marks.add((byte)60);
		stm.addMark("Джонсон", marks);
		marks = new ArrayList<>();
		marks.add((byte)30);
		marks.add((byte)30);
		marks.add((byte)75);
		stm.addMark("Шварцнегер", marks);
		saveObject(stm, "C:\\ws\\stagemarks.xml");
		stm = null;
		stm = (GroupStageMarks) loadObject("C:\\ws\\stagemarks.xml",
				GroupStageMarks.class);
		stm.printMarks();

	}

	public static void testStudentMarks() {
		StudentSemMarks ssm = new StudentSemMarks();
		ssm.addMark("ПОАС", 20, 30, 60);
		ssm.addMark("Политология", 45, 50, 90);
		saveObject(ssm, "C:\\ws\\studentmarks.xml");
		ssm = null;
		ssm = (StudentSemMarks) loadObject("C:\\ws\\studentmarks.xml",
				StudentSemMarks.class);
		ssm.printMarks();
	}

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DELETE IN FUTURE
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

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DELETE IN FUTURE
	public static Object loadObject(String filePath, Class<?> objClass) {
		try {
			File file = new File(filePath);
			JAXBContext jaxb = JAXBContext.newInstance(objClass);
			Unmarshaller unmar = jaxb.createUnmarshaller();
			Object obj = unmar.unmarshal(file);
			return obj;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String objectToXML(Object obj) {
		try (StringWriter strWriter = new StringWriter()) {
			JAXBContext jaxb = JAXBContext.newInstance(obj.getClass());
			Marshaller mar = jaxb.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			mar.marshal(obj, strWriter);
			String xmlString = strWriter.toString();
			return xmlString;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static Object xmlToObject(String xml, Class<?> objClass) {
		try (StringReader strReader = new StringReader(xml)){
			JAXBContext jaxb = JAXBContext.newInstance(objClass);
			Unmarshaller unmar = jaxb.createUnmarshaller();
			Object obj = unmar.unmarshal(strReader);
			return obj;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}
