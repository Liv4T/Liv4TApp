package com.dybcatering.live4teach.Splash.Estudiante.Carrito;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dybcatering.live4teach.Splash.Estudiante.Carrito.Data.DatabaseHandler;
import com.dybcatering.live4teach.Splash.Estudiante.Carrito.Model.Grocery;
import com.dybcatering.live4teach.Splash.Estudiante.Carrito.UI.RecyclerViewAdapter;
import com.dybcatering.live4teach.R;
import com.geniusforapp.fancydialog.FancyAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Grocery> groceryList;
    private List<Grocery> listItems;
    private DatabaseHandler db;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private EditText groceryItem;
    private EditText quantity;
    private EditText imagegro;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito); Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        db = new DatabaseHandler(this);

       // Toast.makeText(this, db.getGroceriesCount(), Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groceryList = new ArrayList<>();
        listItems = new ArrayList<>();

        // Get items from database
        groceryList = db.getAllGroceries();

        if (groceryList.isEmpty()){

            mostraralerta();
        }else{
            for (Grocery c : groceryList) {
                Grocery grocery = new Grocery();
                grocery.setId(c.getId());
                grocery.setName(c.getName());
                grocery.setQuantity("Descripción: " + c.getQuantity());
                grocery.setId(c.getId());
                grocery.setDateItemAdded("Agregado en: " + c.getDateItemAdded());
                grocery.setImagen(""+ c.getImagen());


                listItems.add(grocery);

            }
        }


        recyclerViewAdapter = new RecyclerViewAdapter(this, listItems);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }



    private void mostraralerta() {
        FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(this)
                .setBackgroundColor(R.color.white)
                //.setimageResource(R.drawable.internetconnection)
                .setTextTitle("Carrito de compras Vacío")
                .setTextSubTitle("Parece que aún no has agregado nada al carrito, revisa nuestros cursos disponibles")
                .setCancelable(false)
                //.setBody("Iniciar Sesión ")
                .setPositiveButtonText("Aceptar")
                .setPositiveColor(R.color.colorbonton)
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {

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
