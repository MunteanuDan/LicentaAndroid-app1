package eu.ase.firebaseauthlicenta.password;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import eu.ase.firebaseauthlicenta.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private TextInputEditText etPassword, etConfirmPassword;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        progressBar = findViewById(R.id.progressBar);

    }

    public void btnChangePasswordClick(View view){
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if(password.equals("")){
            etPassword.setError(getString(R.string.enter_password));
        }
        else if(confirmPassword.equals("")){
            etConfirmPassword.setError(getString(R.string.enter_confirm_password));
        }
        else if(!password.equals(confirmPassword)){
            etConfirmPassword.setError(getString(R.string.password_mismatch));
        }
        else { // daca totul este ok
            progressBar.setVisibility(View.VISIBLE);
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(); // obiect pentru Firebase class
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser(); // obiect pentru Firebase User

            if(firebaseUser!=null){ // daca user-ul exista
                firebaseUser.updatePassword(password).addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful()){
                        Toast.makeText(ChangePasswordActivity.this, R.string.password_changed_successfully, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(ChangePasswordActivity.this, getString(R.string.something_went_wrong, task.getException()), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }

}