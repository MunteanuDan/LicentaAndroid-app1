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
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinPizza.KlandestinPizzaCapricciosa2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinPizza.KlandestinPizzaMargherita2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinPizza.KlandestinPizzaVeggie2Activity;

public class KlandestinPizza2Activity extends AppCompatActivity {

    private ImageView klandestin_pizza_capricciosa_iv, klandestin_pizza_margherita_iv, klandestin_pizza_veggie_iv, ivCart, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klandestin_pizza);

        klandestin_pizza_capricciosa_iv = findViewById(R.id.klandestin_pizza_capricciosa_iv);
        klandestin_pizza_margherita_iv = findViewById(R.id.klandestin_pizza_margherita_iv);
        klandestin_pizza_veggie_iv = findViewById(R.id.klandestin_pizza_veggie_iv);
        back = findViewById(R.id.img_back);
        ivCart = findViewById(R.id.iv_cart);

        klandestin_pizza_capricciosa_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinPizza2Activity.this, KlandestinPizzaCapricciosa2Activity.class);
                startActivity(intent);
            }
        });

        klandestin_pizza_margherita_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinPizza2Activity.this, KlandestinPizzaMargherita2Activity.class);
                startActivity(intent);
            }
        });

        klandestin_pizza_veggie_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinPizza2Activity.this, KlandestinPizzaVeggie2Activity.class);
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