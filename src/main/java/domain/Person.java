package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person extends Contact {

    private String surname;
    private LocalDate birthDate;
    private String gender;

    public Person(String name, String surname, String phoneNumber, LocalDate birthDate, String gender) {
        setName(name);
        this.surname = surname;
        setPhoneNumber(phoneNumber);
        this.birthDate = birthDate;
        this.gender = gender;
        setTimeCreated(LocalDateTime.now());
        setLastEditTime(LocalDateTime.now());
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
