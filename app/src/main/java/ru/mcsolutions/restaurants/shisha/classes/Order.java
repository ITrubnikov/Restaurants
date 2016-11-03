package ru.mcsolutions.restaurants.shisha.classes;

import java.util.Date;

public class Order {

    String id, name;
    String idOrderStatus;
    Double amount;
    Date oDate, pDate;
    String idLocation;
    String idWorkDay;

    public Order() {
        id = "";
        name = "";
        idOrderStatus = "";
        amount = new Double(0);
        oDate = null;
        pDate = null;
        idLocation = "";
        idWorkDay = "";

    }

    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public void setIdOrderStatus(String idOrderStatus) {this.idOrderStatus = idOrderStatus;}
    public String getIdOrderStatus() {return idOrderStatus;}

    public void setIdLocation(String idLocation) {this.idLocation = idLocation;}
    public String getIdLocation() {return idLocation;}

    public void setIdWorkDay(String idWorkDay) {this.idWorkDay = idWorkDay;}
    public String getIdWorkDay() {return idWorkDay;}

}
