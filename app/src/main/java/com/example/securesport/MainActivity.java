package com.example.securesport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.TextBoundsInfo;
import android.widget.TextView;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private TextView txt ;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("I'm Abderrahim");
            }
        });
    }
}