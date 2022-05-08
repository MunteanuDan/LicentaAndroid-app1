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
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa3MenuXanadu;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSpaghete.XanaduPenneQuatroFormaggi3Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSpaghete.XanaduSpagheteBolognese3Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSpaghete.XanaduSpagheteCarbonara3Activity;

public class XanaduSpaghete3Activity extends AppCompatActivity {

    private ImageView spaghete_carbonara_iv, spaghete_bolognese_iv, penne_quatro_formaggi_iv, cart_iv, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xanadu_spaghete);

        spaghete_carbonara_iv = findViewById(R.id.Spaghete_carbonara_iv);
        spaghete_bolognese_iv = findViewById(R.id.Spaghete_bolognese_iv);
        penne_quatro_formaggi_iv = findViewById(R.id.Penne_quatro_formaggi_iv);
        back = findViewById(R.id.img_back);
        cart_iv = findViewById(R.id.iv_cart);

        spaghete_carbonara_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSpaghete3Activity.this, XanaduSpagheteCarbonara3Activity.class);
                startActivity(intent);
            }
        });

        spaghete_bolognese_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSpaghete3Activity.this, XanaduSpagheteBolognese3Activity.class);
                startActivity(intent);
            }
        });

        penne_quatro_formaggi_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSpaghete3Activity.this, XanaduPenneQuatroFormaggi3Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XanaduSpaghete3Activity.this, ListaComenziMasa3XanaduActivity.class);
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