package com.example.securesport;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_inscrire);

        Button enregistrerBtn = findViewById(R.id.signupbtn);

        enregistrerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'activité de connexion
                Intent intent = new Intent(Inscription.this, Connexion.class);
                startActivity(intent);
            }
        });
    }
}
