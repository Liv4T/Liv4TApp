package com.dybcatering.live4teach.Estudiante.Liv4T.Pizarra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.graphics.CanvasView;
import com.dybcatering.live4teach.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PizarraFragment extends Fragment {


    public PizarraFragment() {
        // Required empty public constructor
    }
    private CanvasView canvas ;

    private Button texto, undo, redo, negro, rojo, rectangulo, circulo, btnborrador, btnqu, agregarimagen;


    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_pizarra, container, false);
        // Inflate the layout for this fragment

        canvas = myView.findViewById(R.id.canvas);
        texto = myView.findViewById(R.id.btncolor);
        undo = myView.findViewById(R.id.btnundo);
        redo = myView.findViewById(R.id.btnredo);
        negro = myView.findViewById(R.id.btncambianegro);
        rojo = myView.findViewById(R.id.btncambiarojo);
        rectangulo = myView.findViewById(R.id.rectangulo);
        circulo = myView.findViewById(R.id.btncirculo);
        btnborrador = myView.findViewById(R.id.btnborrador);
        btnqu = myView.findViewById(R.id.btnqu);

        texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.TEXT);

// Setter

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Agregar nuevo texto");

                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();

                        canvas.setText(m_Text);
                        canvas.setFontSize(50F);

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                //	canvas.setMode(CanvasView.Mode.DRAW);

            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.DRAW);
                canvas.undo();
            }
        });

        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.DRAW);
                canvas.redo();
            }
        });
        negro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setPaintStrokeWidth(3F);
                canvas.setMode(CanvasView.Mode.DRAW);
                canvas.setDrawer(CanvasView.Drawer.PEN);
                canvas.setPaintStrokeColor(Color.BLACK);
            }
        });
        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setPaintStrokeWidth(3F);
                canvas.setMode(CanvasView.Mode.DRAW);
                canvas.setDrawer(CanvasView.Drawer.PEN);
                canvas.setPaintStrokeColor(Color.RED);
            }
        });
        rectangulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.DRAW);
                canvas.setPaintStrokeColor(Color.BLACK);
                canvas.setPaintStrokeWidth(3F);
                canvas.setDrawer(CanvasView.Drawer.RECTANGLE);
            }
        });

        circulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.DRAW);
                canvas.setPaintStrokeColor(Color.BLACK);
                canvas.setPaintStrokeWidth(3F);
                canvas.setDrawer(CanvasView.Drawer.CIRCLE);
            }
        });
        btnborrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.DRAW);
                canvas.setDrawer(CanvasView.Drawer.PEN);
                canvas.setPaintStrokeColor(Color.WHITE);
                canvas.setPaintStrokeWidth(50F);
            }
        });
        btnqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.DRAW);
                canvas.setDrawer(CanvasView.Drawer.QUADRATIC_BEZIER);
            }
        });
		/*agregarimagen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap bitmap = canvas.getBitmap();

				mImg.setImageBitmap(bitmap);

			}
		});*/



        return myView;
    }

    private File savebitmap(String filename) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;

        File file = new File(filename + ".png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, filename + ".png");
            Log.e("file exist", "" + file + ",Bitmap= " + filename);
        }
        try {
            // make a new bitmap from your file
            Bitmap bitmap = this.canvas.getBitmap();

            //Bitmap bitmap = BitmapFactory.decodeFile(file.getName());

            outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("file", "" + file);
        return file;

    }
}
