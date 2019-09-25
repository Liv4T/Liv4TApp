package com.dybcatering.live4teach.CursosDisponibles;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dybcatering.live4teach.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.R;

public class CompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        new CheckInternetConnection(this).checkConnection();

        String data = prefs.getString("string_id", "no data"); //no id: default value
        if (data.equals("no data")){
            AlertDialog alertDialog = new AlertDialog.Builder(CompraActivity.this, R.style.Botones).create();
            alertDialog.setTitle("No has agregado un curso");
            alertDialog.setMessage("Por favor ingresa un nuevo curso");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            alertDialog.show();
        }else{

            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(data);
            Toast.makeText(this, "el valor nuevo es "+data, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        new CheckInternetConnection(this).checkConnection();
        super.onResume();
    }


    @Override
    protected void onRestart() {
        new CheckInternetConnection(this).checkConnection();
        super.onRestart();
    }

}
