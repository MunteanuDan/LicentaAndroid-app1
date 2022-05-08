package eu.ase.firebaseauthlicenta.signup;

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
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
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

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etName, etPassword, etConfirmPassword;
    private String email, name, password, confirmPassword;
    private View progressBar;
    private ImageView ivProfile;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    // pentru storage

    private StorageReference fileStorage;
    private Uri localFileUri; // pentru a stoca path-ul pe care user-ul il va selecta (imaginea din galerie de ex)
    private Uri  serverFileUri; // cand uploadam imaginea pe server si vrem sa luam url-ul server-ului


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        ivProfile = findViewById(R.id.ivProfile);

        fileStorage = FirebaseStorage.getInstance().getReference(); // ne va da referinta catre storage-ul din firebase

        progressBar = findViewById(R.id.progressBar);

    }

    public void pickImage(View v) {

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

    private void updateNameAndPhoto()
    {
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
                                        hashMap.put(NodeNames.EMAIL, etEmail.getText().toString().trim());
                                        hashMap.put(NodeNames.ONLINE, "true");
                                        hashMap.put(NodeNames.PHOTO, serverFileUri.getPath());

                                        databaseReference.child(userID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(SignupActivity.this, R.string.user_create_successfully, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            }
                                        });

                                    }
                                    else
                                    {
                                        Toast.makeText(SignupActivity.this, getString(R.string.failed_to_update_profile, task.getException()), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    });
                }


            }
        });
    }

    private void updateOnlyName()
    {
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
                    hashMap.put(NodeNames.EMAIL, etEmail.getText().toString().trim());
                    hashMap.put(NodeNames.ONLINE, "true");
                    hashMap.put(NodeNames.PHOTO, "");

                    progressBar.setVisibility(View.VISIBLE);

                    databaseReference.child(userID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SignupActivity.this, R.string.user_create_successfully, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    });

                }
                else
                {
                    Toast.makeText(SignupActivity.this, getString(R.string.failed_to_update_profile, task.getException()), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void btnSignupClick(View v)
    {
        email = etEmail.getText().toString().trim();
        name = etName.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();

        if(email.equals("")){
            etEmail.setError(getString(R.string.enter_email));
        }
        else if (name.equals("")){
            etName.setError(getString(R.string.enter_name));
        } else if (password.equals("")){
            etName.setError(getString(R.string.enter_password));
        } else if (confirmPassword.equals("")){
            etName.setError(getString(R.string.enter_confirm_password));
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ // daca email-ul nu este in formatul corespunzator
            etEmail.setError(getString(R.string.enter_correct_email));
        }
        else if(!password.equals(confirmPassword)){
            etConfirmPassword.setError(getString(R.string.password_mismatch));
        }
        else {

            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                firebaseUser = firebaseAuth.getCurrentUser(); // sa luam user-ul actual

                                if(localFileUri!=null){ // inseamna ca user-ul a selectat o imagine
                                    updateNameAndPhoto();
                                }

                                updateOnlyName();
                            }
                            else {
                                Toast.makeText(SignupActivity.this, getString(R.string.signup_failed, task.getException()), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }


}

    }

