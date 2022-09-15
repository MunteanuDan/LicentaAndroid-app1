package eu.ase.firebaseauthlicenta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private FloatingActionButton navigation_calendar_btn1;
    private FloatingActionButton navigation_calendar_btn2;

    private DatabaseReference mRootRef; // aici avem o referinta catre baza de date
    private FirebaseAuth mAuth;
    private String userId;
    public static String selectedDate;
    private Date d1;

    private Button button;
    private CalendarView calendarView;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        navigation_calendar_btn1 = findViewById(R.id.fab_calendar_car);
        navigation_calendar_btn2 = findViewById(R.id.fab_calendar_walk);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid(); // in astea 3 linii am luat id-ul user-ului

        mRootRef = FirebaseDatabase.getInstance().getReference();

        button = findViewById(R.id.btn_add_date);
        calendarView = findViewById(R.id.calendarView);



        DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari4");

        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                        selectedDate = Integer.toString(year)+ "/" + Integer.toString(month + 1)+ "/" + Integer.toString(dayOfMonth);
                    }
                });

                calendarView.setMinDate(System.currentTimeMillis() - 1000);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        navigation_calendar_btn1.setOnClickListener(new View.OnClickListener() {
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

        navigation_calendar_btn2.setOnClickListener(new View.OnClickListener() {
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


    public void btn1 (View v)
    {

   //     DatabaseReference databaseReferenceUsers = mRootRef.child("rezervari4").child(selectedDate);

        Intent intent = new Intent(getApplicationContext(), MeseActivity.class);
        startActivity(intent);

    }

}