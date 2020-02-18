package com.dybcatering.live4teach.Tutor.Calificaciones.Adaptador;

public class ItemCalificaciones {

	private String Curso;
	private String Categoria;
	private String Fecha;

	public ItemCalificaciones(String curso, String categoria, String fecha) {
		Curso = curso;
		Categoria = categoria;
		Fecha = fecha;
	}

	public String getCurso() {
		return Curso;
	}

	public String getCategoria() {
		return Categoria;
	}

	public String getFecha() {
		return Fecha;
	}
}
