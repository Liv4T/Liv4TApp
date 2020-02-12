package com.dybcatering.live4teach.Tutor.Actividades.AdaptadorActividadesTutor;

public class ItemAdaptadorActividadesTutor {
	private String Id;
	private String NombreCurso;
	private String Name;
	private String IdUsuario;
	private String ActivityType;
	private String EstimatedDurationPlatform;
	private String EstimatedDurationAutonomousWork;
	private String Theme_contextualization;
	private String Activity;
	private String Type_resources_1;
	private String Type_resources_2;
	private String Type_resources_3;
	private String Origin_resourses_1;
	private String Origin_resourses_2;
	private String Origin_resourses_3;
	private String Deliverables;
	private String Evaluation_criteria1;
	private String Evaluation_criteria2;
	private String Evaluation_criteria3;
	private String Work_time;
	private String Moment_evaluation_from;
	private String Moment_evaluation_up;
	private String Evidence_send;
	private String Intervening_actor;
	private String Feedback_date;
	private String Name_category;

	public ItemAdaptadorActividadesTutor(String id, String nombreCurso, String name, String idUsuario, String activityType, String estimatedDurationPlatform, String estimatedDurationAutonomousWork, String theme_contextualization, String activity, String type_resources_1, String type_resources_2, String type_resources_3, String origin_resourses_1, String origin_resourses_2, String origin_resourses_3, String deliverables, String evaluation_criteria1, String evaluation_criteria2, String evaluation_criteria3, String work_time, String moment_evaluation_from, String moment_evaluation_up, String evidence_send, String intervening_actor, String feedback_date, String name_category) {
		Id = id;
		NombreCurso = nombreCurso;
		Name = name;
		IdUsuario = idUsuario;
		ActivityType = activityType;
		EstimatedDurationPlatform = estimatedDurationPlatform;
		EstimatedDurationAutonomousWork = estimatedDurationAutonomousWork;
		Theme_contextualization = theme_contextualization;
		Activity = activity;
		Type_resources_1 = type_resources_1;
		Type_resources_2 = type_resources_2;
		Type_resources_3 = type_resources_3;
		Origin_resourses_1 = origin_resourses_1;
		Origin_resourses_2 = origin_resourses_2;
		Origin_resourses_3 = origin_resourses_3;
		Deliverables = deliverables;
		Evaluation_criteria1 = evaluation_criteria1;
		Evaluation_criteria2 = evaluation_criteria2;
		Evaluation_criteria3 = evaluation_criteria3;
		Work_time = work_time;
		Moment_evaluation_from = moment_evaluation_from;
		Moment_evaluation_up = moment_evaluation_up;
		Evidence_send = evidence_send;
		Intervening_actor = intervening_actor;
		Feedback_date = feedback_date;
		Name_category = name_category;
	}

	public String getId() {
		return Id;
	}

	public String getNombreCurso() {
		return NombreCurso;
	}

	public String getName() {
		return Name;
	}

	public String getIdUsuario() {
		return IdUsuario;
	}

	public String getActivityType() {
		return ActivityType;
	}

	public String getEstimatedDurationPlatform() {
		return EstimatedDurationPlatform;
	}

	public String getEstimatedDurationAutonomousWork() {
		return EstimatedDurationAutonomousWork;
	}

	public String getTheme_contextualization() {
		return Theme_contextualization;
	}

	public String getActivity() {
		return Activity;
	}

	public String getType_resources_1() {
		return Type_resources_1;
	}

	public String getType_resources_2() {
		return Type_resources_2;
	}

	public String getType_resources_3() {
		return Type_resources_3;
	}

	public String getOrigin_resourses_1() {
		return Origin_resourses_1;
	}

	public String getOrigin_resourses_2() {
		return Origin_resourses_2;
	}

	public String getOrigin_resourses_3() {
		return Origin_resourses_3;
	}

	public String getDeliverables() {
		return Deliverables;
	}

	public String getEvaluation_criteria1() {
		return Evaluation_criteria1;
	}

	public String getEvaluation_criteria2() {
		return Evaluation_criteria2;
	}

	public String getEvaluation_criteria3() {
		return Evaluation_criteria3;
	}

	public String getWork_time() {
		return Work_time;
	}

	public String getMoment_evaluation_from() {
		return Moment_evaluation_from;
	}

	public String getMoment_evaluation_up() {
		return Moment_evaluation_up;
	}

	public String getEvidence_send() {
		return Evidence_send;
	}

	public String getIntervening_actor() {
		return Intervening_actor;
	}

	public String getFeedback_date() {
		return Feedback_date;
	}

	public String getName_category() {
		return Name_category;
	}
}
