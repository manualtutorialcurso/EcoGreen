package com.retouno.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.retouno.greengarden.modelos.Usuario;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Obtener referencias a los elementos de la interfaz
        Button botonRetroceso = findViewById(R.id.btnReturn);
        final Intent intent1 = new Intent(this, LoginActivity.class);
        Button botonRegistro = findViewById(R.id.btnRegister);
        final EditText nombre = findViewById(R.id.inputFirstName);
        final EditText apellido = findViewById(R.id.inputLastName);
        final EditText correo = findViewById(R.id.inputMail);
        final EditText nickname = findViewById(R.id.inputNameUser);
        final EditText password1 = findViewById(R.id.inputRegPassword);
        final EditText password2 = findViewById(R.id.inputRepeatPassword);
        CheckBox terminosCondiciones = findViewById(R.id.checkBoxTerminos);
        CheckBox tratamientoDatos = findViewById(R.id.checkBoxData);

        /**
         * Botón para registrar usuarios
         */
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (terminosCondiciones.isChecked() && tratamientoDatos.isChecked()) {
                    // El RadioButton está activado

                    // Revisar si los campos están diligenciados
                    if (!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !correo.getText().toString().isEmpty() &&
                            !nickname.getText().toString().isEmpty() && !password1.getText().toString().isEmpty() && !password2.getText().toString().isEmpty()) {

                        // Revisar si las contraseñas coinciden

                        if (password1.getText().toString().equals(password2.getText().toString())) {
                            // Validar si los datos ya existen en el archivo
                            if (datosExisten(correo.getText().toString(), nickname.getText().toString(), nombre.getText().toString())) {
                                // Los datos ya existen
                                Toast.makeText(getApplicationContext(), "El correo, usuario o nickname ya existen", Toast.LENGTH_SHORT).show();
                            } else {
                                // Los datos no existen, realizar el registro
                                // Crear un nuevo objeto Usuario
                                Usuario nuevoUsuario = new Usuario(nombre.getText().toString(), apellido.getText().toString(), correo.getText().toString(), nickname.getText().toString(), password1.getText().toString());
                                // Guardar los datos en el archivo
                                guardarRegistro(nuevoUsuario);
                                // Ir al activity de inicio de sesión
                                startActivity(intent1);
                            }
                        } else {
                            // Las contraseñas no coinciden
                            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Los campos estan vacios
                        Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacíos",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // El RadioButton no está activado
                    Toast.makeText(getApplicationContext(), "Debe aceptar los términos y condiciones, " +
                            "tratamiento de datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * Botón para retroceder
         */
        botonRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });
    }

    /**
     * Verifica si los datos de usuario ya existen en el archivo
     *
     * @param correo        Correo electrónico del usuario
     * @param nickname      Nombre de usuario
     * @param nombreUsuario Nombre completo del usuario
     * @return true si los datos existen, false en caso contrario
     */
    private boolean datosExisten(String correo, String nickname, String nombreUsuario) {
        File file = new File(getFilesDir(), "datos.txt");

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            List<String> existingEmails = new ArrayList<>();
            List<String> existingNicknames = new ArrayList<>();
            List<String> existingNombresUsuarios = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                existingEmails.add(data[1]);
                existingNicknames.add(data[2]);
                existingNombresUsuarios.add(data[0]);
            }

            bufferedReader.close();

            return existingEmails.contains(correo) || existingNicknames.contains(nickname) || existingNombresUsuarios.contains(nombreUsuario);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Guarda el nuevo registro de usuario en el archivo
     * @param nuevoUsuario Objeto Usuario con los datos del nuevo usuario
     */
    private void guardarRegistro(Usuario nuevoUsuario) {
        File file = new File(getFilesDir(), "datos.txt");

        try {
            FileWriter writer = new FileWriter(file, true); // El segundo parámetro "true" indica que se agregará al final del archivo existente
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String nuevoRegistro = nuevoUsuario.getNombre() + "," + nuevoUsuario.getCorreo() + "," +
                    nuevoUsuario.getNickname() + "," + nuevoUsuario.getPassword() + "\n";
            bufferedWriter.write(nuevoRegistro);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}