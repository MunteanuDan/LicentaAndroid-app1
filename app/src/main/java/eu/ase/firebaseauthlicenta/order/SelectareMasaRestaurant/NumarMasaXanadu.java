package eu.ase.firebaseauthlicenta.order.SelectareMasaRestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa1MenuXanadu;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa2MenuXanadu;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Xanadu.Masa3MenuXanadu;

public class NumarMasaXanadu extends AppCompatActivity {

    private AnimationDrawable anim, anim2, anim3;
    private Button btn_masa_1, btn_masa_2, btn_masa_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numar_masa_xanadu);

        initComponents();

    }

    private void initComponents() {

        btn_masa_1 = findViewById(R.id.btnOrderMasa1);
        btn_masa_2 = findViewById(R.id.btnOrderMasa2);
        btn_masa_3 = findViewById(R.id.btnOrderMasa3);

        anim = (AnimationDrawable) btn_masa_1.getBackground();
        anim.setEnterFadeDuration(2300);
        anim.setExitFadeDuration(2300);

        anim2 = (AnimationDrawable) btn_masa_2.getBackground();
        anim2.setEnterFadeDuration(2800);
        anim2.setExitFadeDuration(2800);

        anim3 = (AnimationDrawable) btn_masa_3.getBackground();
        anim3.setEnterFadeDuration(2700);
        anim3.setExitFadeDuration(2700);

    }

    public void btnMasa1 (View v)
    {
        Intent intent = new Intent(NumarMasaXanadu.this, Masa1MenuXanadu.class);
        startActivity(intent);
    }

    public void btnMasa2 (View v)
    {
        Intent intent = new Intent(NumarMasaXanadu.this, Masa2MenuXanadu.class);
        startActivity(intent);
    }

    public void btnMasa3 (View v)
    {
        Intent intent = new Intent(NumarMasaXanadu.this, Masa3MenuXanadu.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (anim != null && !anim.isRunning()) {
            anim.start();
        }

        if (anim2 != null && !anim2.isRunning()) {
            anim2.start();
        }

        if (anim3 != null && !anim3.isRunning()) {
            anim3.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (anim != null && anim.isRunning()) {
            anim.stop();
        }

        if (anim2 != null && anim2.isRunning()) {
            anim2.stop();
        }

        if (anim3 != null && anim3.isRunning()) {
            anim3.stop();
        }

    }

}