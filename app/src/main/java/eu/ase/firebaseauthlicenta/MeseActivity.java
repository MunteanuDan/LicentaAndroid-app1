package eu.ase.firebaseauthlicenta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import eu.ase.firebaseauthlicenta.NrMasaRezervari.Masa1Activity;
import eu.ase.firebaseauthlicenta.NrMasaRezervari.Masa2Activity;
import eu.ase.firebaseauthlicenta.NrMasaRezervari.Masa3Activity;
import eu.ase.firebaseauthlicenta.profile.ProfileActivity;

public class MeseActivity extends AppCompatActivity {

    private FloatingActionButton navigation_mese_btn1;
    private FloatingActionButton navigation_mese_btn2;

    private DatabaseReference mRootRef; // aici avem o referinta catre baza de date
    private FirebaseAuth mAuth;
    private String userId;

    private AnimationDrawable anim, anim2, anim3;
    private Button btn_masa_1, btn_masa_2, btn_masa_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mese);

        initComponents();

        navigation_mese_btn1 = findViewById(R.id.fab_mese_car);
        navigation_mese_btn2 = findViewById(R.id.fab_mese_walk);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid(); // in astea 3 linii am luat id-ul user-ului

        mRootRef = FirebaseDatabase.getInstance().getReference();


        navigation_mese_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=44.417233,26.174405&mode=d")); // d - drive
                intent.setPackage("com.google.android.apps.maps");

                if(intent.resolveActivity(getPackageManager()) != null){ // aceasta linie este in cazul in care utilizatorul nu are googleMaps instalat, aplicatia sa nu dea crush
                    startActivity(intent);
                }
            }
        });

        navigation_mese_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=44.417233,26.174405&mode=w")); // w - walk
                intent.setPackage("com.google.android.apps.maps");

                if(intent.resolveActivity(getPackageManager()) != null){ // aceasta linie este in cazul in care utilizatorul nu are googleMaps instalat, aplicatia sa nu dea crush
                    startActivity(intent);
                }
            }
        });

    }

    private void initComponents() {

        btn_masa_1 = findViewById(R.id.btnOrderMasa1);
        btn_masa_2 = findViewById(R.id.btnOrderMasa2);
        btn_masa_3 = findViewById(R.id.btnOrderMasa3);

        anim = (AnimationDrawable) btn_masa_1.getBackground();
        anim.setEnterFadeDuration(2300);
        anim.setExitFadeDuration(2300);

        anim2 = (AnimationDrawable) btn_masa_2.getBackground();
        anim2.setEnterFadeDuration(2800);
        anim2.setExitFadeDuration(2800);

        anim3 = (AnimationDrawable) btn_masa_3.getBackground();
        anim3.setEnterFadeDuration(2700);
        anim3.setExitFadeDuration(2700);

    }



    public void btnCalendar (View v)
    {
        Intent intent = new Intent(MeseActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    public void btnMasa1 (View v)
    {
        Intent intent = new Intent(MeseActivity.this, Masa1Activity.class);
        startActivity(intent);
    }

    public void btnMasa2 (View v)
    {
        Intent intent = new Intent(MeseActivity.this, Masa2Activity.class);
        startActivity(intent);
    }

    public void btnMasa3 (View v)
    {
        Intent intent = new Intent(MeseActivity.this, Masa3Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (anim != null && !anim.isRunning()) {
            anim.start();
        }

        if (anim2 != null && !anim2.isRunning()) {
            anim2.start();
        }

        if (anim3 != null && !anim3.isRunning()) {
            anim3.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (anim != null && anim.isRunning()) {
            anim.stop();
        }

        if (anim2 != null && anim2.isRunning()) {
            anim2.stop();
        }

        if (anim3 != null && anim3.isRunning()) {
            anim3.stop();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // metoda este apelata cand activitatea este creata

        getMenuInflater().inflate(R.menu.menu_main, menu); // inflater-ul ne va converti xml file-ul intr-un view vizibil

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // se va executa de fiecare data cand un user va da click pe orice din menu

        int id = item.getItemId(); // luam id-ul item-ului selectat
        if(id==R.id.mnuProfile){
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}