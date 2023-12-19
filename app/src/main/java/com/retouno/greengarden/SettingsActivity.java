package com.retouno.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Elementos de la interfaz
        Button botonRegresar = findViewById(R.id.btnReturn);

        Intent intent = new Intent(this, PrincipalActivity.class);


        //Boton Regresar
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

}