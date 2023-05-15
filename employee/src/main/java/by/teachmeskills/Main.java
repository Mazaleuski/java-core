package by.teachmeskills;

import by.teachmeskills.exceptions.ValidationException;
import by.teachmeskills.interfaces.FilePath;
import by.teachmeskills.sax.SaxExample;
import by.teachmeskills.utils.ValidatorUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    static Employee employee;

    public static void main(String[] args) {
        try {
            System.out.println(checkEmployeeXML("Argunova"));
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            ValidatorUtils.validationName(fullName);
            ValidatorUtils.validationPosition(employee.getPosition());
            ValidatorUtils.validationDepartment(employee.getDepartment());
            ValidatorUtils.validationWorkExperience(employee.getWorkExperience());
        } catch (ParserConfigurationException | SAXException | IOException | ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String checkEmployeeXML(String lastName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxExample.XMLHandler handler = new SaxExample.XMLHandler();
        parser.parse(new File(FilePath.PATH_FILE_XML), handler);
        List<Employee> emp = SaxExample.getListEmployee();
        employee = emp.stream().filter(e -> e.getLastName()
                        .equals(lastName)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Employee: " + lastName + " not found."));
        return employee.toString();
    }
}
