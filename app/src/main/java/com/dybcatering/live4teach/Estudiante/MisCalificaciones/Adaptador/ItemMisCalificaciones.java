package com.dybcatering.live4teach.Estudiante.MisCalificaciones.Adaptador;

public class ItemMisCalificaciones {
	private String Idcurso;
	private String Idactividad;
	private String Actividad;
	private String Tipoactividad;
	private String Lastdate;
	private String Date;
	private String Total;

	public ItemMisCalificaciones(String idcurso, String idactividad, String actividad, String tipoactividad, String lastdate, String date, String total) {
		Idcurso = idcurso;
		Idactividad = idactividad;
		Actividad = actividad;
		Tipoactividad = tipoactividad;
		Lastdate = lastdate;
		Date = date;
		Total = total;
	}

	public String getIdcurso() {
		return Idcurso;
	}

	public String getIdactividad() {
		return Idactividad;
	}

	public String getActividad() {
		return Actividad;
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

	public String getTotal() {
		return Total;
	}
}
