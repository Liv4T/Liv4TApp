package com.dybcatering.live4teach.Tutor;

import android.app.Dialog;
import android.content.Intent;
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

import com.dybcatering.live4teach.Estudiante.Carrito.CarritoActivity;
import com.dybcatering.live4teach.Estudiante.Carrito.Data.DatabaseHandler;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Login.LoginActivity;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.Inicio.InicioTutor;
import com.dybcatering.live4teach.Tutor.Liv4T.Perfil.PerfilTutor;
import com.dybcatering.live4teach.Tutor.MisCursos.MisCursosTutorFragment;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class InicioActivityTutor extends AppCompatActivity {
    public DatabaseHandler db;
    TextView textCartItemCount;

    SessionManager sessionManager;

    DatabaseReference databaseReference;
    ValueEventListener seenListener;

    String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_tutor);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_tutor);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        new CheckInternetConnection(InicioActivityTutor.this).checkConnection();
        sessionManager = new SessionManager(InicioActivityTutor.this);
		if (sessionManager.getFirstTime()) {
			//tap target view
			tapview();
			sessionManager.setFirstTime(false);
		}
        HashMap<String, String> user = sessionManager.getUserDetail();
        valor = user.get(SessionManager.UUID);
        String myRefreshedToken = FirebaseInstanceId.getInstance().getToken();
     //   changeToken(myRefreshedToken, valor);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new InicioTutor()).commit();
        }
        db = new DatabaseHandler(this);
        sessionManager = new SessionManager(this);
        String username = user.get(SessionManager.USER_NAME);

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_cursos_disponibles:
                      //     if (sessionManager.isLoggin()){

                              selectedFragment = new InicioTutor();
                        //    }else {
                          //    mostraralerta();
                           // selectedFragment = new PerfilFragmentTutor();
                            //}
                            break;
                        case R.id.nav_mis_calificaciones:
                          //     if (sessionManager.isLoggin()){
                            selectedFragment = new MisCursosTutorFragment();
                           //  }else{
                            //    mostraralerta();
                             // selectedFragment = new CursosFragment();
                             // }
                            break;
                        case R.id.nav_perfil:

                            selectedFragment = new PerfilTutor();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).addToBackStack("tag").commit();

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



    private void tapview() {
        new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.nav_cursos_disponibles), "Mi perfil", "Tienes el acceso a tu perfil, donde podrás cambiar tu foto de perfil y revisar tus datos básicos")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.gen_black)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.colorText)
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.colorPrimaryDark),
                        TapTarget.forView(findViewById(R.id.nav_mis_calificaciones), "Mis Cursos", "Encontrarás todos los cursos que has agregado ")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.gen_black)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.colorText)
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.colorPrimaryDark),
                        TapTarget.forView(findViewById(R.id.nav_perfil), "Consultas y Tutorias", "Encontrarás tutorias Online Y Offline enviadas por los estudiantes")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.gen_black)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.colorText)
                                .drawShadow(true)
                                .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.colorPrimaryDark))

                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        sessionManager.setFirstTime(false);
                        Toasty.success(InicioActivityTutor.this, "Estás listo para iniciar  !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                    }
                }).start();
    }

   /* @Override
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
    }*/
}
