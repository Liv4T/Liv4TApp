package com.dybcatering.live4teach.Estudiante.MisCalificaciones.Adaptador;

public class ItemMisCalificaciones {
	private String NombreCurso;
	private String Actividad;
	private String UserName;
	private String Tipoactividad;
	private String Lastdate;
	private String Date;
	private String SubTotal;
	private String Total;
	private String Observacion;
	private String ActualizadoDate;


	public ItemMisCalificaciones(String nombrecurso, String actividad, String userName, String tipoactividad, String lastdate, String date, String subTotal, String total, String observacion, String actualizadoDate) {
		NombreCurso= nombrecurso;
		Actividad = actividad;
		UserName = userName;
		Tipoactividad = tipoactividad;
		Lastdate = lastdate;
		Date = date;
		SubTotal = subTotal;
		Total = total;
		Observacion = observacion;
		ActualizadoDate = actualizadoDate;
	}

	public String getNombreCurso() {
		return NombreCurso;
	}

	public String getActividad() {
		return Actividad;
	}

	public String getUserName() {
		return UserName;
	}

	public String getTipoactividad() {
		return Tipoactividad;
	}

	public String getLastdate() {
		return Lastdate;
	}

	public String getDate() {
		return Date;
	}

	public String getSubTotal() {
		return SubTotal;
	}

	public String getTotal() {
		return Total;
	}

	public String getObservacion() {
		return Observacion;
	}

	public String getActualizadoDate() {
		return ActualizadoDate;
	}
}
