package by.teachmeskills.sax;

import by.teachmeskills.Employee;
import by.teachmeskills.interfaces.FilePath;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxExample {
    private static final List<Employee> employees = new ArrayList<>();

    public static List<Employee> getListEmployee() {
        return employees;
    }

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            parser.parse(new File(FilePath.PATH_FILE_XML), handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        employees.forEach(System.out::println);
    }

    public static class XMLHandler extends DefaultHandler {
        private String firstName, lastName, position, department, lastElement, workExperience;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElement = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
            if (!information.isEmpty()) {
                if (lastElement.equals("firstName")) {
                    firstName = information;
                }
                if (lastElement.equals("lastName")) {
                    lastName = information;
                }
                if (lastElement.equals("position")) {
                    position = information;
                }
                if (lastElement.equals("department")) {
                    department = information;
                }
                if (lastElement.equals("workExperience")) {
                    workExperience = information;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((firstName != null && !firstName.isEmpty()) && (lastName != null && !lastName.isEmpty())
                    && (position != null && !position.isEmpty()) && (department != null && !department.isEmpty())
                    && (workExperience != null && !workExperience.isEmpty())) {
                employees.add(new Employee(firstName, lastName, position, department, workExperience));
                firstName = null;
                lastName = null;
                position = null;
                department = null;
                workExperience = null;
            }
        }
    }
}
