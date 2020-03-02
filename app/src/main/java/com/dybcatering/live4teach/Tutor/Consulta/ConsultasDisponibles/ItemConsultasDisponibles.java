package com.dybcatering.live4teach.Tutor.Consulta.ConsultasDisponibles;

public class ItemConsultasDisponibles {
	private String Categoria;
	private String Estado;
	private String Id;
	private String Mensaje;
	private String Receptor;
	private String Remitente;

	public ItemConsultasDisponibles() {
	}

	public ItemConsultasDisponibles(String categoria, String estado, String id, String mensaje, String receptor, String remitente) {
		Categoria = categoria;
		Estado = estado;
		Id = id;
		Mensaje = mensaje;
		Receptor = receptor;
		Remitente = remitente;
	}

	public String getCategoria() {
		return Categoria;
	}

	public String getEstado() {
		return Estado;
	}

	public String getId() {
		return Id;
	}

	public String getMensaje() {
		return Mensaje;
	}

	public String getReceptor() {
		return Receptor;
	}

	public String getRemitente() {
		return Remitente;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}

	public void setReceptor(String receptor) {
		Receptor = receptor;
	}

	public void setRemitente(String remitente) {
		Remitente = remitente;
	}
}
