package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.AdapterDetalleVideosMisCursos;

public class VideosItem {
	String id;
	String id_course;
	String id_courseunit;
	String video;
	String nombre_video;
	String duracion;

	public VideosItem(String id, String id_course, String id_courseunit, String video, String nombre_video, String duracion) {
		this.id = id;
		this.id_course = id_course;
		this.id_courseunit 	= id_courseunit;
		this.video = video;
		this.nombre_video = nombre_video;
		this.duracion = duracion;
	}

	public String getId() {
		return id;
	}

	public String getId_course() {
		return id_course;
	}

	public String getId_courseunit() {
		return id_courseunit;
	}

	public String getVideo() {
		return video;
	}

	public String getNombre_video() {
		return nombre_video;
	}

	public String getDuracion() {
		return duracion;
	}
}
