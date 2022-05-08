package eu.ase.firebaseauthlicenta.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import eu.ase.firebaseauthlicenta.Common.Util;
import eu.ase.firebaseauthlicenta.MainActivity;
import eu.ase.firebaseauthlicenta.MessageActivity;
import eu.ase.firebaseauthlicenta.R;
import eu.ase.firebaseauthlicenta.password.ResetPasswordActivity;
import eu.ase.firebaseauthlicenta.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private String email, password;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        progressBar = findViewById(R.id.progressBar);

    }

    public void tvSignupClick(View v){
        startActivity(new Intent(this, SignupActivity.class));
    }


    public void btnLoginClick(View v) {
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(email.equals("")){
            etEmail.setError(getString(R.string.enter_email));
        }
        else if (password.equals("")){
            etPassword.setError(getString(R.string.enter_password));
        }
        else
        {
            if(Util.connectionAvailable(this)) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login Failed: " +
                                            task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else
            {
                startActivity(new Intent(getApplicationContext(), MessageActivity.class));
            }

        }

    }

    public void tvResetPasswordClick(View view){
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }

    @Override
    protected void onStart() { // am facut aceasta metoda ca sa fim mereu in MainActivity cat timp sntem logati
        super.onStart();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }
}