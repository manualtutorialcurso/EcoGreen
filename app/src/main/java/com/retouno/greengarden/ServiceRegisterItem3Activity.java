package com.retouno.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.retouno.greengarden.modelos.Fertilizante;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class ServiceRegisterItem3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_register_item3);

        // Obtener referencias a los elementos de la interfaz de usuario
        Button botonRetroceso = findViewById(R.id.btnReturn);
        Button botonRegistrar = findViewById(R.id.btnRegisterManure);
        EditText gramos = findViewById(R.id.inputGrManure);
        EditText precio = findViewById(R.id.inputCostManure);
        EditText mes = findViewById(R.id.inputMonthManure);

        // Intent utilizado para regresar a la actividad ServiceRegister
        Intent intent1 = new Intent(this, ServiceRegisterActivity.class);

        // Intent utilizado para navegar a la actividad Principal
        Intent intent2 = new Intent(this, PrincipalActivity.class);

        // Configuración del botón "Regresar"
        botonRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        // Configuración del botón "Registrar"
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar que los campos no estén vacíos
                if (!gramos.getText().toString().isEmpty() && !precio.getText().toString().isEmpty() && !mes.getText().toString().isEmpty()) {
                    String mesBuscado = mes.getText().toString();
                    boolean mesExiste = verificarMes(mesBuscado);
                    if (mesExiste) {
                        // El mes ya existe en el archivo
                        Toast.makeText(ServiceRegisterItem3Activity.this, "El mes ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean datosGuardados = guardarDatos(gramos.getText().toString(), precio.getText().toString(), mes.getText().toString());
                        if (datosGuardados) {
                            // Se cambia de actividad
                            startActivity(intent2);
                        } else {
                            Toast.makeText(ServiceRegisterItem3Activity.this, "Error al guardar el archivo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Algunos campos están vacíos
                    Toast.makeText(ServiceRegisterItem3Activity.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Verifica si un mes existe en el archivo "fertilizante.txt".
     *
     * @param mesBuscado El mes a buscar.
     * @return true si el mes existe, false en caso contrario.
     */
    public boolean verificarMes(String mesBuscado) {
        File file = new File(getFilesDir(), "fertilizante.txt");
        mesBuscado = mesBuscado.toLowerCase();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String mes = linea.split(",")[2]; // Suponiendo que el mes está en la tercera columna separada por coma (,)
                if (mes.equalsIgnoreCase(mesBuscado)) {
                    bufferedReader.close();
                    return true; // El mes existe
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // El mes no existe
    }

    /**
     * Guarda los datos en el archivo "fertilizante.txt" si el mes no existe.
     *
     * @param gramos El gramos a guardar.
     * @param precio  El precio a guardar.
     * @param mes     El mes a guardar.
     * @return true si se guarda correctamente, false en caso contrario.
     */
    public boolean guardarDatos(String gramos, String precio, String mes) {
        File file = new File(getFilesDir(), "fertilizante.txt");
        mes = mes.toLowerCase();
        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Crear una instancia de Fertilizante y escribir los datos en el archivo
            Fertilizante fertilizante = new Fertilizante(Float.parseFloat(gramos), Float.parseFloat(precio), mes);
            String linea = String.format(Locale.getDefault(), "%.2f,%.2f,%s", fertilizante.getGramos(), fertilizante.getPrecio(), fertilizante.getMes());
            bufferedWriter.write(linea);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true; // Los datos se guardaron correctamente
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Error al guardar los datos
    }
}