package com.retouno.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Button btnEstadistica = findViewById(R.id.btnEstadistica);
        Button btnCategoria = findViewById(R.id.btnCategoria);
        Button btnConsejo = findViewById(R.id.btnConsejo);
        Intent intent1 = new Intent(this, SettingsActivity.class);
        Intent intent2 = new Intent(this, ServiceRegisterActivity.class);
        Intent intent3 = new Intent(this, AdviceActivity.class);

        //Boton Estadistica
        btnEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        btnConsejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
            }
        });
    }
}