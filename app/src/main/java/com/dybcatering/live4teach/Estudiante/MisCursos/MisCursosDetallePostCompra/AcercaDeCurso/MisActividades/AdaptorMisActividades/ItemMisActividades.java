package com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades.AdaptorMisActividades;

public class ItemMisActividades {
	private String id;
	private String name;
	private String nombrecurso;
	private String id_user;
	private String activitytype;
	private String duracionestimadaplataforma;
	private String duracionestimadatrabajoautonomo;
	private String contextualizaciondeltema;
	private String actividad;
	private String tiporecursos1;
	private String tiporecursos2;
	private String tiporecursos3;
	private String origenrecurso1;
	private String origenrecurso2;
	private String origenrecurso3;
	private String entregables;
	private String criteriosevaluacion1;
	private String criteriosevaluacion2;
	private String criteriosevaluacion3;
	private String tiempotrabajo;
	private String momentoevaluaciondesde;
	private String momentoevaluacionhasta;
	private String evidenciaenvio;
	private String actorinterviniente;
	private String fecharetroalimentacion;



	public ItemMisActividades(String id, String name, String nombrecurso, String id_user, String activitytype, String estimated_duration_platform, String estimated_duration_autonomous_work, String theme_contextualization, String activity, String type_resources_1, String type_resources_2, String type_resources_3, String origin_resource1, String origin_resource2, String origin_resource3, String deliverables, String evaluation_criteria1, String evaluation_criteria2, String evaluation_criteria3, String work_time, String moment_evaluation_from, String moment_evaluation_up, String evidence_send, String intervening_actor, String feedback_date) {
		this.id = id;
		this.name = name;
		this.nombrecurso = nombrecurso;
		this.id_user = id_user;
		this.activitytype = activitytype;
		this.duracionestimadaplataforma = estimated_duration_platform;
		this.duracionestimadatrabajoautonomo = estimated_duration_autonomous_work;
		this.contextualizaciondeltema = theme_contextualization;
		this.actividad = activity;
		this.tiporecursos1 = type_resources_1;
		this.tiporecursos2 = type_resources_2;
		this.tiporecursos3 = type_resources_3;
		this.origenrecurso1 = origin_resource1;
		this.origenrecurso2 = origin_resource2;
		this.origenrecurso3 = origin_resource3;
		this.entregables = deliverables;
		this.criteriosevaluacion1 = evaluation_criteria1;
		this.criteriosevaluacion2 = evaluation_criteria2;
		this.criteriosevaluacion3 = evaluation_criteria3;
		this.tiempotrabajo = work_time;
		this.momentoevaluaciondesde = moment_evaluation_from;
		this.momentoevaluacionhasta = moment_evaluation_up;
		this.evidenciaenvio = evidence_send;
		this.actorinterviniente = intervening_actor;
		this.fecharetroalimentacion = feedback_date;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNombrecurso() {
		return nombrecurso;
	}

	public String getId_user() {
		return id_user;
	}

	public String getActivitytype() {
		return activitytype;
	}

	public String getDuracionestimadaplataforma() {
		return duracionestimadaplataforma;
	}

	public String getDuracionestimadatrabajoautonomo() {
		return duracionestimadatrabajoautonomo;
	}

	public String getContextualizaciondeltema() {
		return contextualizaciondeltema;
	}

	public String getActividad() {
		return actividad;
	}

	public String getTiporecursos1() {
		return tiporecursos1;
	}

	public String getTiporecursos2() {
		return tiporecursos2;
	}

	public String getTiporecursos3() {
		return tiporecursos3;
	}

	public String getOrigenrecurso1() {
		return origenrecurso1;
	}

	public String getOrigenrecurso2() {
		return origenrecurso2;
	}

	public String getOrigenrecurso3() {
		return origenrecurso3;
	}

	public String getEntregables() {
		return entregables;
	}

	public String getCriteriosevaluacion1() {
		return criteriosevaluacion1;
	}

	public String getCriteriosevaluacion2() {
		return criteriosevaluacion2;
	}

	public String getCriteriosevaluacion3() {
		return criteriosevaluacion3;
	}

	public String getTiempotrabajo() {
		return tiempotrabajo;
	}

	public String getMomentoevaluaciondesde() {
		return momentoevaluaciondesde;
	}

	public String getMomentoevaluacionhasta() {
		return momentoevaluacionhasta;
	}



	public String getEvidenciaenvio() {
		return evidenciaenvio;
	}

	public String getActorinterviniente() {
		return actorinterviniente;
	}

	public String getFecharetroalimentacion() {
		return fecharetroalimentacion;
	}
}
