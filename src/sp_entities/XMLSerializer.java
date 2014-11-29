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
