package com.dybcatering.live4teach.Splash.Estudiante.Carrito.Adaptador;

public class CarritoItem {
	private String idusuario;
	private String idcurso;

	public CarritoItem(String idusuario, String idcurso) {
		this.idusuario = idusuario;
		this.idcurso = idcurso;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public String getIdcurso() {
		return idcurso;
	}
}
