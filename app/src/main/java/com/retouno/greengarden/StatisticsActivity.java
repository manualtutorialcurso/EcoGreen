package com.retouno.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.retouno.greengarden.modelos.Agua;
import com.retouno.greengarden.modelos.Electricidad;
import com.retouno.greengarden.modelos.Fertilizante;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //Elementos de la interfaz
        Button botonRegresar = findViewById(R.id.btnReturn);
        tableLayout = findViewById(R.id.myTableLayout);
        Intent intent = new Intent(this, PrincipalActivity.class);

        //Carga de los archivos de agua y electricidad
        File aguaFile = new File(getFilesDir(), "agua.txt");
        File electricidadFile = new File(getFilesDir(), "electricidad.txt");
        File fertilizanteFile = new File(getFilesDir(), "fertilizante.txt");

        //Listas de Agua y Electricidad
        List<Agua> listaAgua = leerArchivoAgua(aguaFile);
        List<Electricidad> listaElectricidad = leerArchivoElectricidad(electricidadFile);
        List<Fertilizante> listaFertilizante = leerArchivoFertilizante(fertilizanteFile);

        //Crear la Tabla
        addElementAgua(listaAgua);
        addElementElectricidad(listaElectricidad);
        addElementFertilizante(listaFertilizante);
        addPromedioAgua(listaAgua);
        addPromedioElectricidad(listaElectricidad);
        addPromedioFertilizante(listaFertilizante);

        //Boton Regresar
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    private void addPromedioAgua(List<Agua> aguaList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        float promedioConsumoAgua = calcularPromedioVolumenAgua(aguaList);
        float promedioPrecioAgua = calcularPromedioPrecioAgua(aguaList);

        TableRow row = new TableRow(this);
        //AÑADE LA INFORMACIÓN A LA CELDA 1
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(10, 10, 10, 10);
        cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
        // color deseado

        //AÑADE LA INFORMACIÓN A LA CELDA 2
        TextView cell2 = new TextView(this);
        cell2.setText("Agua");
        cell2.setPadding(10, 10, 10, 10);
        cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


        //AÑADE LA INFORMACIÓN A LA CELDA 3
        TextView cell3 = new TextView(this);
        cell3.setText(String.valueOf(promedioConsumoAgua));
        cell3.setPadding(10, 10, 10, 10);
        cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        //AÑADE LA INFORMACIÓN A LA CELDA 4
        TextView cell4 = new TextView(this);
        cell4.setText(String.valueOf(promedioPrecioAgua));
        cell4.setPadding(10, 10, 10, 10);
        cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        // Agrega las celdas a la fila
        row.addView(cell1);
        row.addView(cell2);
        row.addView(cell3);
        row.addView(cell4);
        // Agrega la fila al TableLayout
        tableLayout.addView(row);
    }

    private void addPromedioElectricidad(List<Electricidad> electricidadList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        float promedioConsumoElectricidad = calcularPromedioKilovatios(electricidadList);
        float promedioPrecioElectricidad = calcularPromedioPrecioElectricidad(electricidadList);

        TableRow row = new TableRow(this);
        //AÑADE LA INFORMACIÓN A LA CELDA 1
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(10, 10, 10, 10);
        cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
        // color deseado

        //AÑADE LA INFORMACIÓN A LA CELDA 2
        TextView cell2 = new TextView(this);
        cell2.setText("electricidad");
        cell2.setPadding(10, 10, 10, 10);
        cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


        //AÑADE LA INFORMACIÓN A LA CELDA 3
        TextView cell3 = new TextView(this);
        cell3.setText(String.valueOf(promedioConsumoElectricidad));
        cell3.setPadding(10, 10, 10, 10);
        cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        //AÑADE LA INFORMACIÓN A LA CELDA 4
        TextView cell4 = new TextView(this);
        cell4.setText(String.valueOf(promedioPrecioElectricidad));
        cell4.setPadding(10, 10, 10, 10);
        cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        // Agrega las celdas a la fila
        row.addView(cell1);
        row.addView(cell2);
        row.addView(cell3);
        row.addView(cell4);
        // Agrega la fila al TableLayout
        tableLayout.addView(row);
    }


    private void addPromedioFertilizante(List<Fertilizante> fertilizanteList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        float promedioConsumoFertilizante = calcularPromedioVolumenFertilizante(fertilizanteList);
        float promedioPrecioFertilizante = calcularPromedioPrecioFertilizante(fertilizanteList);

        TableRow row = new TableRow(this);
        //AÑADE LA INFORMACIÓN A LA CELDA 1
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(10, 10, 10, 10);
        cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
        // color deseado

        //AÑADE LA INFORMACIÓN A LA CELDA 2
        TextView cell2 = new TextView(this);
        cell2.setText("Fertilizante");
        cell2.setPadding(10, 10, 10, 10);
        cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


        //AÑADE LA INFORMACIÓN A LA CELDA 3
        TextView cell3 = new TextView(this);
        cell3.setText(String.valueOf(promedioConsumoFertilizante));
        cell3.setPadding(10, 10, 10, 10);
        cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        //AÑADE LA INFORMACIÓN A LA CELDA 4
        TextView cell4 = new TextView(this);
        cell4.setText(String.valueOf(promedioPrecioFertilizante));
        cell4.setPadding(10, 10, 10, 10);
        cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        // Agrega las celdas a la fila
        row.addView(cell1);
        row.addView(cell2);
        row.addView(cell3);
        row.addView(cell4);
        // Agrega la fila al TableLayout
        tableLayout.addView(row);
    }


    private void addElementAgua(List<Agua> aguaList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        for (Agua item: aguaList) {
            // Crea una nueva fila y agrega las celdas
            TableRow row = new TableRow(this);
            //AÑADE LA INFORMACIÓN A LA CELDA 1
            TextView cell1 = new TextView(this);
            cell1.setText(item.getMes());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
            // color deseado

            //AÑADE LA INFORMACIÓN A LA CELDA 2
            TextView cell2 = new TextView(this);
            cell2.setText("Agua");
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


            //AÑADE LA INFORMACIÓN A LA CELDA 3
            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf((item.getVolumen())));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            //AÑADE LA INFORMACIÓN A LA CELDA 4
            TextView cell4 = new TextView(this);
            cell4.setText(String.valueOf((item.getPrecio())));
            cell4.setPadding(10, 10, 10, 10);
            cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            // Agrega las celdas a la fila
            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            row.addView(cell4);
            // Agrega la fila al TableLayout
            tableLayout.addView(row);
        }
    }

    private void addElementElectricidad(List<Electricidad> electricidadList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        for (Electricidad item: electricidadList) {
            // Crea una nueva fila y agrega las celdas
            TableRow row = new TableRow(this);
            //AÑADE LA INFORMACIÓN A LA CELDA 1
            TextView cell1 = new TextView(this);
            cell1.setText(item.getMes());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
            // color deseado

            //AÑADE LA INFORMACIÓN A LA CELDA 2
            TextView cell2 = new TextView(this);
            cell2.setText("Electricidad");
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


            //AÑADE LA INFORMACIÓN A LA CELDA 3
            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf((item.getKilovatios())));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            //AÑADE LA INFORMACIÓN A LA CELDA 4
            TextView cell4 = new TextView(this);
            cell4.setText(String.valueOf((item.getPrecio())));
            cell4.setPadding(10, 10, 10, 10);
            cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            // Agrega las celdas a la fila
            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            row.addView(cell4);
            // Agrega la fila al TableLayout
            tableLayout.addView(row);
        }
    }


    private void addElementFertilizante(List<Fertilizante> fertilizanteList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        for (Fertilizante item: fertilizanteList) {
            // Crea una nueva fila y agrega las celdas
            TableRow row = new TableRow(this);
            //AÑADE LA INFORMACIÓN A LA CELDA 1
            TextView cell1 = new TextView(this);
            cell1.setText(item.getMes());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
            // color deseado

            //AÑADE LA INFORMACIÓN A LA CELDA 2
            TextView cell2 = new TextView(this);
            cell2.setText("Fertilizante");
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


            //AÑADE LA INFORMACIÓN A LA CELDA 3
            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf((item.getGramos())));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            //AÑADE LA INFORMACIÓN A LA CELDA 4
            TextView cell4 = new TextView(this);
            cell4.setText(String.valueOf((item.getPrecio())));
            cell4.setPadding(10, 10, 10, 10);
            cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            // Agrega las celdas a la fila
            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            row.addView(cell4);
            // Agrega la fila al TableLayout
            tableLayout.addView(row);
        }
    }

    private static List<Agua> leerArchivoAgua(File archivo) {
        List<Agua> listaAgua = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float volumen = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String mes = datos[2];

                Agua agua = new Agua(volumen, precio, mes);
                listaAgua.add(agua);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaAgua;
    }

    private static List<Electricidad> leerArchivoElectricidad(File archivo) {
        List<Electricidad> listaElectricidad = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float kilovatios = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String mes = datos[2];

                Electricidad electricidad = new Electricidad(kilovatios, precio, mes);
                listaElectricidad.add(electricidad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaElectricidad;
    }

    private static List<Fertilizante> leerArchivoFertilizante(File archivo) {
        List<Fertilizante> listaFertilizante = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float gramos = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String mes = datos[2];

                Fertilizante fertilizante = new Fertilizante(gramos, precio, mes);
                listaFertilizante.add(fertilizante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaFertilizante;
    }

    private float calcularPromedioVolumenAgua(List<Agua> aguaList) {
        float sum = 0;
        for (Agua item : aguaList) {
            sum += item.getVolumen();
        }
        return sum / aguaList.size();
    }
    private float calcularPromedioPrecioAgua(List<Agua> aguaList) {
        float sum = 0;
        for (Agua item : aguaList) {
            sum += item.getPrecio();
        }
        return sum / aguaList.size();
    }

    private float calcularPromedioKilovatios(List<Electricidad> electricidadList) {
        float sum = 0;
        for (Electricidad item : electricidadList) {
            sum += item.getKilovatios();
        }
        return sum / electricidadList.size();
    }

    private float calcularPromedioPrecioElectricidad(List<Electricidad> electricidadList) {
        float sum = 0;
        for (Electricidad item : electricidadList) {
            sum += item.getPrecio();
        }
        return sum / electricidadList.size();
    }

    private float calcularPromedioVolumenFertilizante(List<Fertilizante> fertilizanteList) {
        float sum = 0;
        for (Fertilizante item : fertilizanteList) {
            sum += item.getGramos();
        }
        return sum / fertilizanteList.size();
    }
    private float calcularPromedioPrecioFertilizante(List<Fertilizante> fertilizanteList) {
        float sum = 0;
        for (Fertilizante item : fertilizanteList) {
            sum += item.getPrecio();
        }
        return sum / fertilizanteList.size();
    }

}