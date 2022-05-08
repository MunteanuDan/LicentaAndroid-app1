package eu.ase.firebaseauthlicenta;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

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

import eu.ase.firebaseauthlicenta.profile.ProfileActivity;

public class MainActivity4 extends AppCompatActivity {

    private DatabaseReference mRootRef; // aici avem o referinta catre baza de date
    private FirebaseAuth mAuth;
    private String userId;

    RadioButton radio_one, radio_two, radio_three;

    boolean esteRezervata1 = false;
    boolean esteRezervata2 = false;
    boolean esteRezervata3 = false;


    String esteRezervata1_bd = null;
    String esteRezervata2_bd = null;
    String esteRezervata3_bd = null;

    String esteRezervata1_user = null;
    String esteRezervata2_user = null;
    String esteRezervata3_user = null;

    String nrMasa = null;
    Spinner spnMasa;


    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid(); // in astea 3 linii am luat id-ul user-ului

        mRootRef = FirebaseDatabase.getInstance().getReference();

        radio_one = findViewById(R.id.rb_left);
        radio_two = findViewById(R.id.rb_center);
        radio_three = findViewById(R.id.rb_right);


        vizibilitateInreg();

        populatePersoanaAdapter();


        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3");

        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                nrMasa = spnMasa.getSelectedItem().toString();
                DatabaseReference databaseReferenceUsers1 = mRootRef.child("rezervari3").child(nrMasa);

                spnMasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


//                        databaseReferenceUsers1.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status1 = null;
                        String status2 = null;
                        String status3 = null;
                        if (dataSnapshot.child("esteRezervata1").getValue() != null) {
                            status1 = dataSnapshot.child("esteRezervata1").getValue().toString();
                            esteRezervata1_bd = dataSnapshot.child("esteRezervata1").getValue().toString();
                            //    esteRezervata1_user =  dataSnapshot.child(userId).child("esteRezervata1").getValue().toString();
                        }


                        // de aici incerc cu timpul


                        String m1 = radio_one.getText().toString();
                        String m2 = radio_two.getText().toString();
                        String m3 = radio_three.getText().toString();


                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

                        try {

                            Date date1 = dateFormat.parse(m1); // luam timpul de pe checkBox

                            // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                            Date dateCurrent1 = dateFormat.parse(currentTime);

                            if (date1.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
                            {
                                radio_one.setClickable(false);
                                radio_one.setBackgroundColor(0xFFC62828);
                                databaseReferenceUsers1.child("esteRezervata1").setValue(true);
                            }


                        } catch (ParseException e) {

                        }

                        // pana aici


                        try {

                            Date date2 = dateFormat.parse(m2); // luam timpul de pe checkBox

                            // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                            Date dateCurrent1 = dateFormat.parse(currentTime);

                            if (date2.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
                            {
                                radio_one.setClickable(false);
                                radio_one.setBackgroundColor(0xFFC62828);
                                databaseReferenceUsers1.child("esteRezervata2").setValue(true);
                            }


                        } catch (ParseException e) {

                        }


                        try {

                            Date date3 = dateFormat.parse(m3); // luam timpul de pe checkBox

                            // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                            Date dateCurrent1 = dateFormat.parse(currentTime);

                            if (date3.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
                            {
                                radio_one.setClickable(false);
                                radio_one.setBackgroundColor(0xFFC62828);
                                databaseReferenceUsers1.child("esteRezervata3").setValue(true);
                            }

                        } catch (ParseException e) {

                        }


                        // daca timpul este 00:00 sa le facem pe toate false (disponibile)

                        String midnightTime = "00:00";
                        try {

                            Date date = dateFormat.parse(midnightTime); // luam timpul de pe checkBox

                            // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                            Date dateCurrent1 = dateFormat.parse(currentTime);

                            if (date.equals(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
                            {
                                radio_one.setClickable(true);
                                radio_one.setBackgroundResource(R.drawable.radio_selector);
                                databaseReferenceUsers1.child("esteRezervata1").setValue(false);

                                radio_two.setClickable(true);
                                radio_two.setBackgroundResource(R.drawable.radio_selector);
                                databaseReferenceUsers1.child("esteRezervata2").setValue(false);

                                radio_three.setClickable(true);
                                radio_three.setBackgroundResource(R.drawable.radio_selector);
                                databaseReferenceUsers1.child("esteRezervata3").setValue(false);

                                Toast.makeText(MainActivity4.this, "Se pot efectua rezervari dupa ora 00:00", Toast.LENGTH_SHORT).show();

                            }


                        } catch (ParseException e) {

                        }

                        // pana aici


                        if (status1.equals("true")) {
                            radio_one.setClickable(false);
                            radio_one.setBackgroundColor(0xFFC62828);
                        }
                        if (status1.equals("false")) {
                            radio_one.setClickable(true);
                            radio_one.setBackgroundResource(R.drawable.radio_selector);

                        }


                        if (dataSnapshot.child("esteRezervata2").getValue() != null) {
                            status2 = dataSnapshot.child("esteRezervata2").getValue().toString();
                            esteRezervata2_bd = dataSnapshot.child("esteRezervata2").getValue().toString();
                            //              esteRezervata2_user =  dataSnapshot.child(userId).child("esteRezervata2").getValue().toString();
                        }

                        if (status2.equals("true")) {
                            radio_two.setClickable(false);
                            radio_two.setBackgroundColor(0xFFC62828);
                        }
                        if (status2.equals("false")) {
                            radio_two.setClickable(true);
                            radio_two.setBackgroundResource(R.drawable.radio_selector);

                        }


                        if (dataSnapshot.child("esteRezervata3").getValue() != null) {
                            status3 = dataSnapshot.child("esteRezervata3").getValue().toString();
                            esteRezervata3_bd = dataSnapshot.child("esteRezervata3").getValue().toString();
                            //           esteRezervata3_user =  dataSnapshot.child(userId).child("esteRezervata3").getValue().toString();
                        }
                        if (status3.equals("true")) {
                            radio_three.setClickable(false);
                            radio_three.setBackgroundColor(0xFFC62828);
                        }
                        if (status3.equals("false")) {
                            radio_three.setClickable(true);
                            radio_three.setBackgroundResource(R.drawable.radio_selector);
                        }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                        Toast.makeText(MainActivity4.this, "Please select table", Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void btnApply1(View v) {
        String m1 = radio_one.getText().toString();
        String m2 = radio_two.getText().toString();
        String m3 = radio_three.getText().toString();

        // facem path pentru storage, unde vom salva datele
        //  DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari1").child(userId);
        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3").child(nrMasa);
        DatabaseReference databaseReferenceUsers1 = mRootRef.child("rezervari3").child(nrMasa).child(userId);


        // facem formatul timpului
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");


        if (radio_one.isChecked()) {
            // esteRezervata11 = true;

            databaseReferenceUsers.child("esteRezervata1").setValue(true); // punem true cand user-ul este Online

            databaseReferenceUsers1.child("esteRezervata1").setValue(true); // punem true cand user-ul este Online
            databaseReferenceUsers1.child("interval1").setValue(m1);


            try {

                Date date1 = dateFormat.parse(m1); // luam timpul de pe checkBox

                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                Date dateCurrent1 = dateFormat.parse(currentTime);

                if (date1.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
                {
                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
                }

            } catch (ParseException e) {

            }

        } else if (radio_two.isChecked()) {

            // esteRezervata22 = true;

            databaseReferenceUsers.child("esteRezervata2").setValue(true); // punem true cand user-ul este Online

            databaseReferenceUsers1.child("esteRezervata2").setValue(true); // punem true cand user-ul este Online
            databaseReferenceUsers1.child("interval2").setValue(m2);

            try {

                Date date2 = dateFormat.parse(m2); // luam timpul de pe checkBox

                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                Date dateCurrent2 = dateFormat.parse(currentTime);

                if (date2.before(dateCurrent2)) // comparam timpul din checkbox cu timpul curent
                {
                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
                }

            } catch (ParseException e) {

            }


        } else if (radio_three.isChecked()) {
            //  esteRezervata33 = true;

            databaseReferenceUsers.child("esteRezervata3").setValue(true); // punem true cand user-ul este Online

            databaseReferenceUsers1.child("esteRezervata3").setValue(true); // punem true cand user-ul este Online
            databaseReferenceUsers1.child("interval3").setValue(m3);

            try {

                Date date3 = dateFormat.parse(m3); // luam timpul de pe checkBox

                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                Date dateCurrent3 = dateFormat.parse(currentTime);

                if (date3.before(dateCurrent3)) // comparam timpul din checkbox cu timpul curent
                {
                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
                }

            } catch (ParseException e) {

            }

        }

    }


    public void btnCancel(View v) {

        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3").child(nrMasa);
        DatabaseReference databaseReferenceUsers1 = mRootRef.child("rezervari3").child(nrMasa).child(userId);


        //   if(esteRezervata1_bd.equals("true") && esteRezervata1_user.equals("true"))
        {
            databaseReferenceUsers.child("esteRezervata1").setValue(false);
            databaseReferenceUsers1.child("esteRezervata1").setValue(false);
            databaseReferenceUsers1.child("interval1").setValue(null);
            //    Toast.makeText(this, "Rezervarea 1 anulata", Toast.LENGTH_SHORT).show();
        }

        //    if(esteRezervata2_bd.equals("true") && esteRezervata2_user.equals("true"))
        {
            databaseReferenceUsers.child("esteRezervata2").setValue(false);
            databaseReferenceUsers1.child("esteRezervata2").setValue(false);
            databaseReferenceUsers1.child("interval2").setValue(null);
            //     Toast.makeText(this, "Rezervarea 2 anulata", Toast.LENGTH_SHORT).show();
        }

        //     if(esteRezervata3_bd.equals("true") && esteRezervata3_user.equals("true"))
        {
            databaseReferenceUsers.child("esteRezervata3").setValue(false);
            databaseReferenceUsers1.child("esteRezervata3").setValue(false);
            databaseReferenceUsers1.child("interval3").setValue(null);
            //     Toast.makeText(this, "Rezervarea 3 anulata", Toast.LENGTH_SHORT).show();
        }


        Toast.makeText(this, "Ati anulat rezervarile facute", Toast.LENGTH_SHORT).show();


    }


    public void vizibilitateInreg() {
        if (esteRezervata1) {
            radio_one.setVisibility(View.GONE);
            //    n = radio_one;
        } else if (esteRezervata2) {
            radio_two.setVisibility(View.GONE);
            //    n = radio_one;
        } else if (esteRezervata3) {
            radio_three.setVisibility(View.GONE);
            //    n = radio_one;
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
        if (id == R.id.mnuProfile) {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void populatePersoanaAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.mese,
                android.R.layout.simple_spinner_dropdown_item);
        spnMasa.setAdapter(adapter);
    }

}
































//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.UserProfileChangeRequest;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ServerValue;
//import com.google.firebase.database.ValueEventListener;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
//import eu.ase.firebaseauthlicenta.profile.ProfileActivity;
//
//public class MainActivity4 extends AppCompatActivity {
//
//    private DatabaseReference mRootRef; // aici avem o referinta catre baza de date
//    private FirebaseAuth mAuth;
//    private String userId;
//
//    RadioButton radio_one, radio_two, radio_three;
//
//    boolean esteRezervata1 = false;
//    boolean esteRezervata2 = false;
//    boolean esteRezervata3 = false;
//
//
//    String esteRezervata1_bd = null;
//    String esteRezervata2_bd = null;
//    String esteRezervata3_bd = null;
//
//    String esteRezervata1_user = null;
//    String esteRezervata2_user = null;
//    String esteRezervata3_user = null;
//
//    String nrMasa;
//    Spinner spnMasa;
//
//
//    private int n = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main4);
//
//
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        userId = currentUser.getUid(); // in astea 3 linii am luat id-ul user-ului
//
//        mRootRef = FirebaseDatabase.getInstance().getReference();
//
//        radio_one = findViewById(R.id.rb_left);
//        radio_two = findViewById(R.id.rb_center);
//        radio_three = findViewById(R.id.rb_right);
//
//        spnMasa = findViewById(R.id.spn_mese);
//
//        vizibilitateInreg();
//
//        populatePersoanaAdapter();
//
//
//        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3");
//
//        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                nrMasa = spnMasa.getSelectedItem().toString();
//                DatabaseReference databaseReferenceUsers1 = mRootRef.child("rezervari3").child(nrMasa);
//
//                spnMasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//
////                        databaseReferenceUsers1.addValueEventListener(new ValueEventListener() {
////                            @Override
////                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                String status1 = null;
//                                String status2 = null;
//                                String status3 = null;
//                                if (dataSnapshot.child("esteRezervata1").getValue() != null) {
//                                    status1 = dataSnapshot.child("esteRezervata1").getValue().toString();
//                                    esteRezervata1_bd = dataSnapshot.child("esteRezervata1").getValue().toString();
//                                    //    esteRezervata1_user =  dataSnapshot.child(userId).child("esteRezervata1").getValue().toString();
//                                }
//
//
//                                // de aici incerc cu timpul
//
//
//                                String m1 = radio_one.getText().toString();
//                                String m2 = radio_two.getText().toString();
//                                String m3 = radio_three.getText().toString();
//
//
//                                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//
//                                try {
//
//                                    Date date1 = dateFormat.parse(m1); // luam timpul de pe checkBox
//
//                                    // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                                    Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                                    if (date1.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                                    {
//                                        radio_one.setClickable(false);
//                                        radio_one.setBackgroundColor(0xFFC62828);
//                                        databaseReferenceUsers1.child("esteRezervata1").setValue(true);
//                                    }
//
//
//                                } catch (ParseException e) {
//
//                                }
//
//                                // pana aici
//
//
//                                try {
//
//                                    Date date2 = dateFormat.parse(m2); // luam timpul de pe checkBox
//
//                                    // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                                    Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                                    if (date2.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                                    {
//                                        radio_one.setClickable(false);
//                                        radio_one.setBackgroundColor(0xFFC62828);
//                                        databaseReferenceUsers1.child("esteRezervata2").setValue(true);
//                                    }
//
//
//                                } catch (ParseException e) {
//
//                                }
//
//
//                                try {
//
//                                    Date date3 = dateFormat.parse(m3); // luam timpul de pe checkBox
//
//                                    // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                                    Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                                    if (date3.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                                    {
//                                        radio_one.setClickable(false);
//                                        radio_one.setBackgroundColor(0xFFC62828);
//                                        databaseReferenceUsers1.child("esteRezervata3").setValue(true);
//                                    }
//
//                                } catch (ParseException e) {
//
//                                }
//
//
//                                // daca timpul este 00:00 sa le facem pe toate false (disponibile)
//
//                                String midnightTime = "00:00";
//                                try {
//
//                                    Date date = dateFormat.parse(midnightTime); // luam timpul de pe checkBox
//
//                                    // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                                    Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                                    if (date.equals(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                                    {
//                                        radio_one.setClickable(true);
//                                        radio_one.setBackgroundResource(R.drawable.radio_selector);
//                                        databaseReferenceUsers1.child("esteRezervata1").setValue(false);
//
//                                        radio_two.setClickable(true);
//                                        radio_two.setBackgroundResource(R.drawable.radio_selector);
//                                        databaseReferenceUsers1.child("esteRezervata2").setValue(false);
//
//                                        radio_three.setClickable(true);
//                                        radio_three.setBackgroundResource(R.drawable.radio_selector);
//                                        databaseReferenceUsers1.child("esteRezervata3").setValue(false);
//
//                                        Toast.makeText(MainActivity4.this, "Se pot efectua rezervari dupa ora 00:00", Toast.LENGTH_SHORT).show();
//
//                                    }
//
//
//                                } catch (ParseException e) {
//
//                                }
//
//                                // pana aici
//
//
//                                if (status1.equals("true")) {
//                                    radio_one.setClickable(false);
//                                    radio_one.setBackgroundColor(0xFFC62828);
//                                }
//                                if (status1.equals("false")) {
//                                    radio_one.setClickable(true);
//                                    radio_one.setBackgroundResource(R.drawable.radio_selector);
//
//                                }
//
//
//                                if (dataSnapshot.child("esteRezervata2").getValue() != null) {
//                                    status2 = dataSnapshot.child("esteRezervata2").getValue().toString();
//                                    esteRezervata2_bd = dataSnapshot.child("esteRezervata2").getValue().toString();
//                                    //              esteRezervata2_user =  dataSnapshot.child(userId).child("esteRezervata2").getValue().toString();
//                                }
//
//                                if (status2.equals("true")) {
//                                    radio_two.setClickable(false);
//                                    radio_two.setBackgroundColor(0xFFC62828);
//                                }
//                                if (status2.equals("false")) {
//                                    radio_two.setClickable(true);
//                                    radio_two.setBackgroundResource(R.drawable.radio_selector);
//
//                                }
//
//
//                                if (dataSnapshot.child("esteRezervata3").getValue() != null) {
//                                    status3 = dataSnapshot.child("esteRezervata3").getValue().toString();
//                                    esteRezervata3_bd = dataSnapshot.child("esteRezervata3").getValue().toString();
//                                    //           esteRezervata3_user =  dataSnapshot.child(userId).child("esteRezervata3").getValue().toString();
//                                }
//                                if (status3.equals("true")) {
//                                    radio_three.setClickable(false);
//                                    radio_three.setBackgroundColor(0xFFC62828);
//                                }
//                                if (status3.equals("false")) {
//                                    radio_three.setClickable(true);
//                                    radio_three.setBackgroundResource(R.drawable.radio_selector);
//                                }
////                            }
////
////                            @Override
////                            public void onCancelled(@NonNull DatabaseError error) {
////
////                            }
////                        });
//
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                        Toast.makeText(MainActivity4.this, "Please select table", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//
//
//    public void btnApply1(View v) {
//        String m1 = radio_one.getText().toString();
//        String m2 = radio_two.getText().toString();
//        String m3 = radio_three.getText().toString();
//
//        // facem path pentru storage, unde vom salva datele
//        //  DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari1").child(userId);
//        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3").child(nrMasa);
//        DatabaseReference databaseReferenceUsers1 = mRootRef.child("rezervari3").child(nrMasa).child(userId);
//
//
//        // facem formatul timpului
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//
//
//        if (radio_one.isChecked()) {
//            // esteRezervata11 = true;
//
//            databaseReferenceUsers.child("esteRezervata1").setValue(true); // punem true cand user-ul este Online
//
//            databaseReferenceUsers1.child("esteRezervata1").setValue(true); // punem true cand user-ul este Online
//            databaseReferenceUsers1.child("interval1").setValue(m1);
//
//
//            try {
//
//                Date date1 = dateFormat.parse(m1); // luam timpul de pe checkBox
//
//                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                if (date1.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                {
//                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
//                }
//
//            } catch (ParseException e) {
//
//            }
//
//        } else if (radio_two.isChecked()) {
//
//            // esteRezervata22 = true;
//
//            databaseReferenceUsers.child("esteRezervata2").setValue(true); // punem true cand user-ul este Online
//
//            databaseReferenceUsers1.child("esteRezervata2").setValue(true); // punem true cand user-ul este Online
//            databaseReferenceUsers1.child("interval2").setValue(m2);
//
//            try {
//
//                Date date2 = dateFormat.parse(m2); // luam timpul de pe checkBox
//
//                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                Date dateCurrent2 = dateFormat.parse(currentTime);
//
//                if (date2.before(dateCurrent2)) // comparam timpul din checkbox cu timpul curent
//                {
//                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
//                }
//
//            } catch (ParseException e) {
//
//            }
//
//
//        } else if (radio_three.isChecked()) {
//            //  esteRezervata33 = true;
//
//            databaseReferenceUsers.child("esteRezervata3").setValue(true); // punem true cand user-ul este Online
//
//            databaseReferenceUsers1.child("esteRezervata3").setValue(true); // punem true cand user-ul este Online
//            databaseReferenceUsers1.child("interval3").setValue(m3);
//
//            try {
//
//                Date date3 = dateFormat.parse(m3); // luam timpul de pe checkBox
//
//                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                Date dateCurrent3 = dateFormat.parse(currentTime);
//
//                if (date3.before(dateCurrent3)) // comparam timpul din checkbox cu timpul curent
//                {
//                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
//                }
//
//            } catch (ParseException e) {
//
//            }
//
//        }
//
//    }
//
//
//    public void btnCancel(View v) {
//
//        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3").child(nrMasa);
//        DatabaseReference databaseReferenceUsers1 = mRootRef.child("rezervari3").child(nrMasa).child(userId);
//
//
//        //   if(esteRezervata1_bd.equals("true") && esteRezervata1_user.equals("true"))
//        {
//            databaseReferenceUsers.child("esteRezervata1").setValue(false);
//            databaseReferenceUsers1.child("esteRezervata1").setValue(false);
//            databaseReferenceUsers1.child("interval1").setValue(null);
//            //    Toast.makeText(this, "Rezervarea 1 anulata", Toast.LENGTH_SHORT).show();
//        }
//
//        //    if(esteRezervata2_bd.equals("true") && esteRezervata2_user.equals("true"))
//        {
//            databaseReferenceUsers.child("esteRezervata2").setValue(false);
//            databaseReferenceUsers1.child("esteRezervata2").setValue(false);
//            databaseReferenceUsers1.child("interval2").setValue(null);
//            //     Toast.makeText(this, "Rezervarea 2 anulata", Toast.LENGTH_SHORT).show();
//        }
//
//        //     if(esteRezervata3_bd.equals("true") && esteRezervata3_user.equals("true"))
//        {
//            databaseReferenceUsers.child("esteRezervata3").setValue(false);
//            databaseReferenceUsers1.child("esteRezervata3").setValue(false);
//            databaseReferenceUsers1.child("interval3").setValue(null);
//            //     Toast.makeText(this, "Rezervarea 3 anulata", Toast.LENGTH_SHORT).show();
//        }
//
//
//        Toast.makeText(this, "Ati anulat rezervarile facute", Toast.LENGTH_SHORT).show();
//
//
//    }
//
//
//    public void vizibilitateInreg() {
//        if (esteRezervata1) {
//            radio_one.setVisibility(View.GONE);
//            //    n = radio_one;
//        } else if (esteRezervata2) {
//            radio_two.setVisibility(View.GONE);
//            //    n = radio_one;
//        } else if (esteRezervata3) {
//            radio_three.setVisibility(View.GONE);
//            //    n = radio_one;
//        }
//
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) { // metoda este apelata cand activitatea este creata
//
//        getMenuInflater().inflate(R.menu.menu_main, menu); // inflater-ul ne va converti xml file-ul intr-un view vizibil
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // se va executa de fiecare data cand un user va da click pe orice din menu
//
//        int id = item.getItemId(); // luam id-ul item-ului selectat
//        if (id == R.id.mnuProfile) {
//            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void populatePersoanaAdapter() {
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
//                R.array.mese,
//                android.R.layout.simple_spinner_dropdown_item);
//        spnMasa.setAdapter(adapter);
//    }
//
//}






















//   import androidx.annotation.NonNull;
//        import androidx.annotation.Nullable;
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.util.Log;
//        import android.view.Menu;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.widget.AdapterView;
//        import android.widget.ArrayAdapter;
//        import android.widget.EditText;
//        import android.widget.RadioButton;
//        import android.widget.Spinner;
//        import android.widget.Toast;
//
//        import com.google.firebase.auth.FirebaseAuth;
//        import com.google.firebase.auth.FirebaseUser;
//        import com.google.firebase.auth.UserProfileChangeRequest;
//        import com.google.firebase.database.DataSnapshot;
//        import com.google.firebase.database.DatabaseError;
//        import com.google.firebase.database.DatabaseReference;
//        import com.google.firebase.database.FirebaseDatabase;
//        import com.google.firebase.database.ServerValue;
//        import com.google.firebase.database.ValueEventListener;
//
//        import java.text.ParseException;
//        import java.text.SimpleDateFormat;
//        import java.util.Date;
//        import java.util.HashMap;
//        import java.util.Locale;
//        import java.util.Map;
//
//        import eu.ase.firebaseauthlicenta.profile.ProfileActivity;
//
//public class MainActivity4 extends AppCompatActivity {
//
//    private DatabaseReference mRootRef; // aici avem o referinta catre baza de date
//    private FirebaseAuth mAuth;
//    private String userId;
//
//    RadioButton radio_one, radio_two, radio_three;
//
//    boolean esteRezervata1 = false;
//    boolean esteRezervata2 = false;
//    boolean esteRezervata3 = false;
//
//
//    String esteRezervata1_bd = null;
//    String esteRezervata2_bd = null;
//    String esteRezervata3_bd = null;
//
//    String esteRezervata1_user = null;
//    String esteRezervata2_user = null;
//    String esteRezervata3_user = null;
//
//    String nrMasa;
//    Spinner spnMasa;
//
//
//    private int n = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main4);
//
//
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        userId = currentUser.getUid(); // in astea 3 linii am luat id-ul user-ului
//
//        mRootRef = FirebaseDatabase.getInstance().getReference();
//
//        radio_one = findViewById(R.id.rb_left);
//        radio_two = findViewById(R.id.rb_center);
//        radio_three = findViewById(R.id.rb_right);
//
//        spnMasa = findViewById(R.id.spn_mese);
//
//        vizibilitateInreg();
//
//        populatePersoanaAdapter();
//
//
//        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3");
//
//        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
//
//                nrMasa = spnMasa.getSelectedItem().toString();
//                DatabaseReference databaseReferenceUsers1 = databaseReferenceUsers.child(nrMasa);
//
////                spnMasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                    @Override
////                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////
////                    }
////
////                    @Override
////                    public void onNothingSelected(AdapterView<?> adapterView) {
////
////                    }
////                });
//
//
//
//                databaseReferenceUsers1.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String status1 = null;
//                        String status2 = null;
//                        String status3 = null;
//                        if (dataSnapshot.child("esteRezervata1").getValue() != null) {
//                            status1 = dataSnapshot.child("esteRezervata1").getValue().toString();
//                            esteRezervata1_bd = dataSnapshot.child("esteRezervata1").getValue().toString();
//                            //    esteRezervata1_user =  dataSnapshot.child(userId).child("esteRezervata1").getValue().toString();
//                        }
//
//
//                        // de aici incerc cu timpul
//
//
//                        String m1 = radio_one.getText().toString();
//                        String m2 = radio_two.getText().toString();
//                        String m3 = radio_three.getText().toString();
//
//
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//
//                        try {
//
//                            Date date1 = dateFormat.parse(m1); // luam timpul de pe checkBox
//
//                            // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                            Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                            if (date1.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                            {
//                                radio_one.setClickable(false);
//                                radio_one.setBackgroundColor(0xFFC62828);
//                                databaseReferenceUsers1.child("esteRezervata1").setValue(true);
//                            }
//
//
//                        } catch (ParseException e) {
//
//                        }
//
//                        // pana aici
//
//
//                        try {
//
//                            Date date2 = dateFormat.parse(m2); // luam timpul de pe checkBox
//
//                            // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                            Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                            if (date2.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                            {
//                                radio_one.setClickable(false);
//                                radio_one.setBackgroundColor(0xFFC62828);
//                                databaseReferenceUsers1.child("esteRezervata2").setValue(true);
//                            }
//
//
//                        } catch (ParseException e) {
//
//                        }
//
//
//                        try {
//
//                            Date date3 = dateFormat.parse(m3); // luam timpul de pe checkBox
//
//                            // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                            Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                            if (date3.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                            {
//                                radio_one.setClickable(false);
//                                radio_one.setBackgroundColor(0xFFC62828);
//                                databaseReferenceUsers1.child("esteRezervata3").setValue(true);
//                            }
//
//                        } catch (ParseException e) {
//
//                        }
//
//
//                        // daca timpul este 00:00 sa le facem pe toate false (disponibile)
//
//                        String midnightTime = "00:00";
//                        try {
//
//                            Date date = dateFormat.parse(midnightTime); // luam timpul de pe checkBox
//
//                            // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                            Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                            if (date.equals(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                            {
//                                radio_one.setClickable(true);
//                                radio_one.setBackgroundResource(R.drawable.radio_selector);
//                                databaseReferenceUsers1.child("esteRezervata1").setValue(false);
//
//                                radio_two.setClickable(true);
//                                radio_two.setBackgroundResource(R.drawable.radio_selector);
//                                databaseReferenceUsers1.child("esteRezervata2").setValue(false);
//
//                                radio_three.setClickable(true);
//                                radio_three.setBackgroundResource(R.drawable.radio_selector);
//                                databaseReferenceUsers1.child("esteRezervata3").setValue(false);
//
//                                Toast.makeText(MainActivity4.this, "Se pot efectua rezervari dupa ora 00:00", Toast.LENGTH_SHORT).show();
//
//                            }
//
//
//                        } catch (ParseException e) {
//
//                        }
//
//                        // pana aici
//
//
//                        if (status1.equals("true")) {
//                            radio_one.setClickable(false);
//                            radio_one.setBackgroundColor(0xFFC62828);
//                        }
//                        if (status1.equals("false")) {
//                            radio_one.setClickable(true);
//                            radio_one.setBackgroundResource(R.drawable.radio_selector);
//
//                        }
//
//
//                        if (dataSnapshot.child("esteRezervata2").getValue() != null) {
//                            status2 = dataSnapshot.child("esteRezervata2").getValue().toString();
//                            esteRezervata2_bd = dataSnapshot.child("esteRezervata2").getValue().toString();
//                            //              esteRezervata2_user =  dataSnapshot.child(userId).child("esteRezervata2").getValue().toString();
//                        }
//
//                        if (status2.equals("true")) {
//                            radio_two.setClickable(false);
//                            radio_two.setBackgroundColor(0xFFC62828);
//                        }
//                        if (status2.equals("false")) {
//                            radio_two.setClickable(true);
//                            radio_two.setBackgroundResource(R.drawable.radio_selector);
//
//                        }
//
//
//                        if (dataSnapshot.child("esteRezervata3").getValue() != null) {
//                            status3 = dataSnapshot.child("esteRezervata3").getValue().toString();
//                            esteRezervata3_bd = dataSnapshot.child("esteRezervata3").getValue().toString();
//                            //           esteRezervata3_user =  dataSnapshot.child(userId).child("esteRezervata3").getValue().toString();
//                        }
//                        if (status3.equals("true")) {
//                            radio_three.setClickable(false);
//                            radio_three.setBackgroundColor(0xFFC62828);
//                        }
//                        if (status3.equals("false")) {
//                            radio_three.setClickable(true);
//                            radio_three.setBackgroundResource(R.drawable.radio_selector);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//
//
//    public void btnApply1(View v) {
//        String m1 = radio_one.getText().toString();
//        String m2 = radio_two.getText().toString();
//        String m3 = radio_three.getText().toString();
//
//        // facem path pentru storage, unde vom salva datele
//        String current_user_ref = "3/" + userId;
//        //  DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari1").child(userId);
//        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3").child(nrMasa);
//        DatabaseReference databaseReferenceUsers1 = mRootRef.child("rezervari3").child(nrMasa).child(userId);
//
//
//        // facem formatul timpului
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//
//
//        if (radio_one.isChecked()) {
//            // esteRezervata11 = true;
//
//            databaseReferenceUsers.child("esteRezervata1").setValue(true); // punem true cand user-ul este Online
//
//            databaseReferenceUsers1.child("esteRezervata1").setValue(true); // punem true cand user-ul este Online
//            databaseReferenceUsers1.child("interval1").setValue(m1);
//
//
//            try {
//
//                Date date1 = dateFormat.parse(m1); // luam timpul de pe checkBox
//
//                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                Date dateCurrent1 = dateFormat.parse(currentTime);
//
//                if (date1.before(dateCurrent1)) // comparam timpul din checkbox cu timpul curent
//                {
//                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
//                }
//
//            } catch (ParseException e) {
//
//            }
//
//        } else if (radio_two.isChecked()) {
//
//            // esteRezervata22 = true;
//
//            databaseReferenceUsers.child("esteRezervata2").setValue(true); // punem true cand user-ul este Online
//
//            databaseReferenceUsers1.child("esteRezervata2").setValue(true); // punem true cand user-ul este Online
//            databaseReferenceUsers1.child("interval2").setValue(m2);
//
//            try {
//
//                Date date2 = dateFormat.parse(m2); // luam timpul de pe checkBox
//
//                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                Date dateCurrent2 = dateFormat.parse(currentTime);
//
//                if (date2.before(dateCurrent2)) // comparam timpul din checkbox cu timpul curent
//                {
//                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
//                }
//
//            } catch (ParseException e) {
//
//            }
//
//
//        } else if (radio_three.isChecked()) {
//            //  esteRezervata33 = true;
//
//            databaseReferenceUsers.child("esteRezervata3").setValue(true); // punem true cand user-ul este Online
//
//            databaseReferenceUsers1.child("esteRezervata3").setValue(true); // punem true cand user-ul este Online
//            databaseReferenceUsers1.child("interval3").setValue(m3);
//
//            try {
//
//                Date date3 = dateFormat.parse(m3); // luam timpul de pe checkBox
//
//                // luam timpul curent, l-am pus in check deoarece timpul curent se poate schimba pana alegem alta optiune, asa il va lua mereu cand alegem o optiune
//                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                Date dateCurrent3 = dateFormat.parse(currentTime);
//
//                if (date3.before(dateCurrent3)) // comparam timpul din checkbox cu timpul curent
//                {
//                    Toast.makeText(this, "Rezervarea dumneavoastra a trecut", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Rezervarea dumneavoastra este valabila", Toast.LENGTH_SHORT).show();
//                }
//
//            } catch (ParseException e) {
//
//            }
//
//        }
//
//    }
//
//
//    public void btnCancel(View v) {
//
//        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari3").child(nrMasa);
//        DatabaseReference databaseReferenceUsers1 = mRootRef.child("rezervari3").child(nrMasa).child(userId);
//
//
//        //   if(esteRezervata1_bd.equals("true") && esteRezervata1_user.equals("true"))
//        {
//            databaseReferenceUsers.child("esteRezervata1").setValue(false);
//            databaseReferenceUsers1.child("esteRezervata1").setValue(false);
//            databaseReferenceUsers1.child("interval1").setValue(null);
//            //    Toast.makeText(this, "Rezervarea 1 anulata", Toast.LENGTH_SHORT).show();
//        }
//
//        //    if(esteRezervata2_bd.equals("true") && esteRezervata2_user.equals("true"))
//        {
//            databaseReferenceUsers.child("esteRezervata2").setValue(false);
//            databaseReferenceUsers1.child("esteRezervata2").setValue(false);
//            databaseReferenceUsers1.child("interval2").setValue(null);
//            //     Toast.makeText(this, "Rezervarea 2 anulata", Toast.LENGTH_SHORT).show();
//        }
//
//        //     if(esteRezervata3_bd.equals("true") && esteRezervata3_user.equals("true"))
//        {
//            databaseReferenceUsers.child("esteRezervata3").setValue(false);
//            databaseReferenceUsers1.child("esteRezervata3").setValue(false);
//            databaseReferenceUsers1.child("interval3").setValue(null);
//            //     Toast.makeText(this, "Rezervarea 3 anulata", Toast.LENGTH_SHORT).show();
//        }
//
//
//        Toast.makeText(this, "Ati anulat rezervarile facute", Toast.LENGTH_SHORT).show();
//
//
//    }
//
//
//    public void vizibilitateInreg() {
//        if (esteRezervata1) {
//            radio_one.setVisibility(View.GONE);
//            //    n = radio_one;
//        } else if (esteRezervata2) {
//            radio_two.setVisibility(View.GONE);
//            //    n = radio_one;
//        } else if (esteRezervata3) {
//            radio_three.setVisibility(View.GONE);
//            //    n = radio_one;
//        }
//
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) { // metoda este apelata cand activitatea este creata
//
//        getMenuInflater().inflate(R.menu.menu_main, menu); // inflater-ul ne va converti xml file-ul intr-un view vizibil
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // se va executa de fiecare data cand un user va da click pe orice din menu
//
//        int id = item.getItemId(); // luam id-ul item-ului selectat
//        if (id == R.id.mnuProfile) {
//            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void populatePersoanaAdapter() {
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
//                R.array.mese,
//                android.R.layout.simple_spinner_dropdown_item);
//        spnMasa.setAdapter(adapter);
//    }
//
//}



