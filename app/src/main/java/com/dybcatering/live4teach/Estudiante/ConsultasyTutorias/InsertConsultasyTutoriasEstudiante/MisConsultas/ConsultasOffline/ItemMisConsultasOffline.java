package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante.MisConsultas.ConsultasOffline;

public class ItemMisConsultasOffline {
	private String Categoria;
	private String Estado;
	private String Id;
	private String Mensaje;
	private String MensajeReceptor;
	private String Receptor;
	private String Remitente;
	private String Hora;
	private String RemitenteEstado;

	public ItemMisConsultasOffline() {
	}

	public ItemMisConsultasOffline(String categoria, String estado, String id, String mensaje, String mensajeReceptor, String receptor, String remitente, String hora, String remitenteEstado) {
		Categoria = categoria;
		Estado = estado;
		Id = id;
		Mensaje = mensaje;
		MensajeReceptor = mensajeReceptor;
		Receptor = receptor;
		Remitente = remitente;
		Hora = hora;
		RemitenteEstado = remitenteEstado;
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

	public String getRemitenteEstado() {
		return RemitenteEstado;
	}

	public void setRemitenteEstado(String remitenteEstado) {
		RemitenteEstado = remitenteEstado;
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
