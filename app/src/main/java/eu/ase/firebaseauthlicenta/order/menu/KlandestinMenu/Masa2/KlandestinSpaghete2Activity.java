package eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa1KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa2KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Klandestin.Masa1MenuKlandestin;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Klandestin.Masa2MenuKlandestin;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinSpaghete.KlandestinPennePollo2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinSpaghete.KlandestinSpagheteCarbonara2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinSpaghete.KlandestinSpagheteFormagiAndPere2Activity;

public class KlandestinSpaghete2Activity extends AppCompatActivity {

    private ImageView klandestin_spaghete_carbonara_iv, klandestin_spaghete_formagi_and_pere_iv, klandestin_penne_pollo_iv, ivCart, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klandestin_spaghete);

        klandestin_spaghete_carbonara_iv = findViewById(R.id.klandestin_spaghete_carbonara_iv);
        klandestin_spaghete_formagi_and_pere_iv = findViewById(R.id.klandestin_spaghete_formagi_and_pere_iv);
        klandestin_penne_pollo_iv = findViewById(R.id.klandestin_penne_pollo_iv);
        back = findViewById(R.id.img_back);
        ivCart = findViewById(R.id.iv_cart);

        klandestin_spaghete_carbonara_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinSpaghete2Activity.this, KlandestinSpagheteCarbonara2Activity.class);
                startActivity(intent);
            }
        });

        klandestin_spaghete_formagi_and_pere_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinSpaghete2Activity.this, KlandestinSpagheteFormagiAndPere2Activity.class);
                startActivity(intent);
            }
        });

        klandestin_penne_pollo_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinSpaghete2Activity.this, KlandestinPennePollo2Activity.class);
                startActivity(intent);
            }
        });

        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaComenziMasa2KlandestinActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Masa2MenuKlandestin.class);
                startActivity(intent);
            }
        });
    }
}