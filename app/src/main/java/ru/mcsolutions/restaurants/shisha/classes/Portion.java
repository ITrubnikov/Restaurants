package ru.mcsolutions.restaurants.shisha.classes;

import java.text.ParseException;
import java.util.Date;

import ru.mcsolutions.restaurants.shisha.tools.Global;

public class Portion {

    int portion;
    Date pDate;
    Double amount;

    public Portion() {
    }

    public Portion(int portion) {
        this.portion = portion;
        amount = new Double(0);
        pDate = null;
    }

    public Portion(int portion, Date pDate, Double amount) {
        this.portion = portion;
        this.pDate = pDate;
        this.amount = amount;
    }

    public void setPortion(String portion) { this.portion = Integer.valueOf(portion);}
    public int getPortion() {
        return portion;
    }

    public void setAmount(Double amount) { this.amount = amount; }
    public void setAmount(String amount) { this.amount = Double.valueOf(amount); }
    public Double getAmount() {
        return amount;
    }

    public void setPDate(Date pDate) { this.pDate = pDate;}
    public void setPDate(String pDate) throws ParseException {
        this.pDate = Global.simpleDateTimeFormat.parse(pDate);
    }
    public Date getPDate() {
        return pDate;
    }

    public String toString(){
        return getPortion() + ", " + getPDate() + ", " + getAmount();
    }
}
