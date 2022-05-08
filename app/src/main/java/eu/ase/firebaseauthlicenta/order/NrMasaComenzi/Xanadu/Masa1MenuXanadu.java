package eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa1XanaduActivity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSpaghete1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSupe1Activity;

public class Masa1MenuXanadu extends AppCompatActivity {

    private ImageView spaghete_iv, salata_iv, supa_iv, cart_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa1_menu_xanadu);

        spaghete_iv = findViewById(R.id.Spaghete_iv);
        salata_iv = findViewById(R.id.Salata_iv);
        supa_iv = findViewById(R.id.Supa_iv);
        cart_iv = findViewById(R.id.iv_cart);


        spaghete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa1MenuXanadu.this, XanaduSpaghete1Activity.class);
                startActivity(intent);
            }
        });

        salata_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa1MenuXanadu.this, XanaduSalate1Activity.class);
                startActivity(intent);
            }
        });

        supa_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa1MenuXanadu.this, XanaduSupe1Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Masa1MenuXanadu.this, ListaComenziMasa1XanaduActivity.class);
                startActivity(intent);
            }
        });

    }
}