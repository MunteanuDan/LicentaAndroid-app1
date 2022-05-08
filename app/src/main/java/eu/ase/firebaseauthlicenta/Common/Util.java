package eu.ase.firebaseauthlicenta.Common;

import android.content.Context;
import android.net.ConnectivityManager;

public class Util {

    public static boolean connectionAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); // pentru a testa daca conexiunea la internet este available sau nu

        if(connectivityManager != null && connectivityManager.getActiveNetworkInfo() !=null) { // daca exista conexiune la internet
            return connectivityManager.getActiveNetworkInfo().isAvailable(); // conexiunea la internet este available
        }
        else
        {
            return false; // nu exista conexiune la internet
        }

    }

}
