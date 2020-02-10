package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades.Cuestionario;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dybcatering.live4teach.R;
import com.geniusforapp.fancydialog.FancyAlertDialog;

import java.util.Random;

public class CuestionarioActivity extends AppCompatActivity {

	TextView puntaje, pregunta;

	Button Respuesta1, Respuesta2, Respuesta3, Respuesta4;

	private Preguntas mPreguntas = new Preguntas();

	private String mRespuesta;
	private int mCorrectas = 0;
	private int mIncorrectas = 0;
	private int mPreguntaTamaño = mPreguntas.mPregunta.length;

	Random random;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuestionario);

		Toolbar toolbar = findViewById(R.id.toolbarJuego);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Descripción del curso");
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				CuestionarioActivity.this.onBackPressed();
			}
		});

		puntaje= findViewById(R.id.txtPuntaje);
		pregunta = findViewById(R.id.txtPregunta);

		Respuesta1= findViewById(R.id.btnprimerpregunta);
		Respuesta2= findViewById(R.id.btnsegundapregunta);
		Respuesta3= findViewById(R.id.btntercerapregunta);
		Respuesta4= findViewById(R.id.btncuartapregunta);

		random = new Random();

		actualizarPregunta(random.nextInt(mPreguntaTamaño));

		Respuesta1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mCorrectas <= 9){
					if (Respuesta1.getText() == mRespuesta){
						mCorrectas++;
						puntaje.setText("Puntaje "+ mCorrectas);
						actualizarPregunta(random.nextInt(mPreguntaTamaño));
					} else	{
						mIncorrectas++;
						actualizarPregunta(random.nextInt(mPreguntaTamaño));
					}
				}else{
					finDelJuego(mCorrectas);
				}

			}
		});

		Respuesta2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mCorrectas <= 9){
					if (Respuesta2.getText() == mRespuesta){
						mCorrectas++;
						puntaje.setText("Puntaje "+ mCorrectas);
						actualizarPregunta(random.nextInt(mPreguntaTamaño));
					} else	{
						mIncorrectas++;
						actualizarPregunta(random.nextInt(mPreguntaTamaño));
					}
				}else{
					finDelJuego(mCorrectas);
				}

			}
		});

		Respuesta3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mCorrectas <= 9){
					if (Respuesta3.getText() == mRespuesta){
						mCorrectas++;
						puntaje.setText("Puntaje "+ mCorrectas);
						actualizarPregunta(random.nextInt(mPreguntaTamaño));
					} else	{
						mIncorrectas++;
						actualizarPregunta(random.nextInt(mPreguntaTamaño));
					}
				}else{
					finDelJuego(mCorrectas);
				}

			}
		});

		Respuesta4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mCorrectas <= 9){
					if (Respuesta4.getText() == mRespuesta){
						mCorrectas++;
						puntaje.setText("Puntaje "+ mCorrectas);
						actualizarPregunta(random.nextInt(mPreguntaTamaño));
					} else	{
						mIncorrectas++;
						actualizarPregunta(random.nextInt(mPreguntaTamaño));
					}
				}else{
					finDelJuego(mCorrectas);
				}

			}
		});
	}

	private void finDelJuego(int correctas) {

		if (correctas >3){
			final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(CuestionarioActivity.this)
					.setBackgroundColor(R.color.white)
					.setimageResource(R.drawable.internetconnection)
					.setTextTitle("Buen Trabajo")
					.setTextSubTitle("Tu puntaje fue "  + mCorrectas + " puntos")

					.setPositiveButtonText("Nuevo juego")
					.setPositiveColor(R.color.colorbonton)
					.setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
						@Override
						public void OnClick(View view, Dialog dialog) {

							startActivity(new Intent(getApplicationContext(), CuestionarioActivity.class));
						}
					})
					.setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
					.setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
					.setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
					.setCancelable(false)
					.setNegativeButtonText("Cerrar")
					.setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
						@Override
						public void OnClick(View view, Dialog dialog) {
							CuestionarioActivity.this.onBackPressed();;
						}
					})
					.build();
			alert.show();
		}else{

		}


	}

	private void actualizarPregunta(int num) {

		pregunta.setText(mPreguntas.getPregunta(num));
		Respuesta1.setText(mPreguntas.getEleccion1(num));
		Respuesta2.setText(mPreguntas.getEleccion2(num));
		Respuesta3.setText(mPreguntas.getEleccion3(num));
		Respuesta4.setText(mPreguntas.getEleccion4(num));

		mRespuesta = mPreguntas.getRespuestaCorrecta(num);
	}
}
