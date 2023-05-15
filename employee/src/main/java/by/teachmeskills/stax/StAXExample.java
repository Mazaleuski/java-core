package by.teachmeskills.stax;

import by.teachmeskills.interfaces.FilePath;
import by.teachmeskills.Employee;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StAXExample {
    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        boolean bFirstName = false, bLastName = false, bPosition = false, bDepartment = false, bWorkExperience = false;
        String firstName = null, lastName = null, position = null, department = null, workExperience = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(Files.newInputStream(Path.of(FilePath.PATH_FILE_XML)));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("firstname")) {
                            bFirstName = true;
                        } else if (qName.equalsIgnoreCase("lastname")) {
                            bLastName = true;
                        } else if (qName.equalsIgnoreCase("position")) {
                            bPosition = true;
                        } else if (qName.equalsIgnoreCase("department")) {
                            bDepartment = true;
                        } else if (qName.equalsIgnoreCase("workExperience")) {
                            bWorkExperience = true;
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        Characters characters = event.asCharacters();
                        if (bFirstName) {
                            firstName = characters.getData();
                            bFirstName = false;
                        }
                        if (bLastName) {
                            lastName = characters.getData();
                            bLastName = false;
                        }
                        if (bPosition) {
                            position = characters.getData();
                            bPosition = false;
                        }
                        if (bDepartment) {
                            department = characters.getData();
                            bDepartment = false;
                        }
                        if (bWorkExperience) {
                            workExperience = characters.getData();
                            bWorkExperience = false;
                        }
                    }

                    case XMLStreamConstants.END_ELEMENT -> {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equalsIgnoreCase("employee")) {
                            employees.add(new Employee(firstName, lastName, position, department, workExperience));
                        }
                    }
                }
            }
            reader.close();
        } catch (XMLStreamException | IOException e) {
            System.out.println(e.getMessage());
        }
        employees.forEach(System.out::println);
    }
}
