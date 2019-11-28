package com.dybcatering.live4teach.Estudiante.Carrito;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dybcatering.live4teach.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CarritoDetalleActivity extends AppCompatActivity {
    private TextView itemName, quantity, dateAdded, imageName, tachado;
    private int groceryId;
    private Button btnComprar;

    private ImageView imgcarritodetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_detalle);
        itemName = (TextView) findViewById(R.id.itemNameDet);
        quantity = (TextView) findViewById(R.id.quantityDet);
        dateAdded = (TextView) findViewById(R.id.dateAddedDet);
        imageName = findViewById(R.id.quantityImage);
        tachado = findViewById(R.id.tachado);
        imgcarritodetalle = findViewById(R.id.imgcarritodetalle);
     //   btnComprar = findViewById(R.id.comprarButton);

		tachado.setPaintFlags(tachado.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
      //      itemName.setText(bundle.getString("name"));
        //    quantity.setText(bundle.getString("quantity"));
            Picasso.with(this).load(bundle.getString("imagen"))
                    .placeholder(R.drawable.internetconnection).fit().into(imgcarritodetalle, new Callback() {
                @Override public    void onSuccess() {}
                @Override public void onError() {}
            });

           // imageName.setText(bundle.getString("imagen"));
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