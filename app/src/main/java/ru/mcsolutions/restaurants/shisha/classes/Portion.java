package ru.mcsolutions.restaurants.shisha.classes;

import java.util.Date;

public class Portion {

    int portion;
    Double amount;
    Date date;

    public Portion(int portion) {
        this.portion = portion;
        amount = new Double(0);
        date = null;
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
