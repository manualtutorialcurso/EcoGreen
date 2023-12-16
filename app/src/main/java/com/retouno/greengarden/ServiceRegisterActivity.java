package com.retouno.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_register);
        Button botonRegresar = findViewById(R.id.btnReturn);
        Button btnElectricidad = findViewById(R.id.buttonElectricidad);
        Button btnAgua = findViewById(R.id.buttonAgua);
        Intent intent1 = new Intent(this, PrincipalActivity.class);
        Intent intent2 = new Intent(this, ServiceRegisterItem1Activity.class);
        Intent intent3 = new Intent(this, ServiceRegisterItem2Activity.class);

        //Boton Regresar
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        //Boton Registro de Agua
        btnAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        //Boton Registro de eléctricidad
        btnElectricidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
            }
        });
    }
}