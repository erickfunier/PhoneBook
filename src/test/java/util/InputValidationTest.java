package util;

import org.junit.jupiter.api.Test;

class InputValidationTest {
    @Test
    void phoneValidation() {
        String phone1 = "";
        ErrorMessage errorMessage = InputValidation.phoneValidation(phone1);
        if (!errorMessage.isError()) {
            System.out.println("[phoneValidation] FAIL: Unexpected valid phone!");
            return;
        }

        String phone2 = "(123) 1234-1234";
        errorMessage = InputValidation.phoneValidation(phone2);
        if (errorMessage.isError()) {
            System.out.println("[phoneValidation] FAIL: Unexpected invalid phone!");
            return;
        }
        System.out.println("[phoneValidation] PASS");
    }

    @Test
    void genderValidation() {
        String gender1 = "L";
        ErrorMessage errorMessage = InputValidation.genderValidation(gender1);
        if (!errorMessage.isError()) {
            System.out.println("[genderValidation] FAIL: Unexpected valid gender!");
            return;
        }

        String gender2 = "F";
        errorMessage = InputValidation.genderValidation(gender2);
        if (errorMessage.isError()) {
            System.out.println("[genderValidation] FAIL: Unexpected invalid gender!");
            return;
        }
        System.out.println("[genderValidation] PASS");
    }

    @Test
    void birthDateValidation() {
        String birthDate1 = "10-10-2010";
        ErrorMessage errorMessage = InputValidation.birthDateValidation(birthDate1);

        if (!errorMessage.isError()) {
            System.out.println("[birthDateValidation] FAIL: Unexpected valid birth date!");
            return;
        }

        String birthDate2 = "2010-10-10";
        errorMessage = InputValidation.birthDateValidation(birthDate2);

        if (errorMessage.isError()) {
            System.out.println("[birthDateValidation] FAIL: Unexpected invalid birth date!");
            return;
        }

        System.out.println("[birthDateValidation] PASS");
    }

    @Test
    void isNumeric() {
        String number1 = "-a";
        boolean num1 = InputValidation.isNumeric(number1);
        if (num1) {
            System.out.println("[isNumeric] FAIL: Unexpected valid number!");
            return;
        }

        String number2 = "1";
        num1 = InputValidation.isNumeric(number2);
        if (!num1) {
            System.out.println("[isNumeric] FAIL: Unexpected invalid number!");
            return;
        }
        System.out.println("[isNumeric] PASS");
    }
}