package com.dybcatering.live4teach.Splash.Estudiante.Perfil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.dybcatering.live4teach.Splash.Estudiante.Inicio.InicioActivity;
import com.dybcatering.live4teach.R;

public class ActualizarDatos extends AppCompatActivity {

    TextView txtNomUp, txtEmailUp, txtTeleUp, txtIdentiUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_actualizar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        txtNomUp = findViewById(R.id.nombre_update);
        txtEmailUp = findViewById(R.id.email_update);
        txtTeleUp = findViewById(R.id.telefono_update);
        txtIdentiUp= findViewById(R.id.identificacion_update);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            txtNomUp.setText(bundle.getString("nombre"));
            txtEmailUp.setText(bundle.getString("correo"));
            txtTeleUp.setText(bundle.getString("telefono"));
            txtIdentiUp.setText(bundle.getString("identificacion"));
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //Fragment selectedFragment = null;
                    Intent intent = new Intent(ActualizarDatos.this, InicioActivity.class);


                    switch (item.getItemId()) {
                        case R.id.nav_cursos_disponibles:
                            startActivity(intent);

                            //selectedFragment = new CursosFragment();
                            break;
                        case R.id.nav_mis_cursos:
                          //  selectedFragment = new MisCursosFragment();
                            startActivity(intent);

                            break;
                        case R.id.nav_mis_calificaciones:

                        //    selectedFragment = new MisCalificacionesFragment();
                            startActivity(intent);

                            break;
                        case R.id.nav_perfil:



                      //      selectedFragment = new PerfilFragment();
                            break;
                    }

                  //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    //        selectedFragment).commit();

                    return true;
                }
            };

}
