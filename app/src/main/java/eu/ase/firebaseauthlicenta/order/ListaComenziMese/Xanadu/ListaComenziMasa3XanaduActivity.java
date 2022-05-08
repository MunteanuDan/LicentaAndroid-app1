package eu.ase.firebaseauthlicenta.order.ListaComenziMese.Xanadu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.order.ComandaFinalizataActivity;
import eu.ase.firebaseauthlicenta.order.ListaComenziMese.Klandestin.ListaComenziMasa3KlandestinActivity;
import eu.ase.firebaseauthlicenta.order.firebase.FirebaseService;
import eu.ase.firebaseauthlicenta.order.util.Callback;
import eu.ase.firebaseauthlicenta.order.util.Comanda;
import eu.ase.firebaseauthlicenta.order.util.ComandaAdapter;

import static eu.ase.firebaseauthlicenta.order.firebase.FirebaseService.referenceComenziMasa3Klandestin;
import static eu.ase.firebaseauthlicenta.order.firebase.FirebaseService.referenceComenziMasa3SumaKlandestin;
import static eu.ase.firebaseauthlicenta.order.firebase.FirebaseService.referenceComenziMasa3Xanadu;
import static eu.ase.firebaseauthlicenta.order.firebase.FirebaseService.referenceComenziMasa3SumaXanadu;

public class ListaComenziMasa3XanaduActivity extends AppCompatActivity {

    private List<Comanda> Comandas3 = new ArrayList<>();
    private ListView lvComandas3;
    private FirebaseService firebaseService;
    private TextView tvPret;
    private int s3 = 0;
    private Button btnFinalizareComanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comenzi_masa3_xanadu);

        firebaseService = FirebaseService.getInstance();
        initComponents();
        firebaseService.attachDataChangeEventListener3(dataChangeCallback());

    }

    private Callback<List<Comanda>> dataChangeCallback() {
        return new Callback<List<Comanda>>() {
            @Override
            public void runResultOnUiThread(List<Comanda> results) {
                if (results != null) {
                    //code apelat din FirebaseService in metoda onDataChange
                    Comandas3.clear();
                    Comandas3.addAll(results);

                    for (Comanda Comandai : Comandas3){
                        s3 = s3 + Integer.parseInt(Comandai.getPret());
                    }

                    tvPret.setText(String.valueOf(s3));
                    referenceComenziMasa3SumaXanadu.setValue(s3);

                    notifyListViewComandaAdapter();
                }
            }
        };
    }

    private void initComponents() {

        lvComandas3 = findViewById(R.id.main_lv_Comandas);
        tvPret = findViewById(R.id.pret_txtView);

        tvPret.setText(String.valueOf(s3));

        btnFinalizareComanda = findViewById(R.id.btn_finalizeaza_comanda);

        btnFinalizareComanda.setOnClickListener(getSaveFinalizareClickEventAlert());

        addListViewComandasAdapter();

    }

    private View.OnClickListener getSaveFinalizareClickEventAlert() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(ListaComenziMasa3XanaduActivity.this)
                        .setTitle("Finalizare comanda")
                        .setMessage("Sigur doriti finalizarea comenzii?")
                        .setPositiveButton("Yes", (DialogInterface.OnClickListener) getSaveFinalizareClickEventPositiveEvent())
                        .setNegativeButton("No", getSaveFinalizareClickEventNegativeEvent());
                build.create().show();

            }
        };

    }


    private DialogInterface.OnClickListener getSaveFinalizareClickEventPositiveEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), ComandaFinalizataActivity.class);
                startActivity(intent);
                finish();
                Comandas3.clear();
                referenceComenziMasa3Xanadu.removeValue();
                referenceComenziMasa3Xanadu.setValue("true");
                referenceComenziMasa3SumaXanadu.removeValue();
            }
        };

    }

    private DialogInterface.OnClickListener getSaveFinalizareClickEventNegativeEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Utilizatorul a renuntat la finalizarea comenzii",
                        Toast.LENGTH_SHORT).show();
            }
        };
    }


    private void addListViewComandasAdapter() {
        ComandaAdapter adapter = new ComandaAdapter(getApplicationContext(), R.layout.lv_row_view,
                Comandas3, getLayoutInflater());
        lvComandas3.setAdapter(adapter);
    }

    private void notifyListViewComandaAdapter() {
        ComandaAdapter adapter = (ComandaAdapter) lvComandas3.getAdapter();
        adapter.notifyDataSetChanged();
    }

}