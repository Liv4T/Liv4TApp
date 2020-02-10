package com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra;

public class MisCursosItem {
	private String id;
	private String name;
	private String methodology;
	private String welcome;
	private String intention;
	private String intensityAC;
	private String competences;
	private String intensityTA;
	private String achievement;
	private String indicatorA;
	private String methodologyG;
	private String type;
	private String description;
	private String descriptionO;
	private String updated_at;
	private String state;
	private String image;
	private String video_presentacion;
	private String topic;
	private String publish;
	private String idtemas;

	public MisCursosItem(String id, String name, String methodology, String welcome, String intention, String intensityAC, String competences, String intensityTA, String achievement, String indicatorA, String methodologyG, String type, String description, String descriptionO, String updated_at, String state, String image, String video_presentacion, String topic, String publish, String idtemas) {
		this.id = id;
		this.name = name;
		this.methodology = methodology;
		this.welcome = welcome;
		this.intention = intention;
		this.intensityAC = intensityAC;
		this.competences = competences;
		this.intensityTA = intensityTA;
		this.achievement = achievement;
		this.indicatorA = indicatorA;
		this.methodologyG = methodologyG;
		this.type = type;
		this.description = description;
		this.descriptionO = descriptionO;
		this.updated_at = updated_at;
		this.state = state;
		this.image = image;
		this.video_presentacion = video_presentacion;
		this.topic = topic;
		this.publish = publish;
		this.idtemas = idtemas;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMethodology() {
		return methodology;
	}

	public String getWelcome() {
		return welcome;
	}

	public String getIntention() {
		return intention;
	}

	public String getIntensityAC() {
		return intensityAC;
	}

	public String getCompetences() {
		return competences;
	}

	public String getIntensityTA() {
		return intensityTA;
	}

	public String getAchievement() {
		return achievement;
	}

	public String getIndicatorA() {
		return indicatorA;
	}

	public String getMethodologyG() {
		return methodologyG;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionO() {
		return descriptionO;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public String getState() {
		return state;
	}

	public String getImage() {
		return image;
	}

	public String getVideo_presentacion() {
		return video_presentacion;
	}

	public String getTopic() {
		return topic;
	}

	public String getPublish() {
		return publish;
	}

	public String getIdtemas() {
		return idtemas;
	}
}
