package com.example.securesport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private CancellationSignal cancellationSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton fingerprintBtn = findViewById(R.id.fingerprintBtn);

        // Vérifier la permission d'utilisation de l'empreinte digitale
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) ==
                PackageManager.PERMISSION_GRANTED) {
            setupFingerprintAuthentication(fingerprintBtn);
        } else {
            // Demander la permission d'utilisation de l'empreinte digitale
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.USE_BIOMETRIC}, 1);
        }

        // Ajouter un OnClickListener pour rediriger vers l'activité InscriptionActivity lorsque l'utilisateur clique sur "S'inscrire"
        TextView inscrireTextView = findViewById(R.id.inscrire);
        inscrireTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'activité InscriptionActivity
                Intent intent = new Intent(MainActivity.this, inscription.class);
                startActivity(intent);
            }
        });

        // Ajouter un OnClickListener pour le bouton de connexion
        MaterialButton loginBtn = findViewById(R.id.loginbtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifier les informations d'identification et l'empreinte digitale
                if (checkCredentials() || checkFingerprint()) {
                    // Informations d'identification ou empreinte digitale valides, redirection vers la page d'accueil
                    redirectToHomePage();
                } else {
                    // Informations d'identification ou empreinte digitale invalides, afficher un message d'erreur si nécessaire
                    Toast.makeText(MainActivity.this, "Nom d'utilisateur, mot de passe ou empreinte digitale incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupFingerprintAuthentication(MaterialButton fingerprintBtn) {
        fingerprintBtn.setOnClickListener(v -> authenticateWithFingerprint());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void authenticateWithFingerprint() {
        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(this);

        if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
            cancellationSignal = new CancellationSignal();

            FingerprintManagerCompat.AuthenticationCallback authenticationCallback =
                    new FingerprintManagerCompat.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                            super.onAuthenticationSucceeded(result);
                            // Authentification par empreinte digitale réussie
                            Toast.makeText(MainActivity.this, "Authentification réussie par empreinte digitale", Toast.LENGTH_SHORT).show();
                            redirectToHomePage();
                        }

                        @Override
                        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                            super.onAuthenticationHelp(helpCode, helpString);
                            // Aide pendant l'authentification
                            Toast.makeText(MainActivity.this, "Aide pendant l'authentification: " + helpString, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            super.onAuthenticationFailed();
                            // Authentification par empreinte digitale échouée
                            Toast.makeText(MainActivity.this, "Échec de l'authentification par empreinte digitale", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAuthenticationError(int errorCode, CharSequence errString) {
                            super.onAuthenticationError(errorCode, errString);
                            // Erreur pendant l'authentification
                            Toast.makeText(MainActivity.this, "Erreur pendant l'authentification: " + errString, Toast.LENGTH_SHORT).show();
                        }
                    };

            fingerprintManager.authenticate(null, 0, cancellationSignal, authenticationCallback, null);
        } else {
            // L'empreinte digitale n'est pas disponible ou n'a pas été configurée
            Toast.makeText(this, "L'empreinte digitale n'est pas disponible ou n'a pas été configurée.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Arrêter l'authentification par empreinte digitale si l'activité est détruite
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
    }

    // Méthode pour vérifier les informations d'identification
    private boolean checkCredentials() {
        // Remplacez ces valeurs par celles que vous attendez de l'utilisateur
        String expectedUsername = "admin";
        String expectedPassword = "admin";

        // Récupérer le nom d'utilisateur et le mot de passe saisis par l'utilisateur
        // Remplacez les IDs par ceux de vos champs de saisie dans votre fichier XML
        TextView usernameTextView = findViewById(R.id.username);
        TextView passwordTextView = findViewById(R.id.password);

        String enteredUsername = usernameTextView.getText().toString();
        String enteredPassword = passwordTextView.getText().toString();

        // Vérifier les informations d'identification
        return expectedUsername.equals(enteredUsername) && expectedPassword.equals(enteredPassword);
    }

    // Méthode pour vérifier l'empreinte digitale
    private boolean checkFingerprint() {
        // Ajoutez ici la logique pour vérifier l'empreinte digitale
        // Cette méthode doit renvoyer true si l'empreinte digitale est vérifiée avec succès
        // Sinon, elle doit renvoyer false
        // Vous pouvez utiliser l'API FingerprintManagerCompat pour cela
        // Assurez-vous que la partie d'authentification par empreinte digitale est correctement gérée
        // dans votre code, comme vous l'avez déjà fait dans votre code initial
        // ...

        return false; // À mettre à jour avec votre logique d'empreinte digitale
    }

    // Méthode pour rediriger vers la page d'accueil
    private void redirectToHomePage() {
        Intent intent = new Intent(MainActivity.this, HomeFragment.class);
        startActivity(intent);
    }
}
