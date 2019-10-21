package com.dybcatering.live4teach.Estudiante.Carrito;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.dybcatering.live4teach.Estudiante.Carrito.Data.DatabaseHandler;
import com.dybcatering.live4teach.Estudiante.Carrito.Model.Grocery;
import com.dybcatering.live4teach.Estudiante.Carrito.UI.RecyclerViewAdapter;
import com.dybcatering.live4teach.R;

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
//        setSupportActionBar(toolbar);

      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                createPopDialog();


            }
        });
*/
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
            AlertDialog alertDialog = new AlertDialog.Builder(CarritoActivity.this, R.style.Botones).create();
            alertDialog.setTitle("Carrito de compras Vacío");
            alertDialog.setMessage("Parece que aún no has agregado nada al carrito, revisa nuestros cursos disponibles");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
            alertDialog.show();
        }else{
            for (Grocery c : groceryList) {
                Grocery grocery = new Grocery();
                grocery.setName(c.getName());
                grocery.setQuantity("Curso: " + c.getQuantity());
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

    /*private void createPopDialog() {

        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        groceryItem = (EditText) view.findViewById(R.id.groceryItem);
        imagegro = view.findViewById(R.id.groceryImage);
        quantity = (EditText) view.findViewById(R.id.groceryQty);
        saveButton = (Button) view.findViewById(R.id.saveButton);


        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGroceryToDB();
            }
        });


    }

    private void saveGroceryToDB() {

        Grocery grocery = new Grocery();

        String newGrocery = groceryItem.getText().toString();
        String newGroceryQuantity = quantity.getText().toString();
        String newGroceryImage = imagegro.getText().toString();

        grocery.setName(newGrocery);
        grocery.setQuantity(newGroceryQuantity);
        grocery.setImagen(newGroceryImage);

        //Save to DB
        db.addGrocery(grocery);

        //Snackbar.make(v, "Item Saved!", Snackbar.LENGTH_LONG).show();

        // Log.d("Item Added ID:", String.valueOf(db.getGroceriesCount()));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                //start a new activity
                startActivity(new Intent(CarritoActivity.this, CarritoActivity.class));
                finish();
            }
        }, 1200); //  1 second.


    }*/


}
