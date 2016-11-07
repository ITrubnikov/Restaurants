package ru.mcsolutions.restaurants.shisha.tools;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.classes.CurrentOrder;
import ru.mcsolutions.restaurants.shisha.classes.Location;
import ru.mcsolutions.restaurants.shisha.classes.MainMenu;
import ru.mcsolutions.restaurants.shisha.classes.Parsers;

public class Global {

    public static double latitude=0;
    public static double longitude=0;

    public static String PACKNAME = "";
    public static String googleAccount = "";

    public static boolean isIn = false;
    public final static String COMPANY="shisha";
    public static Location location = new Location();
    public final static CurrentOrder currentOrder = new CurrentOrder();//Самый главный класс...
    public static ArrayList<MainMenu> mainMenus = new ArrayList<MainMenu>();
    public final static Parsers parsers = new Parsers();

    public final static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static DecimalFormat decimalFormat = null;

    final static String HTTP = "http://cliga.mcsolutions.ru:8080/dpls/rest/xdb_";//будет изменен на что-то подходящее...
    public static String CREDENTIALS = Base64.encodeToString("rest:super123".getBytes(), Base64.NO_WRAP);
    final static Integer TIMEOUT = 10000;
    public final static int HTTP_PENDING = 0;
    public final static int HTTP_FINISHED = 1;

    public static String NO_ACCESS_ERROR = "Вы не имеете прав для использования приложения";
    public static String TIMEOUT_ERROR = "База временно недоступна. Попробуйте обновиться позже";
    public static String INTERNET_NOT_AVAILABLE = "Интернет недоступен";
    public static final String GPS_NOT_AVAILABLE = "Нет разрешений на GPS и GPS от сети";

    public static void init(Context context) {
        PACKNAME = context.getPackageName().substring(15);

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator(' ');
        decimalFormat = new DecimalFormat("#,##0.00", decimalFormatSymbols);

        AccountManager accountManager = AccountManager.get(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
        }else{
            Account[] accounts = accountManager.getAccountsByType("com.google");
            if(accounts.length>0) {
                googleAccount = accounts[0].name;
            }
        }
    }

}
