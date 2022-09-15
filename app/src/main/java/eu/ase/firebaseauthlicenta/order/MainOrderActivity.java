package eu.ase.firebaseauthlicenta.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.SelectareMasaRestaurant.NumarMasaKlandestin;
import eu.ase.firebaseauthlicenta.order.SelectareMasaRestaurant.NumarMasaXanadu;

public class MainOrderActivity extends AppCompatActivity {

    private ImageView xanadu_iv, klandestin_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_order);

        xanadu_iv = findViewById(R.id.Xanadu_iv);
        klandestin_iv = findViewById(R.id.Klandestin_iv);

        xanadu_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainOrderActivity.this, NumarMasaXanadu.class);
                startActivity(intent);
            }
        });

        klandestin_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainOrderActivity.this, NumarMasaKlandestin.class);
                startActivity(intent);
            }
        });

    }
}