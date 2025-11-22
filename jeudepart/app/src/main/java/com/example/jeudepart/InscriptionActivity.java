package com.example.jeudepart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionActivity extends AppCompatActivity {

    private ImageView paysImageView;
    private EditText nomNewUser;
    private EditText prenomNewUser;
    private EditText emailNewUser;
    private EditText pwNewUser;
    private Pays paysActuel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //set listeners EditText
        nomNewUser = findViewById(R.id.nomNewUser);
        prenomNewUser = findViewById(R.id.prenomNewUser);
        emailNewUser = findViewById(R.id.emailNewUser);
        pwNewUser = findViewById(R.id.pwNewUser);

        Spinner listPaysSpinner = findViewById(R.id.listPays);
        paysImageView = findViewById(R.id.paysImage);

        final Pays[] tousLesPays = Pays.values();

        String[] nomsPays = new String[tousLesPays.length];
        for (int i = 0; i < tousLesPays.length; i++) {
            nomsPays[i] = tousLesPays[i].getNom();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                nomsPays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listPaysSpinner.setAdapter(adapter);
        listPaysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pays paysSelectionne = tousLesPays[position];
                paysImageView.setImageResource(paysSelectionne.getDrapeauResId());
                paysActuel = paysSelectionne;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        paysImageView.setImageResource(tousLesPays[0].getDrapeauResId());

        // Clique sur le bouton
        Button newAccountBtn = findViewById(R.id.newAccountBtn);
        newAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifAccount()){
                    enregistrerNouvelUtilisateur();
                }
            }
        });
    }

    private boolean verifAccount(){
         return verifNom() && verifPrenom() && verifEmail() && verifMdp();
    }

    private boolean verifNom(){
        String nomUserSent = nomNewUser.getText().toString().trim();

        if(nomUserSent.length() < 3){
            nomNewUser.setError("Le nom doit être d'au moins 3 charactères.");
            return false;
        }

        nomNewUser.setError(null);
        return true;
    }

    private boolean verifPrenom(){
        String prenomUserSent = prenomNewUser.getText().toString().trim();

        if(prenomUserSent.length() < 3){
            prenomNewUser.setError("Le prenom doit être d'au moins 3 char");
            return false;
        }

        prenomNewUser.setError(null);
        return true;
    }

    private boolean verifEmail() {
        String emailSent = emailNewUser.getText().toString().trim();

        if (emailSent.isEmpty()) {
            emailNewUser.setError("Le courriel est requis");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailSent).matches()) {
            emailNewUser.setError("Veuillez entrer une adresse courriel valide");
            return false;
        }

        emailNewUser.setError(null);
        return true;
    }

    private boolean verifMdp(){
        String mdpSent = pwNewUser.getText().toString().trim();

        if(mdpSent.length() < 6){
            pwNewUser.setError("Le mot de passe doit être d'au moins 6 charactères.");
            return false;
        }

        pwNewUser.setError(null);
        return true;
    }

    private void enregistrerNouvelUtilisateur() {
        String prenom = prenomNewUser.getText().toString().trim();
        String nom = nomNewUser.getText().toString().trim();
        String email = emailNewUser.getText().toString().trim();
        String password = pwNewUser.getText().toString().trim();
        Pays paysSelectionne = paysActuel;

        User newUser = new User(prenom, nom, email, password, paysSelectionne);

        UserDataSource dataSource = new UserDataSource(this);

        User existingUser = dataSource.getUserByEmail(email);

        if (existingUser != null) {
            emailNewUser.setError("Ce courriel est déjà utilisé.");
            dataSource.close();
            return;
        }
        User createdUser = dataSource.createUser(newUser);

        if (createdUser != null) {
            Toast.makeText(this, "Votre compte a bien été créé. Bienvenue!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConnexionActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Échec de l'enregistrement.", Toast.LENGTH_SHORT).show();
        }
        dataSource.close();
    }
}