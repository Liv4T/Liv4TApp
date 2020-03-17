package com.dybcatering.live4teach.Estudiante.Inicio;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dybcatering.live4teach.ConferenciaOnline.ConferenciaOnline;
import com.dybcatering.live4teach.Estudiante.Carrito.CarritoActivity;
import com.dybcatering.live4teach.Estudiante.Carrito.Data.DatabaseHandler;
import com.dybcatering.live4teach.Estudiante.CategoriasCursos.CategoriasCursos;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.ConsultasEstudiante;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante.InsertConsultasyTutorias;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Login.LoginActivity;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.Estudiante.CursosDisponibles.CursosFragment;
import com.dybcatering.live4teach.Estudiante.MisCalificaciones.MisCalificacionesFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosFragment;
import com.dybcatering.live4teach.Estudiante.Perfil.PerfilFragment;
import com.dybcatering.live4teach.R;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class InicioActivity extends AppCompatActivity {
    public DatabaseHandler db;
    TextView textCartItemCount, version;

    SessionManager sessionManager;

    DatabaseReference databaseReference;
    ValueEventListener seenListener;

    String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        new CheckInternetConnection(InicioActivity.this).checkConnection();
        sessionManager = new SessionManager(InicioActivity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        if (sessionManager.getFirstTime()) {
            //tap target view
            tapview();
            sessionManager.setFirstTime(false);
        }
        valor = user.get(SessionManager.UUID);
        String myRefreshedToken = FirebaseInstanceId.getInstance().getToken();
        changeToken(myRefreshedToken, valor);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new CategoriasCursos()).commit();
        }
        db = new DatabaseHandler(this);
        sessionManager = new SessionManager(this);


    }
    private void tapview() {
        new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.nav_cursos_disponibles), "Cursos Disponibles", "Aquí encontrarás todos nuestros cursos disponibles")
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
                        TapTarget.forView(findViewById(R.id.nav_mis_cursos), "Mis Cursos", "Encontrarás todos los cursos que has agregado ")
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
                        TapTarget.forView(findViewById(R.id.nav_consultas), "Consultas", "Aqui tendrás las consultas y tutorias disponibles, tanto offline como online")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.gen_black)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.colorText)
                                .drawShadow(true)
                                .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.colorPrimaryDark),
                        TapTarget.forView(findViewById(R.id.nav_mis_calificaciones), "Mis Calificaciones", "Tendrás el acceso a las calificaciones para todas tus actividades realizadas")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.gen_black)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.colorText)
                                .drawShadow(true)
                                .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.colorPrimaryDark),
                        TapTarget.forView(findViewById(R.id.nav_perfil), "Mi Perfil", "Tienes el acceso a tu perfil, donde podrás cambiar tu foto de perfil y revisar tus datos básicos")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.gen_black)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.colorText)
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.colorPrimaryDark))

                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        sessionManager.setFirstTime(false);
                        Toasty.success(InicioActivity.this, "Estás listo para iniciar  !", Toast.LENGTH_SHORT).show();
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


    public void changeToken(final String token, final String uuid){


//        databaseReference = FirebaseDatabase.getInstance().getReference("Tokens");

        Query query = FirebaseDatabase.getInstance().getReference("Tokens")
                .orderByChild(uuid)
                .equalTo(uuid);

        seenListener = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Token tokenUsuario = new Token(snapshot.getValue(Token.class));
                    if (dataSnapshot.exists()){
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put(uuid,token);
                        snapshot.getRef().updateChildren(hashMap);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_cursos_disponibles:

                            selectedFragment = new CategoriasCursos();
                            break;
                        case R.id.nav_mis_cursos:
                            if (sessionManager.isLoggin()){
                                selectedFragment = new MisCursosFragment();
                            }else{
                                mostraralerta();
                                selectedFragment = new CursosFragment();
                            }
                            break;
                        case R.id.nav_consultas:
                            if (sessionManager.isLoggin()){
                                //selectedFragment = new ConsultasEstudiante();
                               selectedFragment = new InsertConsultasyTutorias();
                            }else{
                                mostraralerta();
                                selectedFragment = new CursosFragment();
                            }

                            break;

                        case R.id.nav_mis_calificaciones:
                            if (sessionManager.isLoggin()){

                                selectedFragment = new MisCalificacionesFragment();
                            }else{

                                mostraralerta();
                                selectedFragment = new CursosFragment();
                            }
                            break;
                        case R.id.nav_perfil:
                            if (sessionManager.isLoggin()){

                                selectedFragment = new PerfilFragment();
                            }else {
                                mostraralerta();
                                selectedFragment = new CursosFragment();
                            }
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

                        Intent intent = new Intent(InicioActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(InicioActivity.this, CarritoActivity.class);
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

        int id = item.getItemId();

        if (id == R.id.action_cart) {


            //mBadge.setNumber(++count);
            //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*@Override
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
