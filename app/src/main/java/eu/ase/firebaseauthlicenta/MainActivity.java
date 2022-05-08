package eu.ase.firebaseauthlicenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import eu.ase.firebaseauthlicenta.order.MainOrderActivity;


public class MainActivity extends AppCompatActivity {

    ImageView accountSettings;
    Button book_btn, order_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book_btn = findViewById(R.id.Book_btn);
        order_btn = findViewById(R.id.Order_btn);

        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}