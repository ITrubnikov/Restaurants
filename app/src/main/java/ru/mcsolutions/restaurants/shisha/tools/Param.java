package ru.mcsolutions.restaurants.shisha.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Param {

    String name;
    String value;

    public Param(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getPair(){
        String result = "";
        try {
            result = "&" + name + "=" + URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
