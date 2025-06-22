package com.amonlexasoftware.yaradio.support.service;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connection {
    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager con_manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected());
    }
}
//https://www.youtube.com/watch?v=vNaxOU_j