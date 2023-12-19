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

        // botones

        Button btnEstadistica = findViewById(R.id.btnStatitics);
        Button btnCategoria = findViewById(R.id.btnCategory);
        Button btnConsejo = findViewById(R.id.btnAdvice);
        Button btnConfiguracion = findViewById(R.id.btnSettings);

        Intent intent1 = new Intent(this, StatisticsActivity.class);
        Intent intent2 = new Intent(this, ServiceRegisterActivity.class);
        Intent intent3 = new Intent(this, AdviceActivity.class);
        Intent intent4 = new Intent(this, SettingsActivity.class);

        //Boton Estadistica
        btnEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });

        btnConsejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent3);
            }
        });

        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent4);
            }
        });
    }

}


