package com.dybcatering.live4teach.Estudiante.CursosDisponibles;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrimerCurso extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String >> listHashMap;
    public ImageView image;
    private DatabaseHandler databaseHandler;
    public TextView texto_nombre, texto_descripcion, texto_monto;
    private SliderLayout sliderShow;

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer_curso);

            new CheckInternetConnection(this).checkConnection();
            inflateImageSlider();

            //image = findViewById(R.id.imDesc);
            //Picasso.with(PrimerCurso.this).load("http://192.168.1.101/imagenes/primer_curso.jpg").fit().into(image);
           // snack();
            mostrar();
            listView = (ExpandableListView)findViewById(R.id.expand);
            initData();
            listAdapter = new ExpandableListAdapter(this, listDataHeader, listHashMap);
            listView.setAdapter(listAdapter);

            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Toast.makeText(PrimerCurso.this, "hola", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.boton_arriba, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            sessionManager = new SessionManager(this);
            if (sessionManager.isLoggin()){
                saveGroceryToDB();
            }else{
                alert();
            }
        }
        return super.onOptionsItemSelected(item);
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

                        Intent intent = new Intent(PrimerCurso.this, LoginActivity.class);
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


    public void mostrar(){
        ChocoBar.builder().setBackgroundColor(Color.parseColor("#007883"))
                .setTextSize(18)
                .setTextColor(Color.parseColor("#FFFFFF"))
                .setActionClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        AlertDialog alertDialog = new AlertDialog.Builder(PrimerCurso.this, R.style.Botones).create();
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

                .setActivity(PrimerCurso.this)
                .setDuration(ChocoBar.LENGTH_INDEFINITE)
                .build()
                .show();
    }

    @Override
    protected void onResume() {
        new CheckInternetConnection(this).checkConnection();
        super.onResume();
    }

    public void saveGroceryToDB() {

        Grocery grocery = new Grocery();
        texto_nombre= findViewById(R.id.txtNombreCurso);
        String newGrocery = texto_nombre.getText().toString();
        String newGroceryQuantity = texto_nombre.getText().toString();
        String newGroceryImage = texto_nombre.getText().toString();

        grocery.setName(newGrocery);
        grocery.setQuantity(newGroceryQuantity);
        grocery.setImagen(newGroceryImage);

        //Save to DB
        int cuenta = databaseHandler.contar(newGrocery);
        if (cuenta>0){
            new AlertDialog.Builder(PrimerCurso.this, R.style.Botones)
                    .setTitle("Este curso ya fue agregado al carrito de compras")
                    .setMessage("¿Desea ir al carrito de compras?")
                    .setIcon(R.drawable.carrito)
                    .setPositiveButton("Si",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(PrimerCurso.this, CarritoActivity.class);
                                    startActivity(intent);

                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {;
                            dialog.cancel();
                        }
                    }).show();


        }else{

            databaseHandler.addGrocery(grocery);
            new AlertDialog.Builder(PrimerCurso.this, R.style.Botones)
                    .setTitle("Curso Agregado al carrito")
                    .setMessage("¿Ir al carrito de compras?")
                    .setIcon(R.drawable.carrito)
                    .setPositiveButton("Si",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(PrimerCurso.this, CarritoActivity.class);
                                    startActivity(intent);
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {;
                            dialog.cancel();
                        }
                    }).show();


        }

    }



    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = findViewById(R.id.slider_primer_curso);

        //populating Image slider
        //ArrayList<String> sliderImages= new ArrayList<>();
        //sliderImages.add("https://dev-res.thumbr.io/libraries/27/08/11/lib/1469777955350_1.jpg?size=854x493s&ext=jpg");
        //sliderImages.add("http://192.168.1.101/imagenes/cover2.png");
        //sliderImages.add("http://digitalandroidservices.com/personal/cover1.jpg");
        //sliderImages.add("http://192.168.1.101/imagenes/cover3.png");
        //sliderImages.add("https://dev-res.thumbr.io/libraries/27/08/11/lib/1469777955350_1.jpg?size=854x493s&ext=jpg");

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Descripción 1", "http://digitalandroidservices.com/personal/cover1.jpg");
        url_maps.put("Descripción 2", "http://digitalandroidservices.com/personal/cover2.png");
        url_maps.put("Descripción 3", "http://digitalandroidservices.com/personal/cover3.png");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Descripción 2",R.drawable.cover1);
        file_maps.put("Descripción 1",R.drawable.cover2);
        file_maps.put("Descripción 3",R.drawable.cover3);

        for (String s:url_maps.keySet()){
          //  DefaultSliderView sliderView=new DefaultSliderView(PrimerCurso.this);
           // sliderView.image(s);
           // sliderShow.addSlider(sliderView);
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(s)
                    .image(file_maps.get(s))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",s);

            sliderShow.addSlider(textSliderView);

        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
    }


}
