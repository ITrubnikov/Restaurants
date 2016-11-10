package ru.mcsolutions.restaurants.shisha.classes;

import java.util.Date;

public class Portion {

    int portion;
    Date date;
    Double amount;

    public Portion(int portion) {
        this.portion = portion;
        amount = new Double(0);
        date = null;
    }

    public Portion(int portion, Date date, Double amount) {
        this.portion = portion;
        this.date = date;
        this.amount = amount;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }
    public int getPortion() {
        return portion;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Double getAmount() {
        return amount;
    }

    public void setDate(Date Date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
}
