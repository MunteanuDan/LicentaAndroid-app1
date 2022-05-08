package eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa1XanaduActivity;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Klandestin.Masa1MenuKlandestin;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa1MenuXanadu;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate.XanaduSalataCaesar1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate.XanaduSalataMediteraneana1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate.XanaduSalataMexicana1Activity;

public class XanaduSalate1Activity extends AppCompatActivity {

    private ImageView salata_caesar_iv, salata_mediteraneana_iv, salata_mexicana_iv, cart_iv, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xanadu_salate);

        salata_caesar_iv = findViewById(R.id.Salata_caesar_iv);
        salata_mediteraneana_iv = findViewById(R.id.Salata_mediteraneana_iv);
        salata_mexicana_iv = findViewById(R.id.Salata_mexicana_iv);
        back = findViewById(R.id.img_back);
        cart_iv = findViewById(R.id.iv_cart);

        salata_caesar_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSalate1Activity.this, XanaduSalataCaesar1Activity.class);
                startActivity(intent);
            }
        });

        salata_mediteraneana_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSalate1Activity.this, XanaduSalataMediteraneana1Activity.class);
                startActivity(intent);
            }
        });

        salata_mexicana_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSalate1Activity.this, XanaduSalataMexicana1Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XanaduSalate1Activity.this, ListaComenziMasa1XanaduActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Masa1MenuXanadu.class);
                startActivity(intent);
            }
        });

    }
}