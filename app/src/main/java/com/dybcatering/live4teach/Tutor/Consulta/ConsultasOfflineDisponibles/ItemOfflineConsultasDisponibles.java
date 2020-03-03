package com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles;

public class ItemOfflineConsultasDisponibles {
	private String Categoria;
	private String Estado;
	private String Id;
	private String Mensaje;
	private String MensajeReceptor;
	private String Receptor;
	private String Remitente;
	private String Hora;

	public ItemOfflineConsultasDisponibles() {
	}

	public ItemOfflineConsultasDisponibles(String categoria, String estado, String id, String mensaje, String mensajeReceptor, String receptor, String remitente, String hora) {
		Categoria = categoria;
		Estado = estado;
		Id = id;
		Mensaje = mensaje;
		MensajeReceptor = mensajeReceptor;
		Receptor = receptor;
		Remitente = remitente;
		Hora = hora;
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

	public String getMensajeReceptor() {
		return MensajeReceptor;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public void setMensajeReceptor(String mensajeReceptor) {
		MensajeReceptor = mensajeReceptor;
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
