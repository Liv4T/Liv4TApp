package com.dybcatering.live4teach.Tutor.MisCursos.Adaptador;

public class ItemMisCursosTutor {
	private String Id;
	private String Name;
	private String Id_category;
	private String Id_subcategory;
	private String Methodology;
	private String Welcome;
	private String Intention;
	private String IntensityAC;
	private String Competences;
	private String IntensityTA;
	private String Achievement;
	private String IndicatorA;
	private String Map;
	private String MehodologyG;
	private String Type;
	private String Description;
	private String Presentation;
	private String Id_user;
	private String DescriptionO;
	private String Updated_at;
	private String Created_at;
	private String State;
	private String Publish;
	private String Image;
	private String Price;
	private String Video_presentation;
	private String Totalinscritos;

	public ItemMisCursosTutor(String id, String name, String id_category, String id_subcategory, String methodology, String welcome, String intention, String intensityAC, String competences, String intensityTA, String achievement, String indicatorA, String map, String mehodologyG, String type, String description, String presentation, String id_user, String descriptionO, String updated_at, String created_at, String state, String publish, String image, String price, String video_presentation, String totalinscritos) {
		Id = id;
		Name = name;
		Id_category = id_category;
		Id_subcategory = id_subcategory;
		Methodology = methodology;
		Welcome = welcome;
		Intention = intention;
		IntensityAC = intensityAC;
		Competences = competences;
		IntensityTA = intensityTA;
		Achievement = achievement;
		IndicatorA = indicatorA;
		Map = map;
		MehodologyG = mehodologyG;
		Type = type;
		Description = description;
		Presentation = presentation;
		Id_user = id_user;
		DescriptionO = descriptionO;
		Updated_at = updated_at;
		Created_at = created_at;
		State = state;
		Publish = publish;
		Image = image;
		Price = price;
		Video_presentation = video_presentation;
		Totalinscritos = totalinscritos;
	}

	public String getId() {
		return Id;
	}

	public String getName() {
		return Name;
	}

	public String getId_category() {
		return Id_category;
	}

	public String getId_subcategory() {
		return Id_subcategory;
	}

	public String getMethodology() {
		return Methodology;
	}

	public String getWelcome() {
		return Welcome;
	}

	public String getIntention() {
		return Intention;
	}

	public String getIntensityAC() {
		return IntensityAC;
	}

	public String getCompetences() {
		return Competences;
	}

	public String getIntensityTA() {
		return IntensityTA;
	}

	public String getAchievement() {
		return Achievement;
	}

	public String getIndicatorA() {
		return IndicatorA;
	}

	public String getMap() {
		return Map;
	}

	public String getMehodologyG() {
		return MehodologyG;
	}

	public String getType() {
		return Type;
	}

	public String getDescription() {
		return Description;
	}

	public String getPresentation() {
		return Presentation;
	}

	public String getId_user() {
		return Id_user;
	}

	public String getDescriptionO() {
		return DescriptionO;
	}

	public String getUpdated_at() {
		return Updated_at;
	}

	public String getCreated_at() {
		return Created_at;
	}

	public String getState() {
		return State;
	}

	public String getPublish() {
		return Publish;
	}

	public String getImage() {
		return Image;
	}

	public String getPrice() {
		return Price;
	}

	public String getVideo_presentation() {
		return Video_presentation;
	}

	public String getTotalinscritos() {
		return Totalinscritos;
	}
}
