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
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinBurgeri.KlandestinBurgerBlackAngusSiCartofiFryNDip2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinBurgeri.KlandestinCheeseburgerBlackAngusSiCartofiFryNDip2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinBurgeri.KlandestinChickenBurgerAndCartofiFryNDip2Activity;

public class KlandestinBurgeri2Activity extends AppCompatActivity {

    private ImageView klandestin_burger_black_angus_iv, klandestin_cheeseburger_black_angus_iv, klandestin_chicken_burger_iv, ivCart, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klandestin_burgeri);

        klandestin_burger_black_angus_iv = findViewById(R.id.klandestin_burger_black_angus_iv);
        klandestin_cheeseburger_black_angus_iv = findViewById(R.id.klandestin_cheeseburger_black_angus_iv);
        klandestin_chicken_burger_iv = findViewById(R.id.klandestin_chicken_burger_iv);
        back = findViewById(R.id.img_back);
        ivCart = findViewById(R.id.iv_cart);

        klandestin_burger_black_angus_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinBurgeri2Activity.this, KlandestinBurgerBlackAngusSiCartofiFryNDip2Activity.class);
                startActivity(intent);
            }
        });

        klandestin_cheeseburger_black_angus_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinBurgeri2Activity.this, KlandestinCheeseburgerBlackAngusSiCartofiFryNDip2Activity.class);
                startActivity(intent);
            }
        });

        klandestin_chicken_burger_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KlandestinBurgeri2Activity.this, KlandestinChickenBurgerAndCartofiFryNDip2Activity.class);
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