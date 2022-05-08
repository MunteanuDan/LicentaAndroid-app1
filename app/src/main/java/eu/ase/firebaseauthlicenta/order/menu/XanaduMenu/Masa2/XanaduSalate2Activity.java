package eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa1XanaduActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa2XanaduActivity;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa1MenuXanadu;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa2MenuXanadu;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate.XanaduSalataCaesar1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate.XanaduSalataMediteraneana1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate.XanaduSalataMexicana1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSpaghete1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSalate.XanaduSalataCaesar2Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSalate.XanaduSalataMediteraneana2Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSalate.XanaduSalataMexicana2Activity;

public class XanaduSalate2Activity extends AppCompatActivity {

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
                Intent intent = new Intent(XanaduSalate2Activity.this, XanaduSalataCaesar2Activity.class);
                startActivity(intent);
            }
        });

        salata_mediteraneana_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSalate2Activity.this, XanaduSalataMediteraneana2Activity.class);
                startActivity(intent);
            }
        });

        salata_mexicana_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSalate2Activity.this, XanaduSalataMexicana2Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XanaduSalate2Activity.this, ListaComenziMasa2XanaduActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Masa2MenuXanadu.class);
                startActivity(intent);
            }
        });
    }
}