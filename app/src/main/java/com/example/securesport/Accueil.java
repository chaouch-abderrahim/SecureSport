package com.example.securesport;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.TextBoundsInfo;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Accueil extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    ActiviteFragment activiteFragment = new ActiviteFragment();
    ProfilFragment profilFragment = new ProfilFragment();
    GroupFragment groupFragment = new GroupFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.profil);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getItemId()==R.id.profil){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,profilFragment).commit();
                    return true;

                }
                else if(item.getItemId()==R.id.Activite){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,activiteFragment).commit();
                    return true;
                }else if(item.getItemId()==R.id.group){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,groupFragment).commit();
                    return true;
                }
                else if(item.getItemId()==R.id.Acceuil){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                    return true;
                }
                else{
                    return false;}


            }
        });

    }
}