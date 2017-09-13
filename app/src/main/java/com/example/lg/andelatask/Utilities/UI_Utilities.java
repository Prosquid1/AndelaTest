package com.example.lg.andelatask.Utilities;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by LG on 9/12/2017.
 */

public class UI_Utilities {


    public static void displayToast(Context context, String message){

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

}
