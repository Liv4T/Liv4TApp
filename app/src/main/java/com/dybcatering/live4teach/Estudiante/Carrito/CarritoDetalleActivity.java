package com.dybcatering.live4teach.Estudiante.Carrito;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

public class CarritoDetalleActivity extends AppCompatActivity {
    private TextView itemName;
    private TextView quantity;
    private TextView dateAdded;
    private TextView imageName;
    private TextView tachado;
    private int groceryId;
    private Button btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_detalle);
        itemName = (TextView) findViewById(R.id.itemNameDet);
        quantity = (TextView) findViewById(R.id.quantityDet);
        dateAdded = (TextView) findViewById(R.id.dateAddedDet);
        imageName = findViewById(R.id.quantityImage);
        tachado = findViewById(R.id.tachado);
     //   btnComprar = findViewById(R.id.comprarButton);

		tachado.setPaintFlags(tachado.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
      //      itemName.setText(bundle.getString("name"));
        //    quantity.setText(bundle.getString("quantity"));
          //  imageName.setText(bundle.getString("imagen"));
            dateAdded.setText(bundle.getString("date"));
            groceryId = bundle.getInt("id");
        }

       /* btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CarritoDetalleActivity.this, "prueba toast ", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}