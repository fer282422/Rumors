package com.example.rumors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rumors.Entidades.Rumor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CrearRumorActivity extends AppCompatActivity {

    EditText txtTitulo, txtDescripcion;
    Spinner spnCategoria;
    RadioGroup rgbTipoRumor;
    CheckBox chkFoto, chkVideo, chkPeriodico, chkRedes;

    private String nombreArchivo = "rumores.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_rumor);
        
        this.InicializarControles();
    }

    private void InicializarControles() {
        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);

        spnCategoria = (Spinner) findViewById(R.id.spnCategoria);
        this.LLenarSpinner();
        rgbTipoRumor = (RadioGroup) findViewById(R.id.rgbTipoRumor);

        chkVideo = (CheckBox) findViewById(R.id.chkVideo);
        chkPeriodico = (CheckBox) findViewById(R.id.chkPeriodico);
        chkFoto = (CheckBox) findViewById(R.id.chkFoto);
        chkRedes = (CheckBox) findViewById(R.id.chkRedes);

    }

    private void LLenarSpinner() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Protestas");
        categorias.add("Internacionales");
        categorias.add("Nacionales");
        categorias.add("Deporte");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,categorias);
        spnCategoria.setAdapter(adapter);
    }

    public void GuardarRumor(View v){
        try {
            String tipoRumor = this.ObtenerTipoRumor();
            List<String> detalles = this.ObtederDetallesSeleccionados();

            Rumor rumor = new Rumor(
                    txtTitulo.getText().toString(),
                    txtDescripcion.getText().toString(),
                    spnCategoria.getSelectedItem().toString(),
                    tipoRumor,
                    detalles
            );
            Toast.makeText(getApplicationContext(),"Rumor Guardado Correctamente", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            //NO ES NECESARIO PORQUE YA GUARDAMOS LOS RUMORES POR ARCHIVOS
            //i.putExtra("from",1);
            //i.putExtras(rumor.toBundle());

            this.EscribirArchivo(rumor.getCategoria()+"|"+rumor.getTitulo()+"|"+rumor.getDescripcion()+"|"+rumor.getTipo());

            startActivity(i);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito =>"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private List<String> ObtederDetallesSeleccionados() {
        List<String> detalles = new ArrayList<>();

        if(chkRedes.isChecked())
            detalles.add(chkRedes.getText().toString());

        if(chkVideo.isChecked())
            detalles.add(chkVideo.getText().toString());

        if(chkPeriodico.isChecked())
            detalles.add(chkPeriodico.getText().toString());

        if(chkFoto.isChecked())
            detalles.add(chkFoto.getText().toString());

        return detalles;
    }

    private String ObtenerTipoRumor() {
        if(rgbTipoRumor.getCheckedRadioButtonId() == R.id.rbtMentira)
            return "Mentira";
        else if(rgbTipoRumor.getCheckedRadioButtonId() == R.id.rbtCasiVerdad)
            return "Casi verdad";
        else
            return "Confirmado";
    }

    private String LeerArchivo(){
        String lectura = "";

        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(openFileInput(nombreArchivo)));
            lectura = bfr.readLine();
            bfr.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito Lectura => "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return lectura;

    }

    private void EscribirArchivo(String rumor){
        try {
            String datosExistentes = this.LeerArchivo();
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(nombreArchivo, Context.MODE_PRIVATE));

            writer.write(rumor+"?"+datosExistentes);
            writer.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito Escritura => "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}