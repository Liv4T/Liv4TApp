package com.dybcatering.live4teach.Tutor.EditorTexto;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.graphics.CanvasView;
import com.dybcatering.live4teach.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class EditorTextoActivity extends AppCompatActivity {

	private CanvasView canvas ;

	private Button texto, undo, redo, negro, rojo, rectangulo, circulo, btnborrador, btnqu, btnguardarimagen;

	ImageView mImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor_texto);
		canvas = findViewById(R.id.canvas);
		texto = findViewById(R.id.btncolor);
		undo = findViewById(R.id.btnundo);
		redo = findViewById(R.id.btnredo);
		negro = findViewById(R.id.btncambianegro);
		rojo = findViewById(R.id.btncambiarojo);
		rectangulo = findViewById(R.id.rectangulo);
		circulo = findViewById(R.id.btncirculo);
		btnborrador = findViewById(R.id.btnborrador);
		btnqu = findViewById(R.id.btnqu);
		btnguardarimagen = findViewById(R.id.btnguardarimagen);



		texto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setMode(CanvasView.Mode.TEXT);

// Setter

				AlertDialog.Builder builder = new AlertDialog.Builder(EditorTextoActivity.this);
				builder.setTitle("Agregar nuevo texto");

				final EditText input = new EditText(EditorTextoActivity.this);
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
		btnguardarimagen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GuardarDatos();

			}
		});


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

	public void GuardarDatos(){
		Bitmap bitmap = this.canvas.getBitmap();


		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(getFilesDir() + "/req_images");
		myDir.mkdirs();
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		String fname = "Image-" + n + ".jpg";
		File file = new File(myDir, fname);
		Toast.makeText(this, "El archivo quedo guardado en: " + file, Toast.LENGTH_SHORT).show();//Log.i(TAG, "" + file);
		if (file.exists())
			file.delete();
		try {
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
