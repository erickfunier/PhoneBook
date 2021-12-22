package domain;

import java.time.LocalDateTime;

public class Organization extends Contact {

    private String address;

    public Organization(String name, String address, String phoneNumber) {
        setName(name);
        this.address = address;
        setPhoneNumber(phoneNumber);
        setTimeCreated(LocalDateTime.now());
        setLastEditTime(LocalDateTime.now());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
