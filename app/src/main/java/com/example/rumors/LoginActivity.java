package com.example.rumors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario, txtContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        this.InicializarControles();
        this.ValidarSession();
    }

    private void ValidarSession() {
        SharedPreferences pref = getSharedPreferences("Session", Context.MODE_PRIVATE);
        boolean loged = pref.getBoolean("loged",false);
        if(loged)
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void InicializarControles() {
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtContrasena = (EditText) findViewById(R.id.txtContrasena);
    }

    public void IniciarSesion(View v){

        //ESTOS DATOS, DEBEN PROVENIR DE UNA FUENTE DE DATOS
        String user = "kakaroto";
        String pass = "123*";

        try {
            String userEntrante = txtUsuario.getText().toString();
            String passEntrante = txtContrasena.getText().toString();

            if(user.equals(userEntrante)){
                if(user.equals(userEntrante) && pass.equals(passEntrante)){
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    //YA NO ES NECESARIO, PORQUE TRABAJAMOS CON SHARED PREFS
                    //i.putExtra("from",0);
                    //i.putExtra("u",userEntrante);

                    SharedPreferences pref = getSharedPreferences("Session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("user",userEntrante);
                    editor.putBoolean("loged",true);

                    editor.commit();

                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"TA LOCO MI NINO, TAN MAL TUS CREDENCIALES", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"TA LOCO MI NINO, ESE USUARIO NO EXITE", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito =>"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void HacerLlamada(View v){
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:2553311"));
        startActivity(i);
    }
    public void RecuperarContrasena(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://ecampus.utp.ac.pa"));
        startActivity(i);
    }
}