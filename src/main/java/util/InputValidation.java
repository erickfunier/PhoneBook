package util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
    public static ErrorMessage phoneValidation(String phone) {
        String regex = "(^[+]?[(]?[0-9a-zA-Z]+[)]?$)|(^[+]?[(]?[0-9a-zA-Z]+[)]?[ -]?[0-9a-zA-Z]+$)|(^[+]?[0-9a-zA-Z]+[ -]?[(]?[0-9a-zA-Z]+[)]?$)|(^[+]?[(]?[(0-9a-zA-Z]+[)]?[ -]?[(]?[0-9a-zA-Z]{2,}[)]?[ -]?[0-9a-zA-Z]{2,}$)|(^[+]?[(]?[(0-9a-zA-Z]+[)]?[ -]?[(]?[0-9a-zA-Z]{2,}[)]?[ -]?[0-9a-zA-Z]{2,}[ -]?[0-9a-zA-Z]{2,}$)|(^[+]?[(]?[(0-9a-zA-Z]+[)]?[ -]?[(]?[0-9a-zA-Z]{2,}[)]?[ -]?[0-9a-zA-Z]{2,}[ -]?[0-9a-zA-Z]{2,}[ -]+[(0-9a-zA-Z]+$)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        if (matcher.find()) {
            return new ErrorMessage(false, "Valid number!");
        } else {
            return new ErrorMessage(true, "Wrong number format!");
        }
    }

    public static ErrorMessage genderValidation(String gender) {
        if (gender.equals("M") || gender.equals("F")) {
            return new ErrorMessage(false, "Valid gender!");
        } else {
            return new ErrorMessage(true, "Bad gender!");
        }
    }

    public static ErrorMessage birthDateValidation(String birthDate) {
        try {
            LocalDate.parse(birthDate);
            return new ErrorMessage(false, "Valid birth date!");
        } catch (DateTimeException e) {
            return new ErrorMessage(true, "Bad birth date!");
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
