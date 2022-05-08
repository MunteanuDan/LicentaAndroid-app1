
package eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa3.KlandestinBurgeri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa2KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa3KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.NrMasaComenzi.Klandestin.Masa2MenuKlandestin;
import eu.ase.firebaseauthlicenta.order.firebase.FirebaseService;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa2.KlandestinBurgeri.KlandestinBurgerBlackAngusSiCartofiFryNDip2Activity;
import eu.ase.firebaseauthlicenta.order.menu.KlandestinMenu.Masa3.KlandestinBurgeri3Activity;
import eu.ase.firebaseauthlicenta.order.util.Callback;
import eu.ase.firebaseauthlicenta.order.util.Comanda;

public class KlandestinBurgerBlackAngusSiCartofiFryNDip3Activity extends AppCompatActivity {

    private TextView tvDenumire, tvCantitate, tvPret;
    private ImageView imgAddToCart, decrement, increment, ivCart, back;
    private int count = 1;
    private int p1;
    private List<Comanda> Comandas = new ArrayList<>();

    private int selectedComandaIndex = -1;

    private FirebaseService firebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klandestin_burger_black_angus_si_cartofi_fry_n_dip);

        initComponents();
        firebaseService = FirebaseService.getInstance();
        firebaseService.attachDataChangeEventListener3Klandestin(dataChangeCallback());


        incrementMethod();

        decrementMethod();
    }

    private Callback<List<Comanda>> dataChangeCallback() {
        return new Callback<List<Comanda>>() {
            @Override
            public void runResultOnUiThread(List<Comanda> results) {
                if (results != null) {
                    //code apelat din FirebaseService in metoda onDataChange
                    Comandas.clear();
                    Comandas.addAll(results);
                }
            }
        };
    }

    private void initComponents() {
        tvDenumire = findViewById(R.id.tv_klandestin_burger_black_angus_si_cartofi_fry_n_dip);
        imgAddToCart = findViewById(R.id.img_add_to_cart);
        tvCantitate = findViewById(R.id.tv_cantitate);
        tvPret = findViewById(R.id.tv_pret);
        decrement = findViewById(R.id.minus_iv);
        increment = findViewById(R.id.plus_iv);
        ivCart = findViewById(R.id.iv_cart);
        back = findViewById(R.id.img_back);
        p1 = Integer.parseInt(tvPret.getText().toString());
        imgAddToCart.setOnClickListener(getSaveComandaClickEventAlert());

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
                Intent intent = new Intent(getApplicationContext(), KlandestinBurgeri3Activity.class);
                startActivity(intent);
            }
        });

    }

    private View.OnClickListener getSaveComandaClickEventAlert() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(KlandestinBurgerBlackAngusSiCartofiFryNDip3Activity.this)
                        .setTitle("Adaugare comanda")
                        .setMessage("Sigur doriti comanda selectata?")
                        .setPositiveButton("Yes", (DialogInterface.OnClickListener) getSaveComandaClickEventPositiveEvent())
                        .setNegativeButton("No", getSaveComandaClickEventNegativeEvent());
                build.create().show();

            }
        };
    }

    private DialogInterface.OnClickListener getSaveComandaClickEventPositiveEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (selectedComandaIndex == -1) {
                    Comanda Comanda = updateComandaFromView(null);
                    firebaseService.insertMasa3Klandestin(Comanda);
                } else {
                    Comanda Comanda = updateComandaFromView(Comandas.get(selectedComandaIndex).getId());
                    firebaseService.updateMasa3Klandestin(Comanda);
                }

                Intent intent = new Intent(getApplicationContext(), ListaComenziMasa3KlandestinActivity.class);
                startActivity(intent);
            }
        };

    }


    private DialogInterface.OnClickListener getSaveComandaClickEventNegativeEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Utilizatorul a renuntat la comanda selectata",
                        Toast.LENGTH_SHORT).show();
            }
        };
    }



    private Comanda updateComandaFromView(String id) {
        Comanda Comanda = new Comanda();
        Comanda.setId(id);
        Comanda.setDenumire(tvDenumire.getText().toString());
        Comanda.setCantitate(tvCantitate.getText().toString());
        Comanda.setPret(tvPret.getText().toString());
        return Comanda;

    }

    private void incrementMethod() {

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                tvCantitate.setText("" + count);
                tvPret.setText("" + p1 * count);
            }
        });
    }

    private void decrementMethod() {

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 1) {
                    count = 1;
                } else {
                    count--;
                }
                tvCantitate.setText("" + count);
                tvPret.setText("" + p1 * count);
            }
        });
    }
}