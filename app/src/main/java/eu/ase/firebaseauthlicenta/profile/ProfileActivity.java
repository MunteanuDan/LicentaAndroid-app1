package eu.ase.firebaseauthlicenta.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import eu.ase.firebaseauthlicenta.Common.NodeNames;
import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.login.LoginActivity;
import eu.ase.firebaseauthlicenta.password.ChangePasswordActivity;
import eu.ase.firebaseauthlicenta.signup.SignupActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etName;
    private String email, name;

    private ImageView ivProfile;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private View progressBar;

    // pentru storage

    private StorageReference fileStorage;
    private Uri localFileUri; // pentru a stoca path-ul pe care user-ul il va selecta (imaginea din galerie de ex)
    private Uri  serverFileUri; // cand uploadam imaginea pe server si vrem sa luam url-ul server-ului
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        ivProfile = findViewById(R.id.ivProfile);
        progressBar = findViewById(R.id.progressBar);

        fileStorage = FirebaseStorage.getInstance().getReference(); // ne va da referinta catre storage-ul din firebase

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser !=null){
            etName.setText(firebaseUser.getDisplayName()); // o sa facem fetch in EditText-ul din ProfileActivity, din SignUpActivity
            etEmail.setText(firebaseUser.getEmail());
            serverFileUri = firebaseUser.getPhotoUrl();

            if(serverFileUri != null){
                // pentru a afisa imaginea din firebase
                Glide.with(this)
                        .load(serverFileUri)
                        .placeholder(R.drawable.default_profile) // va fi imaginea default_profile in acel loc
                        .error(R.drawable.default_profile) // in caz unei erori, va ramane to imaginea aceea
                        .into(ivProfile); // punem noua imagine de profil din SignUpActivity si putem schimba poza de profil oricand
            }

        }

    }

    public void btnLogoutClick(View view){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        finish();
    }

    public void btnSaveClick(View view){
        if(etName.getText().toString().trim().equals(""))
        {
            etEmail.setError(getString(R.string.enter_name));
        }
        else
        {
            if(localFileUri!=null) {
                updateNameAndPhoto();
            }
            else
                updateOnlyName();

        }
    }


    public void changeImage(View view){
        if(serverFileUri ==null) { // daca nu avem o imagine pe server
            pickImage(); // atunci user-ul va vrea sa puna acea imagine
        }
        else
        {
            PopupMenu popupMenu = new PopupMenu(this, view); // view-ul este catre ce fila vrem sa facem popUp, in cazul nostru este ImageView-ul deoarece suntem in changeImage
            popupMenu.getMenuInflater().inflate(R.menu.menu_picture, popupMenu.getMenu()); // popupMenu.getMenu() este parent-ul
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    int id = menuItem.getItemId();

                    if(id == R.id.mnuChangePic)
                    {
                        pickImage();
                    }
                    else if (id==R.id.mnuRemovePic)
                    {
                        removePhoto();
                    }

                    return false;
                }
            });

            popupMenu.show();

        }
    }


    private void pickImage() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) { // adresam permisiune si daca e acceptata
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // pentru a accesa imaginea din device-ul extern
            startActivityForResult(intent, 101);
        }
        else
        {  // daca nu e acceptata atunci vom intreba pentru permisiune
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 102); // pentru a intreba pentru permisiune vom folosi metoda de mai jos onRequestPermissionsResult
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // dupa ce user-ul alege o imagine
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){ // avand acelasi requestCode ca cel cu pickImage, este on result-ul aceluia
            if(resultCode==RESULT_OK){ //daca user-ul a selectat imaginea din galerie

                localFileUri = data.getData(); // ne va da path-ul imaginii
                ivProfile.setImageURI(localFileUri); // astfel user-ul deschide un picker(alege din galerie de ex) si va aparea pe imageView, nu si pe server momentan



            } // daca user-ul a deschis galeria dar nu a selectat nimic
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 102) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // pentru a accesa imaginea din device-ul extern
                startActivityForResult(intent, 101);
            }
            else
            { // daca user-ul nu e allowed
                Toast.makeText(this, R.string.permission_required, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void removePhoto(){
        progressBar.setVisibility(View.VISIBLE);
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(etName.getText().toString())
                .setPhotoUri(null) // am pus null pentru a sterge poza din UserProfile
                .build(); // facem request doar ca aici punem si poza

        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    String userID = firebaseUser.getUid(); // luam userId-ul utilizatorului actual
                    databaseReference = FirebaseDatabase.getInstance().getReference().child(NodeNames.USERS);

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(NodeNames.PHOTO, "");

                    databaseReference.child(userID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ProfileActivity.this, R.string.photo_removed_successfully, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else
                {
                    Toast.makeText(ProfileActivity.this, getString(R.string.failed_to_update_profile, task.getException()), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void updateNameAndPhoto(){
        String strFileName = firebaseUser.getUid() + ".jpg"; // numele file-ului cand va fi uploadat pe server

        final StorageReference fileRef = fileStorage.child("images/" + strFileName);

        progressBar.setVisibility(View.VISIBLE);

        fileRef.putFile(localFileUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() { // asa vom uploada file-uri pe server
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){ // daca file-ul a fost uploadat cu succes
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() { // sa luam url-ul file-ului
                        @Override
                        public void onSuccess(Uri uri) {
                            serverFileUri = uri;

                            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(etName.getText().toString())
                                    .setPhotoUri(serverFileUri)
                                    .build(); // facem request doar ca aici punem si poza

                            firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        String userID = firebaseUser.getUid(); // luam userId-ul utilizatorului actual
                                        databaseReference = FirebaseDatabase.getInstance().getReference().child(NodeNames.USERS);

                                        HashMap<String, String> hashMap = new HashMap<>();

                                        hashMap.put(NodeNames.NAME, etName.getText().toString().trim());
                                        hashMap.put(NodeNames.PHOTO, serverFileUri.getPath());

                                        databaseReference.child(userID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                finish();
                                            }
                                        });

                                    }
                                    else
                                    {
                                        Toast.makeText(ProfileActivity.this, getString(R.string.failed_to_update_profile, task.getException()), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    });
                }


            }
        });

    }

    private void updateOnlyName(){
        progressBar.setVisibility(View.VISIBLE);
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(etName.getText().toString())
                .build();

        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    String userID = firebaseUser.getUid(); // luam userId-ul utilizatorului actual
                    databaseReference = FirebaseDatabase.getInstance().getReference().child(NodeNames.USERS);

                    HashMap<String, String> hashMap = new HashMap<>();

                    hashMap.put(NodeNames.NAME, etName.getText().toString().trim());


                    databaseReference.child(userID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                        }
                    });

                }
                else
                {
                    Toast.makeText(ProfileActivity.this, getString(R.string.failed_to_update_profile, task.getException()), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void btnChangePasswordClick(View view){
        startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
    }

}