package com.dybcatering.live4teach.Splash.Tutor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dybcatering.live4teach.Splash.Estudiante.Carrito.CarritoActivity;
import com.dybcatering.live4teach.Splash.Estudiante.Carrito.Data.DatabaseHandler;
import com.dybcatering.live4teach.Login.LoginActivity;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.PrincipalActivity;
import com.dybcatering.live4teach.Splash.Tutor.Actividades.MisActividadesTutorFragment;
import com.dybcatering.live4teach.Splash.Tutor.Calificaciones.CalificacionesTutorFragment;
import com.dybcatering.live4teach.Splash.Tutor.Consulta.ConsultaTutorFragment;
import com.dybcatering.live4teach.Splash.Tutor.MisCursos.MisCursosTutorFragment;
import com.dybcatering.live4teach.Splash.Tutor.Perfil.PerfilFragmentTutor;
import com.geniusforapp.fancydialog.FancyAlertDialog;

public class InicioActivityTutor extends AppCompatActivity {
    public DatabaseHandler db;
    TextView textCartItemCount;

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_tutor);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_tutor);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PerfilFragmentTutor()).commit();
        }
        db = new DatabaseHandler(this);
        sessionManager = new SessionManager(this);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_perfil_tutor:
                      //     if (sessionManager.isLoggin()){

                              selectedFragment = new PerfilFragmentTutor();
                        //    }else {
                          //    mostraralerta();
                           // selectedFragment = new PerfilFragmentTutor();
                            //}
                            break;
                        case R.id.nav_mis_cursos_tutor:
                          //     if (sessionManager.isLoggin()){
                            selectedFragment = new MisCursosTutorFragment();
                           //  }else{
                            //    mostraralerta();
                             // selectedFragment = new CursosFragment();
                             // }
                            break;
                        case R.id.nav_consultas_tutor:

                            selectedFragment = new ConsultaTutorFragment();
                            break;
                        case R.id.nav_calificaciones_tutor:
                             //if (sessionManager.isLoggin()){

                            selectedFragment = new CalificacionesTutorFragment();
                            //}else{
                            // mostraralerta();
                            //selectedFragment = new CursosFragment();
                            //}
                            break;


                        case R.id.nav_actividades_tutor:
                            selectedFragment = new MisActividadesTutorFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    private void mostraralerta() {
      FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(this)
                .setBackgroundColor(R.color.white)
                //.setimageResource(R.drawable.internetconnection)
                .setTextTitle("Alerta")
                .setTextSubTitle("Para continuar es necesario iniciar sesión")
                //.setBody("Iniciar Sesión ")
                .setPositiveButtonText("Aceptar")
                .setPositiveColor(R.color.colorbonton)
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {

                        Intent intent = new Intent(InicioActivityTutor.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                .setCancelable(false)
                .build();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_cart);


        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);
        final String total = Integer.toString(db.contartotal());


        textCartItemCount.setText(total);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivityTutor.this, CarritoActivity.class);
                startActivity(intent);

                 /*if (total.equals("0")){
                    AlertDialog alertDialog = new AlertDialog.Builder(PrincipalActivity.this, R.style.Botones).create();
                    alertDialog.setTitle("Carrito de compras Vacío");
                    alertDialog.setMessage("Parece que aún no has agregado nada al carrito, revisa nuestros cursos disponibles");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else{


                }*/
                // onOptionsItemSelected(menuItem);
                // String str = String.valueOf(count++);

                //    textCartItemCount.setText(str);
                //setupBadge();

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {


            //mBadge.setNumber(++count);
            //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(this)
                .setBackgroundColor(R.color.white)
                //.setimageResource(R.drawable.internetconnection)
                .setTextTitle("Información")
                .setTextSubTitle("¿Deseas Salir de la Aplicación?")
                .setCancelable(false)
                //.setBody("Iniciar Sesión ")
                .setPositiveButtonText("Aceptar")
                .setPositiveColor(R.color.colorbonton)
                .setNegativeButtonText("Cancelar")
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {

                        finish();
                    }
                })
                .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();
                    }
                })
                .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                .setCancelable(false)
                .build();
        alert.show();
    }
}
