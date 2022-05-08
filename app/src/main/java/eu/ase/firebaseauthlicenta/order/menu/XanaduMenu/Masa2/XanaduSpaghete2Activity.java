package eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa1XanaduActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu.ListaComenziMasa2XanaduActivity;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa2MenuXanadu;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSpaghete.XanaduPenneQuatroFormaggi1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSpaghete.XanaduSpagheteBolognese1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSpaghete.XanaduSpagheteCarbonara1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSpaghete1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSpaghete.XanaduPenneQuatroFormaggi2Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSpaghete.XanaduSpagheteBolognese2Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSpaghete.XanaduSpagheteCarbonara2Activity;

public class XanaduSpaghete2Activity extends AppCompatActivity {

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
                Intent intent = new Intent(XanaduSpaghete2Activity.this, XanaduSpagheteCarbonara2Activity.class);
                startActivity(intent);
            }
        });

        spaghete_bolognese_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSpaghete2Activity.this, XanaduSpagheteBolognese2Activity.class);
                startActivity(intent);
            }
        });

        penne_quatro_formaggi_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSpaghete2Activity.this, XanaduPenneQuatroFormaggi2Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XanaduSpaghete2Activity.this, ListaComenziMasa2XanaduActivity.class);
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