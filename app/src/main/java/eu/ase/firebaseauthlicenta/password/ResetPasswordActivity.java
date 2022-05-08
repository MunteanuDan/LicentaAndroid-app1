package eu.ase.firebaseauthlicenta.password;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import eu.ase.firebaseauthlicenta.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private TextView tvMessage;
    private LinearLayout llResetPassword, llMessage;
    private Button btnRetry;
    private View progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etEmail = findViewById(R.id.etEmail);
        tvMessage = findViewById(R.id.tvMessage);
        llResetPassword = findViewById(R.id.llResetPassword);
        llMessage = findViewById(R.id.llMessage);

        btnRetry = findViewById(R.id.btnRetry);
        progressBar = findViewById(R.id.progressBar);

    }

    public void btnResetPasswordClick(View view){
        String email = etEmail.getText().toString().trim();

        if(email.equals(""))
        {
            etEmail.setError(getString(R.string.enter_email));
        }
        else
        {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) { // dupa ce e completat
                    progressBar.setVisibility(View.GONE);
                    llResetPassword.setVisibility(View.GONE); // linearLayout de reset password dispare
                    llMessage.setVisibility(View.VISIBLE); // LinearLayout de mesaj apare

                    if(task.isSuccessful())
                    {
                        tvMessage.setText(getString(R.string.reset_password_instructions, email));
                        new CountDownTimer(60000, 1000){

                            @Override
                            public void onTick(long l) { // ce se intampla la fiecare countDownInterval, la noi este 1 secunda
                                btnRetry.setText(getString(R.string.resend_timer, String.valueOf(l/1000))); //impartim la 1000 ca sa avem mereu timpul ramas in secunde
                                btnRetry.setOnClickListener(null); // opreste functionalitatea Butonului de Retry
                            }

                            @Override
                            public void onFinish() { // dupa terminare, adica dupa 60 de secunde in cazul nostru
                                btnRetry.setText(R.string.retry);

                                btnRetry.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        llResetPassword.setVisibility(View.VISIBLE);
                                        llMessage.setVisibility(View.GONE);
                                    }
                                });

                            }
                        }.start(); // start pentru a incepe timer-ul
                    }
                    else
                    {
                        tvMessage.setText(getString(R.string.email_sent_failed, task.getException()));
                        btnRetry.setText(R.string.retry);

                        btnRetry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                llResetPassword.setVisibility(View.VISIBLE);
                                llMessage.setVisibility(View.GONE);
                            }
                        });

                    }


                }
            });
        }

    }

    public void btnCloseClick(View view) {
        finish();
    }

}