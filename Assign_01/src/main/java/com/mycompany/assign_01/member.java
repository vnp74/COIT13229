package com.mycompany.assign_01;

import java.io.Serializable;

public class member implements Serializable {
    private static final long serialVersionUID = 1L;

    private String FirstName;
    private String LastName;
    private String address;
    private String Ph_No;

    public member(String FirstName, String LastName, String address, String Ph_No) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.address = address;
        this.Ph_No = Ph_No;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return Ph_No;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + FirstName + '\'' +
                ", lastName='" + LastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + Ph_No + '\'' +
                '}';
    }
}
