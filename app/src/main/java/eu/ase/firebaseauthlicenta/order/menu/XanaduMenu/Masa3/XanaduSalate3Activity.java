package eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa1XanaduActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa3XanaduActivity;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa1MenuXanadu;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa2MenuXanadu;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa3MenuXanadu;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSalate.XanaduSalataCaesar3Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSalate.XanaduSalataMediteraneana3Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSalate.XanaduSalataMexicana3Activity;

public class XanaduSalate3Activity extends AppCompatActivity {

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
                Intent intent = new Intent(XanaduSalate3Activity.this, XanaduSalataCaesar3Activity.class);
                startActivity(intent);
            }
        });

        salata_mediteraneana_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSalate3Activity.this, XanaduSalataMediteraneana3Activity.class);
                startActivity(intent);
            }
        });

        salata_mexicana_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSalate3Activity.this, XanaduSalataMexicana3Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XanaduSalate3Activity.this, ListaComenziMasa3XanaduActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Masa3MenuXanadu.class);
                startActivity(intent);
            }
        });

    }
}