package eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa2XanaduActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa3XanaduActivity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSalate3Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSpaghete3Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSupe3Activity;

public class Masa3MenuXanadu extends AppCompatActivity {

    private ImageView spaghete_iv, salata_iv, supa_iv, cart_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa3_menu_xanadu);

        spaghete_iv = findViewById(R.id.Spaghete_iv);
        salata_iv = findViewById(R.id.Salata_iv);
        supa_iv = findViewById(R.id.Supa_iv);
        cart_iv = findViewById(R.id.iv_cart);


        spaghete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa3MenuXanadu.this, XanaduSpaghete3Activity.class);
                startActivity(intent);
            }
        });

        salata_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa3MenuXanadu.this, XanaduSalate3Activity.class);
                startActivity(intent);
            }
        });

        supa_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa3MenuXanadu.this, XanaduSupe3Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Masa3MenuXanadu.this, ListaComenziMasa3XanaduActivity.class);
                startActivity(intent);
            }
        });

    }
}