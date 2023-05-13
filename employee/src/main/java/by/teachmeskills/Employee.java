package by.teachmeskills;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private String workExperience;

    public Employee(){}

    public Employee(String firstName, String lastName, String position, String department, String workExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.workExperience = workExperience;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    @Override
    public String toString() {
        return "firstName : " + firstName + "\n" +
                "lastName : " + lastName + "\n" +
                "position : " + position + "\n" +
                "department : " + department + "\n" +
                "workExperience : " + workExperience + "\n";
    }
}