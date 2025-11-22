package com.example.jeudepart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ConnexionActivity extends AppCompatActivity {

    private EditText emailUserEditText;
    private EditText pwUserEditText;
    private UserDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        emailUserEditText = findViewById(R.id.emailUser);
        pwUserEditText = findViewById(R.id.pwUser);

        dataSource = new UserDataSource(this);

        Button inscrireBtn = findViewById(R.id.inscrireBtn);
        inscrireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

        Button seConnecterBtn = findViewById(R.id.seConnecter);
        seConnecterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tenterConnexion();
            }
        });
    }

    private void tenterConnexion() {
        String email = emailUserEditText.getText().toString().trim();
        String password = pwUserEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer le courriel et le mot de passe.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Search user avec email
        User user = dataSource.getUserByEmail(email);

        if (user != null) {
            //verif mdp
            if (user.getPassword().equals(password)) {
                Toast.makeText(this, "Connexion réussie! Bienvenue " + user.getPrenom(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ConnexionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                pwUserEditText.setError("Mot de passe incorrect.");
                Toast.makeText(this, "Identifiants invalides.", Toast.LENGTH_SHORT).show();
            }
        } else {
            emailUserEditText.setError("Ce courriel n'existe pas.");
            Toast.makeText(this, "Identifiants invalides.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataSource != null) {
            dataSource.close();
        }
    }
}