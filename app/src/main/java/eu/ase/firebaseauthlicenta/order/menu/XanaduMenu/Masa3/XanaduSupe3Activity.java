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
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSupe.XanaduCiorbaDeFasoleCuAfumatura3Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSupe.XanaduCiorbaDeVacuta3Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa3.XanaduSupe.XanaduSupaDePuiCuGaluste3Activity;

public class XanaduSupe3Activity extends AppCompatActivity {

    private ImageView supa_de_put_cu_galuste_iv, ciorba_de_fasole_cu_afumatura_iv, ciorba_de_vacuta_iv, cart_iv, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xanadu_supe);

        supa_de_put_cu_galuste_iv = findViewById(R.id.Supa_de_pui_cu_galuste_iv);
        ciorba_de_fasole_cu_afumatura_iv = findViewById(R.id.Ciorba_de_fasole_cu_afumatura_iv);
        ciorba_de_vacuta_iv = findViewById(R.id.Ciorba_de_vacuta_iv);
        back = findViewById(R.id.img_back);
        cart_iv = findViewById(R.id.iv_cart);

        supa_de_put_cu_galuste_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSupe3Activity.this, XanaduSupaDePuiCuGaluste3Activity.class);
                startActivity(intent);
            }
        });

        ciorba_de_fasole_cu_afumatura_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSupe3Activity.this, XanaduCiorbaDeFasoleCuAfumatura3Activity.class);
                startActivity(intent);
            }
        });

        ciorba_de_vacuta_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSupe3Activity.this, XanaduCiorbaDeVacuta3Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XanaduSupe3Activity.this, ListaComenziMasa3XanaduActivity.class);
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