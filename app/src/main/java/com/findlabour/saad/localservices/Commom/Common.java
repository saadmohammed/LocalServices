package com.findlabour.saad.localservices.Commom;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.findlabour.saad.localservices.Model.User;

/**
 * Created by Samsung on 04-Mar-18.
 */

public class Common {
    public static User currentuser;

    //Internet Connection Checking
    public static boolean isConnectedToInternet(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){

            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if(info != null){

                for(int i=0;i<info.length;i++){

                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }


        }
        return false;
    }

}
