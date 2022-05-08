package eu.ase.firebaseauthlicenta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import eu.ase.firebaseauthlicenta.Common.Util;

public class MessageActivity extends AppCompatActivity {

    private TextView tvMessage;
    private ProgressBar pbMessage;
    private ConnectivityManager.NetworkCallback networkCallback; // asa ne vom da seama daca starea internetului se schimba sau nu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        tvMessage = findViewById(R.id.tvMessage);
        pbMessage = findViewById(R.id.pbMessage);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){ // putem folosi acest networkCallback daca avem versiunea >= 21, adica LOLLIPOP, pe cele precedente nu este posibil
            networkCallback = new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull Network network) { // cand internetul este available
                    super.onAvailable(network);
                    finish();
                } // cand avem conexiune, ne intoarce in activitate

                @Override
                public void onLost(@NonNull Network network) { // cand internetul este pierdut
                    super.onLost(network);
                    tvMessage.setText(R.string.no_internet);
                } // cand nu avem conexiune, ne va aparea mesajul
            };

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE); // astfel inregistram Callback-ul cu Connectivity Manager-ul
            // CONNECTIVITY_SERVICE este service-ul pe care-l dorim
            connectivityManager.registerNetworkCallback(new NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build(), networkCallback); // cu aceasta metoda va intra pe onAvailable si pe onLost si va determina starile internetului


        }

    }

    public void btnRetryClick(View view){
        pbMessage.setVisibility(View.VISIBLE);
        if(Util.connectionAvailable(this)){ // daca conexiunea este available
            finish(); // mesajul dispare
        }
        else
        {
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pbMessage.setVisibility(View.GONE);
                }
            }, 1000);
        }

    }

    public void btnCloseClick(View view){
        finishAffinity(); // inchidem toate activitatile si ne iese din aplicatie
    }

}