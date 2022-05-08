package eu.ase.firebaseauthlicenta.order.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import eu.ase.firebaseauthlicenta.R;

public class ComandaAdapter extends ArrayAdapter<Comanda> {
    private Context context;
    private int resource;
    private List<Comanda> Comandas;
    private LayoutInflater inflater;

    public ComandaAdapter(@NonNull Context context, int resource, @NonNull List<Comanda> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.Comandas = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Comanda Comanda = Comandas.get(position);
        if (Comanda != null) {
            addDenumire(view, Comanda.getDenumire());
            addCantitate(view, Comanda.getCantitate());
            addPret(view, Comanda.getPret());

        }
        return view;
    }


    private void addDenumire(View view, String denumire) {
        TextView textView = view.findViewById(R.id.tv_denumire);
        populateTextViewContent(textView, denumire);
    }

    private void addCantitate(View view, String cantitate) {
        TextView textView = view.findViewById(R.id.tv_cantitate);
        populateTextViewContent(textView, cantitate);
    }

    private void addPret(View view, String pret) {
        TextView textView = view.findViewById(R.id.tv_pret);
        populateTextViewContent(textView, pret);
    }



    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.lv_row_default_value);
        }
    }
}
