package com.dybcatering.live4teach.Tutor.Actividades.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ActivityRequest extends StringRequest {

	private static final String REGISTER_URL = "https://dybcatering.com/back_live_app/miscursos/misactividades/tutor/insertaractividad.php";
	private Map<String, String> parameters;
	public ActivityRequest (String stSpinnerCurso, String stSpinnerUnidad, String id_usuario, String stSpinnerTipoActividad, String stTiempoestimado, String stTrabajoautonomo, String stContextualizacion,
							String stActividad, String stTipoRecursos1, String stTipoRecursos2, String stTipoRecursos3, String stOrigenRecursos1, String stOrigenRecursos2, String stOrigenRecursos3,
							String stentregables, String stcriteriosevaluacion1, String stcriteriosevaluacion2, String stcriteriosevaluacion3, Response.Listener<String> listener) {
		super(Request.Method.POST, REGISTER_URL, listener, null);
		parameters = new HashMap<>();

		parameters.put("NombreCurso", stSpinnerCurso);
		parameters.put("NombreUnidad", stSpinnerUnidad);
		parameters.put("id_user", id_usuario);
		parameters.put("TipoActividad", stSpinnerTipoActividad);
		parameters.put("estimated_duration_platform", stTiempoestimado);
		parameters.put("estimated_duration_autonomous_work", stTrabajoautonomo);
		parameters.put("theme_contextualization", stContextualizacion);
		parameters.put("activity", stActividad);
		parameters.put("type_resources_1", stTipoRecursos1);
		parameters.put("type_resources_2", stTipoRecursos2);
		parameters.put("type_resources_3", stTipoRecursos3);
		parameters.put("origin_resource1", stOrigenRecursos1);
		parameters.put("origin_resource2", stOrigenRecursos2);
		parameters.put("origin_resource3", stOrigenRecursos3);
		parameters.put("deliverables", stentregables);
		parameters.put("evaluation_criteria1", stcriteriosevaluacion1);
		parameters.put("evaluation_criteria2", stcriteriosevaluacion2);
		parameters.put("evaluation_criteria3", stcriteriosevaluacion3);

	}
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return parameters;
	}
}
