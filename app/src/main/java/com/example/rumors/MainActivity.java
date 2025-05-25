package com.example.rumors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumors.Adapters.ListviewRumoresAdapter;
import com.example.rumors.Entidades.Rumor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView lblusuario;
    ListView lstRumores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InicializarControles();
        this.MapearDatos();
    }

    private void MapearDatos() {
        Intent i = getIntent();
        int from = i.getIntExtra("from",-1);
        //ESTO YA NO SE HACE ASI PORQUE VIENE DESDE ARCHIVOS
        /*if(from == 1){
            Rumor rumor = new Rumor(i.getExtras());
            this.LLenarListViewRumores(rumor);
        }*/

        this.LLenarListViewRumores();

        SharedPreferences pref = getSharedPreferences("Session", Context.MODE_PRIVATE);
        lblusuario.setText(pref.getString("user",""));
    }

    private void LLenarListViewRumores() {
        List<Rumor> rumores = new ArrayList<>();
        String[] rawRumores = this.LeerArchivo().split("\\?");

        for(String rawRumor : rawRumores){
            String[] campos = rawRumor.split("\\|");
            Rumor rumor = new Rumor(
                    campos[0],
                    campos[1],
                    campos[2],
                    campos[3],
                    new ArrayList<>()
            );
            rumores.add(rumor);
        }


        ListviewRumoresAdapter adapter = new ListviewRumoresAdapter(getApplicationContext(),rumores);
        lstRumores.setAdapter(adapter);
    }

    private void InicializarControles() {

        lblusuario = (TextView) findViewById(R.id.lblUsuario);
        lstRumores = (ListView) findViewById(R.id.lstRumores);
    }

    public void CerrarSession(View v){
        SharedPreferences pref = getSharedPreferences("Session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("user","");
        editor.putBoolean("loged",false);

        editor.commit();

        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    public void AnadirRumor(View v){
        startActivity(new Intent(getApplicationContext(), CrearRumorActivity.class));
    }

    private String LeerArchivo(){
        String lectura = "";

        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(openFileInput("rumores.txt")));
            lectura = bfr.readLine();
            bfr.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito Lectura => "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return lectura;

    }
}