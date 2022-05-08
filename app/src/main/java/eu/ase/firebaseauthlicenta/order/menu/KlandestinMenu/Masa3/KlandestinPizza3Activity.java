package eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa1KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa3KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Klandestin.Masa1MenuKlandestin;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Klandestin.Masa3MenuKlandestin;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa3.KlandestinPizza.KlandestinPizzaCapricciosa3Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa3.KlandestinPizza.KlandestinPizzaMargherita3Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa3.KlandestinPizza.KlandestinPizzaVeggie3Activity;

public class KlandestinPizza3Activity extends AppCompatActivity {

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
                Intent intent = new Intent(KlandestinPizza3Activity.this, KlandestinPizzaCapricciosa3Activity.class);
                startActivity(intent);
            }
        });

        klandestin_pizza_margherita_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinPizza3Activity.this, KlandestinPizzaMargherita3Activity.class);
                startActivity(intent);
            }
        });

        klandestin_pizza_veggie_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinPizza3Activity.this, KlandestinPizzaVeggie3Activity.class);
                startActivity(intent);
            }
        });

        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaComenziMasa3KlandestinActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Masa3MenuKlandestin.class);
                startActivity(intent);
            }
        });
    }
}