package com.example.lg.andelatask.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Oyeleke Okiki on 9/12/2017.
 */

public class Utility {

    /**
     * Check if device is connected to the Internet
     * This method is connection-agnostic (Wifi or Mobile Data will return true as long as the device is connected)
     * @param context Context which this method was called
     * @return Boolean Is device connected to the Internet
     * */

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null;
    }


    /**
     * Capitalize First Letters of Strings
     * @param original Original string to be capitalized
     * @return String capitalized
     * */

    public static String getSentenceCase(String original) {
        return original.length() == 0 ? original : original.substring(0, 1).toUpperCase() + original.substring(1);
    }


}
