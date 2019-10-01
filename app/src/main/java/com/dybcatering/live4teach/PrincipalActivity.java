package com.dybcatering.live4teach;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dybcatering.live4teach.CursosDisponibles.Cursos;
import com.dybcatering.live4teach.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.MisActividades.MisActividades;
import com.dybcatering.live4teach.MisCalificaciones.MisCalificaciones;
import com.dybcatering.live4teach.MisCertificados.MisCertificados;
import com.dybcatering.live4teach.MisCursos.MisCursos;
import com.dybcatering.live4teach.MisCursos.MisCursosDetalle;
import com.dybcatering.live4teach.Perfil.Perfil;
import com.nex3z.notificationbadge.NotificationBadge;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView textCartItemCount, version;
    SessionManager sessionManager;
    NotificationBadge mBadge;
    private int count =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


       // sessionManager = new SessionManager(this);
        //sessionManager.checkLogin();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        new CheckInternetConnection(this).checkConnection();

        inicio();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);
        View header = navigationView.getHeaderView(0);
        version =  header.findViewById(R.id.txtVersion);

        version.setText("Versión: "+ BuildConfig.VERSION_NAME);
    }



    public void inicio(){
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new Cursos())
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_cart);


        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
                String str = String.valueOf(count++);
                textCartItemCount.setText(str);
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
          //  mBadge.setNumber(++count);
            //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_perfil) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Perfil())
                    .commit();
            //Intent intent = new Intent(PrincipalActivity.this, SubPrimerActivity.class);
            // startActivity(intent);


        } else if (id == R.id.nav_cursos) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Cursos())
                    .commit();


        } else if (id == R.id.nav_mis_cursos) {

            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MisCursos())
                    .commit();

        } else if (id == R.id.nav_calificaciones) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MisCalificaciones())
                    .commit();


            //  Intent intent = new Intent(PrincipalActivity.this, SubPrimerActivity.class);
           //  startActivity(intent);
        } else if (id == R.id.nav_certificados) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MisCertificados())
                    .commit();

        } else if (id == R.id.nav_slideshow){
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MisActividades())
                    .commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        new CheckInternetConnection(this).checkConnection();
        super.onResume();
    }

}
