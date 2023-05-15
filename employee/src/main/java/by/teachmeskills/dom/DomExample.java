package by.teachmeskills.dom;

import by.teachmeskills.interfaces.FilePath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import by.teachmeskills.Employee;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomExample {
    static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        String firstName, lastName, position, department, workExperience;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(FilePath.PATH_FILE_XML);

            NodeList nList = doc.getElementsByTagName("employee");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    firstName = eElement.getElementsByTagName("firstName").item(0).getTextContent();
                    lastName = eElement.getElementsByTagName("lastName").item(0).getTextContent();
                    position = eElement.getElementsByTagName("position").item(0).getTextContent();
                    department = eElement.getElementsByTagName("department").item(0).getTextContent();
                    workExperience = eElement.getElementsByTagName("workExperience").item(0).getTextContent();
                    employees.add(new Employee(firstName, lastName, position, department, workExperience));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
        employees.forEach(System.out::println);
    }
}
