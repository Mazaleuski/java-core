package by.teachmeskills.jaxb;

import by.teachmeskills.Employees;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

import static by.teachmeskills.interfaces.FilePath.PATH_FILE_XML;

public class JaxbWorker {

    public static void main(String[] args) {
        try {
            convertFromXmlToObject();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static void convertFromXmlToObject() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Employees es = (Employees) jaxbUnmarshaller.unmarshal(new File(PATH_FILE_XML));
        es.getEmployees().forEach(System.out::println);
    }
}
