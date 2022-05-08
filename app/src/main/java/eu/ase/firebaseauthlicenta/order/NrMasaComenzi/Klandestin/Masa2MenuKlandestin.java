package eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Klandestin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa2KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa3KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinBurgeri2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinPizza2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinSpaghete2Activity;

public class Masa2MenuKlandestin extends AppCompatActivity {

    private ImageView paste_iv, burger_iv, pizza_iv, ivCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa2_menu_klandestin);

        paste_iv = findViewById(R.id.Paste_iv);
        burger_iv = findViewById(R.id.Burger_iv);
        pizza_iv = findViewById(R.id.Pizza_iv);
        ivCart = findViewById(R.id.iv_cart);


        paste_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa2MenuKlandestin.this, KlandestinSpaghete2Activity.class);
                startActivity(intent);
            }
        });

        burger_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa2MenuKlandestin.this, KlandestinBurgeri2Activity.class);
                startActivity(intent);
            }
        });

        pizza_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Masa2MenuKlandestin.this, KlandestinPizza2Activity.class);
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
    }
}