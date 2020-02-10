package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades.Cuestionario;

public class Preguntas {

	public String mPregunta []={

			"Esta es la primera pregunta, requiero la respuesta",
			"Esta es la segunda requiero la respuesta",
			"Esta es la tercera pregunta, requiero la respuesta",
			"Esta es la cuarta pregunta, requiero la respuesta",
			"Esta es la quinta pregunta, requiero la respuesta",
			"Esta es la sexta pregunta, requiero la respuesta",
			"Esta es la septima pregunta, requiero la respuesta",
			"Esta es la octava pregunta, requiero la respuesta",
			"Esta es la novena pregunta, requiero la respuesta",
			"Esta es la decima pregunta, requiero la respuesta",
	};

	public String mEleccion [][] = {
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},
			{"Respuesta1","Respuesta2", "Respuesta3", "Respuesta4"},

	};

	public String mRespuestaCorrecta[] = {"Respuesta1", "Respuesta2", "Respuesta3", "Respuesta4", "Respuesta3", "Respuesta2", "Respuesta1", "Respuesta2", "Respuesta3","Respuesta4"};

	public String getPregunta(int a ){
		String pregunta  = mPregunta[a];
		return pregunta;
	}

	public String getEleccion1(int a){
		String eleccion  = mEleccion[a][0];
		return  eleccion;
	}
	public String getEleccion2(int a){
		String eleccion  = mEleccion[a][1];
		return  eleccion;
	}
	public String getEleccion3(int a){
		String eleccion  = mEleccion[a][2];
		return  eleccion;
	}
	public String getEleccion4(int a){
		String eleccion  = mEleccion[a][3];
		return  eleccion;
	}

	public String getRespuestaCorrecta(int a){
		String pregunta = mRespuestaCorrecta[a];
		return pregunta;
	}


}
