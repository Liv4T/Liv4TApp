package com.dybcatering.live4teach.Estudiante.CursosDisponibles;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.dybcatering.live4teach.Estudiante.Carrito.CarritoActivity;
import com.dybcatering.live4teach.Estudiante.Carrito.Data.DatabaseHandler;
import com.dybcatering.live4teach.Estudiante.Carrito.Model.Grocery;
import com.dybcatering.live4teach.Estudiante.CursosDisponibles.Adapter.ExpandableListAdapter;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Estudiante.Login.LoginActivity;
import com.dybcatering.live4teach.Estudiante.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.pd.chocobar.ChocoBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CuartoCurso extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private DatabaseHandler databaseHandler;
    private HashMap<String, List<String >> listHashMap;
    public TextView texto_nombre;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto_curso);
        listView = findViewById(R.id.expand_fourth);
      //  snack();
        mostrar();
        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHashMap);
        listView.setAdapter(listAdapter);


        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    listView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        databaseHandler = new DatabaseHandler(this);

    }


    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("¿Qué tipo de contenidos crear para atraer tráfico?");
        listDataHeader.add("¿Qué canales usar según tu tipo de negocio?");
        listDataHeader.add("¿Cómo segmentar tu público según tu negocio?");
        listDataHeader.add("¿Qué es un crecimiento orgánico y uno pago?");
        listDataHeader.add("¿Cómo medir mi presupuesto publicitario?");

        List<String> edmt = new ArrayList<>();
        edmt.add("Lista Expandible");

        List<String> androidstudio = new ArrayList<>();
        androidstudio.add("Expandcible lista");
        androidstudio.add("Google");
        androidstudio.add("Hola");
        androidstudio.add("Chat App Firebase");



        List<String> xamarin= new ArrayList<>();
        xamarin.add("Segunda Expandcible lista");
        xamarin.add("Segunda Google");
        xamarin.add("Segunda Hola");
        xamarin.add("Segunda Chat App Firebase");


        List<String> uwp= new ArrayList<>();
        uwp.add("UWP Expandcible lista");
        uwp.add("UWP Google");
        uwp.add("UWP Hola");
        uwp.add("UWP Chat App Firebase");

        List<String> a = new ArrayList<>();

//        a.add("");
        listHashMap.put(listDataHeader.get(0), edmt);
        listHashMap.put(listDataHeader.get(1), androidstudio);
        listHashMap.put(listDataHeader.get(2), xamarin);
        listHashMap.put(listDataHeader.get(3), uwp);
        listHashMap.put(listDataHeader.get(4), a);




    }



    public void snack(){
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "", Snackbar.LENGTH_INDEFINITE)
                //  Snackbar.make(parentLayout, "OBTÉN UN DESCUENTO DEL 50% EN CURSOS PREMIUM", Snackbar.LENGTH_INDEFINITE)
                .setAction("OBTÉN UN DESCUENTO DEL 50% EN CURSOS PREMIUM", new View.OnClickListener() {
                    //.setAction("", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog = new AlertDialog.Builder(CuartoCurso.this, R.style.Botones).create();
                        alertDialog.setTitle("Curso en promoción");
                        alertDialog.setMessage("Hola tenemos un curso en promoción");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        snack();
                                    }
                                });
                        alertDialog.show();
                    }
                    }).setActionTextColor(Color.parseColor("#FF378F44")).show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.boton_arriba, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

            if (id == R.id.mybutton) {
                // do something here
                sessionManager = new SessionManager(this);
                if (sessionManager.isLoggin()){
                    guardar();
                }else{
                    alert();
                }
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        new CheckInternetConnection(this).checkConnection();
        super.onResume();
    }

    public void guardar() {

        Grocery grocery = new Grocery();
        texto_nombre = findViewById(R.id.txtNombreCursoCuarto);

        //se deben agregar los demas items de la pantalla

        String newGrocery = texto_nombre.getText().toString();
        String newGroceryQuantity = texto_nombre.getText().toString();
        String newGroceryImage = texto_nombre.getText().toString();

        grocery.setName(newGrocery);
        grocery.setQuantity(newGroceryQuantity);
        grocery.setImagen(newGroceryImage);

        //Save to DB
        int cuenta = databaseHandler.contar(newGrocery);
        if (cuenta > 0) {
            new AlertDialog.Builder(CuartoCurso.this, R.style.Botones)
                    .setTitle("Este curso ya fue agregado al carrito de compras")
                    .setMessage("¿Desea ir al carrito de compras?")
                    .setIcon(R.drawable.carrito)
                    .setPositiveButton("Si",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(CuartoCurso.this, CarritoActivity.class);
                                    startActivity(intent);

                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ;
                            dialog.cancel();
                        }
                    }).show();


        } else {

            databaseHandler.addGrocery(grocery);
            new AlertDialog.Builder(CuartoCurso.this, R.style.Botones)
                    .setTitle("Curso Agregado al carrito")
                    .setMessage("¿Ir al carrito de compras?")
                    .setIcon(R.drawable.carrito)
                    .setPositiveButton("Si",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(CuartoCurso.this, CarritoActivity.class);
                                    startActivity(intent);
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).show();
        }

    }

    public void mostrar(){
        ChocoBar.builder().setBackgroundColor(Color.parseColor("#007883"))
                .setTextSize(18)
                .setTextColor(Color.parseColor("#FFFFFF"))
                .setActionClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        AlertDialog alertDialog = new AlertDialog.Builder(CuartoCurso.this, R.style.Botones).create();
                        alertDialog.setTitle("Curso en promoción");
                        alertDialog.setMessage("Hola tenemos un curso en promoción");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        //  guardarBaseDatos();
                                        //Intent intent = new Intent(PrimerCurso.this, CompraActivity.class);
                                        // startActivity(intent);
                                        mostrar();
                                    }
                                });
                        alertDialog.show();
                    }
                })
                //.setText("This is a custom Chocobar")
                .setMaxLines(4)
                .centerText()
                .setActionText("OBTÉN UN DESCUENTO DEL 50% EN CURSOS PREMIUM")
                .setActionTextColor(Color.parseColor("#FFFFFF"))
                .setActionTextSize(20)

                .setActivity(CuartoCurso.this)
                .setDuration(ChocoBar.LENGTH_INDEFINITE)
                .build()
                .show();
    }
    private void alert() {
        final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(this)
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

                        Intent intent = new Intent(CuartoCurso.this, LoginActivity.class);
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
}
