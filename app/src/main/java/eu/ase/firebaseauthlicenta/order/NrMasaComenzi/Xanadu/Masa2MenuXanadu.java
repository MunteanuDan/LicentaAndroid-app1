package eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa1XanaduActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa2XanaduActivity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSalate1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSpaghete1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSupe1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSalate2Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSpaghete2Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSupe2Activity;

public class Masa2MenuXanadu extends AppCompatActivity {

    private ImageView spaghete_iv, salata_iv, supa_iv, cart_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa2_menu_xanadu);

        spaghete_iv = findViewById(R.id.Spaghete_iv);
        salata_iv = findViewById(R.id.Salata_iv);
        supa_iv = findViewById(R.id.Supa_iv);
        cart_iv = findViewById(R.id.iv_cart);


        spaghete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa2MenuXanadu.this, XanaduSpaghete2Activity.class);
                startActivity(intent);
            }
        });

        salata_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa2MenuXanadu.this, XanaduSalate2Activity.class);
                startActivity(intent);
            }
        });

        supa_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa2MenuXanadu.this, XanaduSupe2Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Masa2MenuXanadu.this, ListaComenziMasa2XanaduActivity.class);
                startActivity(intent);
            }
        });

    }
}