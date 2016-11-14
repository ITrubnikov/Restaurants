package ru.mcsolutions.restaurants.shisha.classes;

import java.text.ParseException;
import java.util.Date;

import ru.mcsolutions.restaurants.shisha.tools.Global;

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
        amount = null;
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

    public void setAmount(String amount) {
        if(!amount.equals("")){
            this.amount = Double.valueOf(amount);
        }
    }
    public Double getAmount() {return amount;}

    public void setODate(String oDate) throws ParseException {
        if(!oDate.equals("")){
            this.oDate = Global.simpleDateTimeFormat.parse(oDate);
        }
    }
    public Date getODate() {return oDate;}

    public void setPDate(String pDate) throws ParseException {
        if(pDate.equals("")){
            pDate = null;
        }else{
            this.pDate = Global.simpleDateTimeFormat.parse(pDate);
        }
    }
    public Date getPDate() {return pDate;}

}
