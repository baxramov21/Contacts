package com.example.contacts;

public class Contact {

    private String contactName;
    private String contactNumber;
    private int contactImage;
    private String contactEmail;
    private int id;

    public Contact(String contactName, String contactNumber, int contactImage, String contactEmail, int id) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactImage = contactImage;
        this.contactEmail = contactEmail;
        this.id = id;
    }

    public Contact(String contactName, String contactNumber, int contactImage, String contactEmail) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactImage = contactImage;
        this.contactEmail = contactEmail;
    }

    public Contact(String contactName, String contactNumber, String contactEmail) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }


    public Contact(String contactName, String contactNumber, int contactImage) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactImage = contactImage;
    }

    public Contact(int id, String contactName , String contactNumber) {
        this.id = id;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getContactImage() {
        return contactImage;
    }

    public void setContactImage(int contactImage) {
        this.contactImage = contactImage;
    }
}
