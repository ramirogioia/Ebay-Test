package helpers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLReader {
	
	private final String sourceXML="src/test/resources/Configuration.xml";
	
	public String readNode(String node) {
		File sourceFile = new File(sourceXML);
		 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder dBuilder;
	     String result="";
	     try {
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(sourceFile);
				doc.getDocumentElement().normalize();
				result = doc.getElementsByTagName(node).item(0).getTextContent();
		} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	       
		return result;
	}
}
