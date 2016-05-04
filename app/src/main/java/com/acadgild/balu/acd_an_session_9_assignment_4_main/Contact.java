package com.acadgild.balu.acd_an_session_9_assignment_4_main;

/**
 * Created by BALU on 4/19/2016.
 */
public class Contact
{
    private String contact_name;
    private String contact_number;
    private String contact_dob;

    public Contact() {
    }

    public Contact(String contact_name, String contact_number, String contact_dob) {
        this.contact_name = contact_name;
        this.contact_number = contact_number;
        this.contact_dob = contact_dob;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getContact_dob() {
        return contact_dob;
    }

    public void setContact_dob(String contact_dob) {
        this.contact_dob = contact_dob;
    }
}
