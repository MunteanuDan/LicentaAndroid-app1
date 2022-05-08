package eu.ase.firebaseauthlicenta.order.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eu.ase.firebaseauthlicenta.order.util.Callback;
import eu.ase.firebaseauthlicenta.order.util.Comanda;

public class FirebaseService {

    public static final String COMENZI_REFERENCE = "comenzi1";
    public static final String COMENZI_REFERENCE_SUMA = "comenziSuma";
    public static DatabaseReference referenceComenziMasa1Xanadu, referenceComenziMasa2Xanadu, referenceComenziMasa3Xanadu;
    public static DatabaseReference referenceComenziMasa1Klandestin, referenceComenziMasa2Klandestin, referenceComenziMasa3Klandestin;
    public static DatabaseReference referenceComenziMasa1SumaXanadu, referenceComenziMasa2SumaXanadu, referenceComenziMasa3SumaXanadu;
    public static DatabaseReference referenceComenziMasa1SumaKlandestin, referenceComenziMasa2SumaKlandestin, referenceComenziMasa3SumaKlandestin;
    private static FirebaseService firebaseService;

    private FirebaseService() {
        referenceComenziMasa1Xanadu = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE).child("Xanadu").child("1");
        referenceComenziMasa2Xanadu = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE).child("Xanadu").child("2");
        referenceComenziMasa3Xanadu = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE).child("Xanadu").child("3");
        referenceComenziMasa1Klandestin = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE).child("Klandestin").child("1");
        referenceComenziMasa2Klandestin = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE).child("Klandestin").child("2");
        referenceComenziMasa3Klandestin = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE).child("Klandestin").child("3");

        referenceComenziMasa1SumaXanadu = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE_SUMA).child("Xanadu").child("1");
        referenceComenziMasa2SumaXanadu = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE_SUMA).child("Xanadu").child("2");
        referenceComenziMasa3SumaXanadu = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE_SUMA).child("Xanadu").child("3");

        referenceComenziMasa1SumaKlandestin = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE_SUMA).child("Klandestin").child("1");
        referenceComenziMasa2SumaKlandestin = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE_SUMA).child("Klandestin").child("2");
        referenceComenziMasa3SumaKlandestin = FirebaseDatabase.getInstance().getReference(COMENZI_REFERENCE_SUMA).child("Klandestin").child("3");

    }

    public static FirebaseService getInstance() {
        if (firebaseService == null) {
            synchronized (FirebaseService.class) {
                if (firebaseService == null) {
                    firebaseService = new FirebaseService();
                }
            }
        }
        return firebaseService;
    }

    public void insertMasa1(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face insert trebuie sa NU avem id
        if (Comanda == null || (Comanda.getId() != null && !Comanda.getId().trim().isEmpty())) {
            return;
        }
        //adaugam o cheie noua in Firebase. Mare atentie, aceasta nu contine valorile obiectului Comanda
        String id = referenceComenziMasa1Xanadu.push().getKey();
        Comanda.setId(id);
        //ne pozitionam pe id-ul adaugat la linia 46
        //setValue asigura adaugarea informatiilor stocate in copilul pozitionat
        referenceComenziMasa1Xanadu.child(Comanda.getId()).setValue(Comanda);

    }
    public void insertMasa2(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face insert trebuie sa NU avem id
        if (Comanda == null || (Comanda.getId() != null && !Comanda.getId().trim().isEmpty())) {
            return;
        }
        //adaugam o cheie noua in Firebase. Mare atentie, aceasta nu contine valorile obiectului Comanda
        String id = referenceComenziMasa2Xanadu.push().getKey();
        Comanda.setId(id);
        //ne pozitionam pe id-ul adaugat la linia 46
        //setValue asigura adaugarea informatiilor stocate in copilul pozitionat
        referenceComenziMasa2Xanadu.child(Comanda.getId()).setValue(Comanda);

    }
    public void insertMasa3(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face insert trebuie sa NU avem id
        if (Comanda == null || (Comanda.getId() != null && !Comanda.getId().trim().isEmpty())) {
            return;
        }
        //adaugam o cheie noua in Firebase. Mare atentie, aceasta nu contine valorile obiectului Comanda
        String id = referenceComenziMasa3Xanadu.push().getKey();
        Comanda.setId(id);
        //ne pozitionam pe id-ul adaugat la linia 46
        //setValue asigura adaugarea informatiilor stocate in copilul pozitionat
        referenceComenziMasa3Xanadu.child(Comanda.getId()).setValue(Comanda);

    }

    public void updateMasa1(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face update trebuie sa avem id
        if (Comanda == null || Comanda.getId() == null || Comanda.getId().trim().isEmpty()) {
            return;
        }

        //setValue asigura suprascrierea informatiilor stocate in copilul pozitionat prin child.
        referenceComenziMasa1Xanadu.child(Comanda.getId()).setValue(Comanda);
    }
    public void updateMasa2(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face update trebuie sa avem id
        if (Comanda == null || Comanda.getId() == null || Comanda.getId().trim().isEmpty()) {
            return;
        }

        //setValue asigura suprascrierea informatiilor stocate in copilul pozitionat prin child.
        referenceComenziMasa2Xanadu.child(Comanda.getId()).setValue(Comanda);
    }
    public void updateMasa3(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face update trebuie sa avem id
        if (Comanda == null || Comanda.getId() == null || Comanda.getId().trim().isEmpty()) {
            return;
        }

        //setValue asigura suprascrierea informatiilor stocate in copilul pozitionat prin child.
        referenceComenziMasa3Xanadu.child(Comanda.getId()).setValue(Comanda);
    }

//    public void delete(Comanda Comanda) {
//        //validam obiectul Comanda. Ca sa putem face delete trebuie sa avem id
//        if (Comanda == null || Comanda.getId() == null || Comanda.getId().trim().isEmpty()) {
//            return;
//        }
//        //ne pozitionam pe un copil din Comandas (cel pe care l-am primit in obiectul Comanda)
//        //removeValue asigura stergerea informatiilor stocate in copilul pozitionat
//        referenceComenziMasa1.child(Comanda.getId()).removeValue();
//    }

    public void attachDataChangeEventListener1(Callback<List<Comanda>> callback) {
        //evenimentul este atasat la nivel de Comandas (reference).
        //Prin urmare asculta orice modificare de insert/update/delete executata asupra acestui nod
        //Apelul metodelor de mai sus forteaza primirea unei notificari de la Firebase in acest eveniment
        referenceComenziMasa1Xanadu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Comanda> Comandas1 = new ArrayList<>();
                //parcurgem lista de subnoduri din cel principal
                for (DataSnapshot data : snapshot.getChildren()) {
                    //folosim reflection pentru convertirea de la Snapshot la Comanda
                    //Mare atentie sa definim in Comanda un constructor default + getteri si setteri
                    Comanda Comanda1 = data.getValue(Comanda.class);
                    if (Comanda1 != null) {
                        Comandas1.add(Comanda1);
                    }
                }
                //trimitem lista catre activitatea prin intermediul callback-ului
                callback.runResultOnUiThread(Comandas1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService", "Data is not available");
            }
        });

    }

    public void attachDataChangeEventListener2(Callback<List<Comanda>> callback) {
        //evenimentul este atasat la nivel de Comandas (reference).
        //Prin urmare asculta orice modificare de insert/update/delete executata asupra acestui nod
        //Apelul metodelor de mai sus forteaza primirea unei notificari de la Firebase in acest eveniment
        referenceComenziMasa2Xanadu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Comanda> Comandas2 = new ArrayList<>();
                //parcurgem lista de subnoduri din cel principal
                for (DataSnapshot data : snapshot.getChildren()) {
                    //folosim reflection pentru convertirea de la Snapshot la Comanda
                    //Mare atentie sa definim in Comanda un constructor default + getteri si setteri
                    Comanda Comanda2 = data.getValue(Comanda.class);
                    if (Comanda2 != null) {
                        Comandas2.add(Comanda2);
                    }
                }
                //trimitem lista catre activitatea prin intermediul callback-ului
                callback.runResultOnUiThread(Comandas2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService", "Data is not available");
            }
        });

    }

    public void attachDataChangeEventListener3(Callback<List<Comanda>> callback) {
        //evenimentul este atasat la nivel de Comandas (reference).
        //Prin urmare asculta orice modificare de insert/update/delete executata asupra acestui nod
        //Apelul metodelor de mai sus forteaza primirea unei notificari de la Firebase in acest eveniment
        referenceComenziMasa3Xanadu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Comanda> Comandas3 = new ArrayList<>();
                //parcurgem lista de subnoduri din cel principal
                for (DataSnapshot data : snapshot.getChildren()) {
                    //folosim reflection pentru convertirea de la Snapshot la Comanda
                    //Mare atentie sa definim in Comanda un constructor default + getteri si setteri
                    Comanda Comanda3 = data.getValue(Comanda.class);
                    if (Comanda3 != null) {
                        Comandas3.add(Comanda3);
                    }
                }
                //trimitem lista catre activitatea prin intermediul callback-ului
                callback.runResultOnUiThread(Comandas3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService", "Data is not available");
            }
        });

    }

    // Klandestin

    public void insertMasa1Klandestin(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face insert trebuie sa NU avem id
        if (Comanda == null || (Comanda.getId() != null && !Comanda.getId().trim().isEmpty())) {
            return;
        }
        //adaugam o cheie noua in Firebase. Mare atentie, aceasta nu contine valorile obiectului Comanda
        String id = referenceComenziMasa1Klandestin.push().getKey();
        Comanda.setId(id);
        //ne pozitionam pe id-ul adaugat la linia 46
        //setValue asigura adaugarea informatiilor stocate in copilul pozitionat
        referenceComenziMasa1Klandestin.child(Comanda.getId()).setValue(Comanda);

    }
    public void insertMasa2Klandestin(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face insert trebuie sa NU avem id
        if (Comanda == null || (Comanda.getId() != null && !Comanda.getId().trim().isEmpty())) {
            return;
        }
        //adaugam o cheie noua in Firebase. Mare atentie, aceasta nu contine valorile obiectului Comanda
        String id = referenceComenziMasa2Klandestin.push().getKey();
        Comanda.setId(id);
        //ne pozitionam pe id-ul adaugat la linia 46
        //setValue asigura adaugarea informatiilor stocate in copilul pozitionat
        referenceComenziMasa2Klandestin.child(Comanda.getId()).setValue(Comanda);

    }
    public void insertMasa3Klandestin(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face insert trebuie sa NU avem id
        if (Comanda == null || (Comanda.getId() != null && !Comanda.getId().trim().isEmpty())) {
            return;
        }
        //adaugam o cheie noua in Firebase. Mare atentie, aceasta nu contine valorile obiectului Comanda
        String id = referenceComenziMasa3Klandestin.push().getKey();
        Comanda.setId(id);
        //ne pozitionam pe id-ul adaugat la linia 46
        //setValue asigura adaugarea informatiilor stocate in copilul pozitionat
        referenceComenziMasa3Klandestin.child(Comanda.getId()).setValue(Comanda);

    }

    public void updateMasa1Klandestin(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face update trebuie sa avem id
        if (Comanda == null || Comanda.getId() == null || Comanda.getId().trim().isEmpty()) {
            return;
        }

        //setValue asigura suprascrierea informatiilor stocate in copilul pozitionat prin child.
        referenceComenziMasa1Klandestin.child(Comanda.getId()).setValue(Comanda);
    }
    public void updateMasa2Klandestin(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face update trebuie sa avem id
        if (Comanda == null || Comanda.getId() == null || Comanda.getId().trim().isEmpty()) {
            return;
        }

        //setValue asigura suprascrierea informatiilor stocate in copilul pozitionat prin child.
        referenceComenziMasa2Klandestin.child(Comanda.getId()).setValue(Comanda);
    }
    public void updateMasa3Klandestin(Comanda Comanda) {
        //validam obiectul Comanda. Ca sa putem face update trebuie sa avem id
        if (Comanda == null || Comanda.getId() == null || Comanda.getId().trim().isEmpty()) {
            return;
        }

        //setValue asigura suprascrierea informatiilor stocate in copilul pozitionat prin child.
        referenceComenziMasa3Klandestin.child(Comanda.getId()).setValue(Comanda);
    }

//    public void delete(Comanda Comanda) {
//        //validam obiectul Comanda. Ca sa putem face delete trebuie sa avem id
//        if (Comanda == null || Comanda.getId() == null || Comanda.getId().trim().isEmpty()) {
//            return;
//        }
//        //ne pozitionam pe un copil din Comandas (cel pe care l-am primit in obiectul Comanda)
//        //removeValue asigura stergerea informatiilor stocate in copilul pozitionat
//        referenceComenziMasa1.child(Comanda.getId()).removeValue();
//    }

    public void attachDataChangeEventListener1Klandestin(Callback<List<Comanda>> callback) {
        //evenimentul este atasat la nivel de Comandas (reference).
        //Prin urmare asculta orice modificare de insert/update/delete executata asupra acestui nod
        //Apelul metodelor de mai sus forteaza primirea unei notificari de la Firebase in acest eveniment
        referenceComenziMasa1Klandestin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Comanda> Comandas1 = new ArrayList<>();
                //parcurgem lista de subnoduri din cel principal
                for (DataSnapshot data : snapshot.getChildren()) {
                    //folosim reflection pentru convertirea de la Snapshot la Comanda
                    //Mare atentie sa definim in Comanda un constructor default + getteri si setteri
                    Comanda Comanda1 = data.getValue(Comanda.class);
                    if (Comanda1 != null) {
                        Comandas1.add(Comanda1);
                    }
                }
                //trimitem lista catre activitatea prin intermediul callback-ului
                callback.runResultOnUiThread(Comandas1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService", "Data is not available");
            }
        });

    }

    public void attachDataChangeEventListener2Klandestin(Callback<List<Comanda>> callback) {
        //evenimentul este atasat la nivel de Comandas (reference).
        //Prin urmare asculta orice modificare de insert/update/delete executata asupra acestui nod
        //Apelul metodelor de mai sus forteaza primirea unei notificari de la Firebase in acest eveniment
        referenceComenziMasa2Klandestin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Comanda> Comandas2 = new ArrayList<>();
                //parcurgem lista de subnoduri din cel principal
                for (DataSnapshot data : snapshot.getChildren()) {
                    //folosim reflection pentru convertirea de la Snapshot la Comanda
                    //Mare atentie sa definim in Comanda un constructor default + getteri si setteri
                    Comanda Comanda2 = data.getValue(Comanda.class);
                    if (Comanda2 != null) {
                        Comandas2.add(Comanda2);
                    }
                }
                //trimitem lista catre activitatea prin intermediul callback-ului
                callback.runResultOnUiThread(Comandas2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService", "Data is not available");
            }
        });

    }

    public void attachDataChangeEventListener3Klandestin(Callback<List<Comanda>> callback) {
        //evenimentul este atasat la nivel de Comandas (reference).
        //Prin urmare asculta orice modificare de insert/update/delete executata asupra acestui nod
        //Apelul metodelor de mai sus forteaza primirea unei notificari de la Firebase in acest eveniment
        referenceComenziMasa3Klandestin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Comanda> Comandas3 = new ArrayList<>();
                //parcurgem lista de subnoduri din cel principal
                for (DataSnapshot data : snapshot.getChildren()) {
                    //folosim reflection pentru convertirea de la Snapshot la Comanda
                    //Mare atentie sa definim in Comanda un constructor default + getteri si setteri
                    Comanda Comanda3 = data.getValue(Comanda.class);
                    if (Comanda3 != null) {
                        Comandas3.add(Comanda3);
                    }
                }
                //trimitem lista catre activitatea prin intermediul callback-ului
                callback.runResultOnUiThread(Comandas3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService", "Data is not available");
            }
        });

    }

}
