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
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSpaghete1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSupe.XanaduCiorbaDeFasoleCuAfumatura1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSupe.XanaduCiorbaDeVacuta1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSupe.XanaduSupaDePuiCuGaluste1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa1.XanaduSupe1Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSupe.XanaduCiorbaDeFasoleCuAfumatura2Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSupe.XanaduCiorbaDeVacuta2Activity;
import eu.ase.firebaseauthlicenta.order.menu.XanaduMenu.Masa2.XanaduSupe.XanaduSupaDePuiCuGaluste2Activity;

public class XanaduSupe2Activity extends AppCompatActivity {

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
                Intent intent = new Intent(XanaduSupe2Activity.this, XanaduSupaDePuiCuGaluste2Activity.class);
                startActivity(intent);
            }
        });

        ciorba_de_fasole_cu_afumatura_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSupe2Activity.this, XanaduCiorbaDeFasoleCuAfumatura2Activity.class);
                startActivity(intent);
            }
        });

        ciorba_de_vacuta_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XanaduSupe2Activity.this, XanaduCiorbaDeVacuta2Activity.class);
                startActivity(intent);
            }
        });

        cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XanaduSupe2Activity.this, ListaComenziMasa2XanaduActivity.class);
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