package by.teachmeskills.utils;

import by.teachmeskills.exceptions.ValidationException;

public class ValidatorUtils {
    private ValidatorUtils() {
    }

    public static void validationName(String name) throws ValidationException {
        if (name.matches("[A-Za-z ]+") && Character.isUpperCase(name.charAt(0))) {
            System.out.println("Name valid.");
        } else throw new ValidationException("Invalid name: " + name + ".");
    }

    public static void validationPosition(String position) throws ValidationException {
        if (position.matches("[A-Za-z -]+") && Character.isUpperCase(position.charAt(0))) {
            System.out.println("Position valid.");
        } else throw new ValidationException("Invalid position: " + position + ".");
    }

    public static void validationDepartment(String department) throws ValidationException {
        if (department.matches("[0-9A-Za-z -]+")) {
            System.out.println("Department valid.");
        } else throw new ValidationException("Invalid department: " + department + ".");
    }

    public static void validationWorkExperience(String workExperience) throws ValidationException {
        if (workExperience.matches("[0-9A-Za-z ]+")) {
            System.out.println("Work experience valid.");
        } else throw new ValidationException("Invalid work experience: " + workExperience + ".");
    }
}
