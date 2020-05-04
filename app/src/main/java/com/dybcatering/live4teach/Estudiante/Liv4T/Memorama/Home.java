package com.dybcatering.live4teach.Estudiante.Liv4T.Memorama;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homed);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutFragment, new Start());
        transaction.commit();
    }

    int counter = 0;

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.layoutFragment);

        counter++;
        if (currentFragment instanceof Start){
            AlertDialog.Builder exit = new AlertDialog.Builder(this);
            exit.setTitle("¿De verdad quieres empezar?");
            exit.setPositiveButton("Si, por favor.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            exit.setNegativeButton("Umm, puedo quedarme.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Home.this, "Bienvenido de nuevo.", Toast.LENGTH_SHORT).show();
                }
            });

            exit.show();
        }
        else if(currentFragment instanceof Result){
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            super.onBackPressed();
        }
        else{
            super.onBackPressed();
        }

    }
}
