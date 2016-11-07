package ru.mcsolutions.restaurants.shisha.tools;

import android.util.Log;

import java.util.Date;

public class Utils {

    public static void log(String message){
        Log.d("timur", Global.simpleDateTimeFormat.format(new Date()) + ": " + message);
    }

    public static void log(int message){
        Log.d("timur", Global.simpleDateTimeFormat.format(new Date()) + ": " + message);
    }

    public static String getDecimalString(Double double_){
        return Global.decimalFormat.format(double_);
    }

    public static String getIdOrderStatus(String idOrderStatus){
        String result = "";
        switch(idOrderStatus){
            case "1": result = "Новый";break;
            case "2": result = "Обслуживание";break;
            case "3": result = "Закрыт / Оплачен";break;
            case "4": result = "Клиент Редиска";break;
            default: break;
        }
        return result;
    }

    public static String getIdOrderDishStatus(String idOrderStatus){
        String result = "";
        switch(idOrderStatus){
            case "1": result = "Ожидается";break;
            case "2": result = "Готовится";break;
            case "3": result = "Серверуется";break;
            case "4": result = "Подано";break;
            default: break;
        }
        return result;
    }

}
