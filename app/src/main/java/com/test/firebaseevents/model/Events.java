package com.test.firebaseevents.model;

public class Events {

    String Customer_Name;
    String Customer_Number;
    String Event_Description;
    String Event_Type;

    public Events(String customer_Name, String customer_Number, String event_Description, String event_Type) {
        Customer_Name = customer_Name;
        Customer_Number = customer_Number;
        Event_Description = event_Description;
        Event_Type = event_Type;
    }

    public Events() {
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getCustomer_Number() {
        return Customer_Number;
    }

    public void setCustomer_Number(String customer_Number) {
        Customer_Number = customer_Number;
    }

    public String getEvent_Description() {
        return Event_Description;
    }

    public void setEvent_Description(String event_Description) {
        Event_Description = event_Description;
    }

    public String getEvent_Type() {
        return Event_Type;
    }

    public void setEvent_Type(String event_Type) {
        Event_Type = event_Type;
    }

}
