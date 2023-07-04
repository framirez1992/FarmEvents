package com.far.farmevents.Globals;

import android.content.Context;

import com.far.farmevents.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Functions {
    public  static  String getAppVersion(){
        return "v ".concat(BuildConfig.VERSION_CODE+"");
    }
    public static String formatDate(Date date){
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
