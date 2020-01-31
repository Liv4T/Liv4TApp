package com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.Carrito.CarritoActivity;
import com.dybcatering.live4teach.Splash.Estudiante.Carrito.Data.DatabaseHandler;
import com.dybcatering.live4teach.Splash.Estudiante.Carrito.Model.Grocery;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.AdapterUnidadesSinComprar.UnidadesAdaptor;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.AdapterUnidadesSinComprar.UnidadesItem;
import com.pd.chocobar.ChocoBar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAACTUALIZADOEN;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRABIENVENIDA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRACATEGORIA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRACOMPENTENCIAS;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRACREADOEN;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRADESCRIPCION;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRADESCRIPCIONO;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAESTADO;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAIDUSER;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAIMAGEN;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAINDICADORA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAINTENSIDAD;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAINTENSIDADAC;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAINTENSIDADTA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRALOGRO;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAMAPA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAMETODOLOGIA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAMETODOLOGIAG;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRANOMBRE;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAPRECIO;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAPRESENTACION;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAPUBLICADO;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRASUBCATEGORIA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRATIPO;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAVIDEOPRESENTACION;
import static com.squareup.picasso.Picasso.Priority.HIGH;

public class CursosDetalle extends AppCompatActivity {

	ImageView imagen_curso;

	TextView txtNombreCurso, txtDescripcion, txtIntension, txtCompetencias, txtImagen;

	TextView  segunda_desc, tercera_desc;
	ProgressBar progressBar;
	ImageButton show, hide, show2, hide2, show3, hide3;
	private DatabaseHandler databaseHandler;
	private RecyclerView mRecyclerView;
	private UnidadesAdaptor mUnidadesAdaptor;
	private ArrayList<UnidadesItem> munidadesItems;
	private RequestQueue mRequestQueue;
	private static String URL_READ = "https://dybcatering.com/back_live_app/cursos/cursos_unidades.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cursos_detalle);
		mostrar();
		imagen_curso = findViewById(R.id.imagen_curso);
		txtNombreCurso = findViewById(R.id.txtNombreCurso);
		txtDescripcion = findViewById(R.id.txtDescripcion);
		txtIntension = findViewById(R.id.description_text);
		txtCompetencias = findViewById(R.id.txtCompetencias);
		txtImagen = findViewById(R.id.textImagen);
		show = findViewById(R.id.show);
		hide = findViewById(R.id.hide);
		segunda_desc =findViewById(R.id.segunda_descripcion);
		show2 = findViewById(R.id.vermassegundo);
		hide2 = findViewById(R.id.hide2);
		tercera_desc = findViewById(R.id.tercera_descripcion);
		show3= findViewById(R.id.vermastercero);
		hide3 = findViewById(R.id.hide3);
		Intent intent= getIntent();
		final String nombre = intent.getStringExtra(EXTRANOMBRE);
		final String categoria = intent.getStringExtra(EXTRACATEGORIA);
		final String subcategoria = intent.getStringExtra(EXTRASUBCATEGORIA);
		final String metodologia= intent.getStringExtra(EXTRAMETODOLOGIA);
		final String bienvenida= intent.getStringExtra(EXTRABIENVENIDA);
		final String intensidad= intent.getStringExtra(EXTRAINTENSIDAD);
		final String intensidadac = intent.getStringExtra(EXTRAINTENSIDADAC);
		final String competencias = intent.getStringExtra(EXTRACOMPENTENCIAS);
		final String intensidadta= intent.getStringExtra(EXTRAINTENSIDADTA);
		final String logro = intent.getStringExtra(EXTRALOGRO);
		final String indicadora = intent.getStringExtra(EXTRAINDICADORA);
		final String mapa = intent.getStringExtra(EXTRAMAPA);
		final String metodologiag = intent.getStringExtra(EXTRAMETODOLOGIAG);
		final String tipo = intent.getStringExtra(EXTRATIPO);
		final String descripcion= intent.getStringExtra(EXTRADESCRIPCION);
		final String presentacion= intent.getStringExtra(EXTRAPRESENTACION);
		final String iduser= intent.getStringExtra(EXTRAIDUSER);
		final String descripciono= intent.getStringExtra(EXTRADESCRIPCIONO);
		final String actualizadoen= intent.getStringExtra(EXTRAACTUALIZADOEN);
		final String creadoen= intent.getStringExtra(EXTRACREADOEN);
		final String estado = intent.getStringExtra(EXTRAESTADO);
		final String publicado= intent.getStringExtra(EXTRAPUBLICADO);
		final String imagen = intent.getStringExtra(EXTRAIMAGEN);
		final String precio = intent.getStringExtra(EXTRAPRECIO);
		final String videopresentacion = intent.getStringExtra(EXTRAVIDEOPRESENTACION);



		Picasso.with(this).load(imagen)
				.priority(HIGH)
				.fit()
				.centerInside()
				.into(imagen_curso);
				//resize(100,100).

		txtNombreCurso.setText(nombre);
		txtDescripcion.setText(descripcion);
		txtIntension.setText(intensidad);
		txtImagen.setText(imagen);
		txtCompetencias.setText(competencias);
		segunda_desc.setText(presentacion);

		show.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				show.setVisibility(View.INVISIBLE);
				hide.setVisibility(View.VISIBLE);
				txtIntension.setMaxLines(Integer.MAX_VALUE);
				//ocultar los otros, se debe implementar en los demas

				hide2.setVisibility(View.INVISIBLE);
				show2.setVisibility(View.VISIBLE);
				segunda_desc.setMaxLines(5);
				hide3.setVisibility(View.INVISIBLE);
				show3.setVisibility(View.VISIBLE);
				tercera_desc.setMaxLines(5);

			}
		});
		hide.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hide.setVisibility(View.INVISIBLE);
				show.setVisibility(View.VISIBLE);
				txtIntension.setMaxLines(5);

			}
		});

		show3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				show3.setVisibility(View.INVISIBLE);
				hide3.setVisibility(View.VISIBLE);
				tercera_desc.setMaxLines(Integer.MAX_VALUE);

				hide.setVisibility(View.INVISIBLE);
				show.setVisibility(View.VISIBLE);
				txtIntension.setMaxLines(5);
				hide2.setVisibility(View.INVISIBLE);
				show2.setVisibility(View.VISIBLE);
				segunda_desc.setMaxLines(5);


			}
		});
		show2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				show2.setVisibility(View.INVISIBLE);
				hide2.setVisibility(View.VISIBLE);
				segunda_desc.setMaxLines(Integer.MAX_VALUE);

				hide.setVisibility(View.INVISIBLE);
				show.setVisibility(View.VISIBLE);
				txtIntension.setMaxLines(5);
				hide3.setVisibility(View.INVISIBLE);
				show3.setVisibility(View.VISIBLE);
				tercera_desc.setMaxLines(5);

			}
		});
		hide2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				hide2.setVisibility(View.INVISIBLE);
				show2.setVisibility(View.VISIBLE);
				segunda_desc.setMaxLines(5);
			}
		});

		hide3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hide3.setVisibility(View.INVISIBLE);
				show3.setVisibility(View.VISIBLE);
				tercera_desc.setMaxLines(5);
			}
		});
		databaseHandler = new DatabaseHandler(this);

		mRecyclerView = findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(CursosDetalle.this));

		munidadesItems = new ArrayList<>();

		mRequestQueue = Volley.newRequestQueue(CursosDetalle.this);

		obtenerUnidades();
	}

	/*private void obtenerUnidades() {
		final ProgressDialog progressDialog = new ProgressDialog(CursosDetalle.this);
		progressDialog.setMessage("Cargando...");
		progressDialog.show();
		progressDialog.setCancelable(false);
		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						progressDialog.dismiss();
						try {
							JSONObject jsonObject = new JSONObject(response);
							String success = jsonObject.getString("success");
							JSONArray jsonArray = jsonObject.getJSONArray("read");

							if (success.equals("1")) {

								for (int i = 0; i < jsonArray.length(); i++) {

									JSONObject object = jsonArray.getJSONObject(i);


									String name= object.getString("name");
									String hability = object.getString("hability");
									String presentation= object.getString("presentation");
									String competences_e1= object.getString("competences_e1");
									String competences_e2= object.getString("competences_e2");
									String competences_e3= object.getString("competences_e3");
									String competences_t1= object.getString("competences_t1");
									String competences_t2= object.getString("competences_t2");
									String competences_t3= object.getString("competences_t3");
									String result1= object.getString("result1");
									String result2= object.getString("result2");
									String result3= object.getString("result3");
									String unit= object.getString("unit");
									String result4= object.getString("result4");
									String comper11= object.getString("comper11");
									String comper12= object.getString("comper12");
									String comper13= object.getString("comper13");
									String comper21= object.getString("comper21");
									String comper22= object.getString("comper22");
									String comper23= object.getString("comper23");
									String comper31= object.getString("comper31");
									String comper32= object.getString("comper32");
									String comper33= object.getString("comper33");
									String comper41= object.getString("comper41");
									String comper42= object.getString("comper42");
									String comper43= object.getString("comper43");
									String question= object.getString("question");
									String ready= object.getString("ready");
									String nameV= object.getString("nameV");
									String video_apoyo= object.getString("video_apoyo");
									String projecting= object.getString("projecting");
									String topic= object.getString("topic");
									String challenge= object.getString("challenge");
									String doing= object.getString("doing");
									String bibliography= object.getString("bibliography");
									String content= object.getString("content");
									String id_course= object.getString("id_course");
									String type= object.getString("type");


									munidadesItems.add(new UnidadesItem(name, hability, presentation, competences_e1, competences_e2, competences_e3, competences_t1, competences_t2, competences_t3, result1, result2, result3, unit, result4, comper11, comper12, comper13, comper21, comper22, comper23, comper31, comper32, comper33, comper41, comper42, comper43, question, ready, nameV, video_apoyo, projecting, topic, challenge, doing, bibliography, content, id_course, type));

									/*String strName = object.getString("name").trim();
									String strApellido = object.getString("last_name").trim();
									String strEmail = object.getString("email").trim();
									String strUsuario = object.getString("user_name").trim();
									String strPicture= object.getString("picture").trim();
									String strTelefono = object.getString("phone").trim();
									edtNombre.setText(strName);
									edtApellido.setText(strApellido);
									edtTelefono.setText(strTelefono);
									edtCorreo.setText(strEmail);
									txtUsuario.setText(strUsuario);
									if (strPicture.equals("")) {
										imvprofile.setImageResource(R.drawable.imagenperfil);
									} else {
										Picasso.with(getActivity()).load(strPicture).centerCrop()
												.placeholder(R.drawable.internetconnection).fit().into(imvprofile, new Callback() {
											@Override public    void onSuccess() {}
											@Override public void onError() {}
										});
										//Picasso.with(getActivity()).load(strPicture).into(imvprofile);
									}


								}
								mUnidadesAdaptor = new UnidadesAdaptor(CursosDetalle.this, munidadesItems);
								mRecyclerView.setAdapter(mUnidadesAdaptor);
								mUnidadesAdaptor.setOnClickItemListener(CursosDetalle.this);


							}

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toast.makeText(CursosDetalle.this, "Error de conexión ", Toast.LENGTH_SHORT).show();
						}

					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						progressDialog.dismiss();
						Toast.makeText(CursosDetalle.this, "Error de conexión  ", Toast.LENGTH_SHORT).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<>();
				params.put("id", "1");
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(CursosDetalle.this);
		requestQueue.add(stringRequest);

	}*/

	public void obtenerUnidades(){
		String url = "http://dybcatering.com/back_live_app/cursos/cursos_unidades.php";

		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {
							JSONArray jsonArray = response.getJSONArray("Registros");
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject hit = jsonArray.getJSONObject(i);
								String name= hit.getString("name");
								String topic= hit.getString("topic");
								/*String hability = hit.getString("hability");
								String presentation= hit.getString("presentation");
								String competences_e1= hit.getString("competences_e1");
								String competences_e2= hit.getString("competences_e2");
								String competences_e3= hit.getString("competences_e3");
								String competences_t1= hit.getString("competences_t1");
								String competences_t2= hit.getString("competences_t2");
								String competences_t3= hit.getString("competences_t3");
								String result1= hit.getString("result1");
								String result2= hit.getString("result2");
								String result3= hit.getString("result3");
								String unit= hit.getString("unit");
								String result4= hit.getString("result4");
								String comper11= hit.getString("comper11");
								String comper12= hit.getString("comper12");
								String comper13= hit.getString("comper13");
								String comper21= hit.getString("comper21");
								String comper22= hit.getString("comper22");
								String comper23= hit.getString("comper23");
								String comper31= hit.getString("comper31");
								String comper32= hit.getString("comper32");
								String comper33= hit.getString("comper33");
								String comper41= hit.getString("comper41");
								String comper42= hit.getString("comper42");
								String comper43= hit.getString("comper43");
								String question= hit.getString("question");
								String ready= hit.getString("ready");
								String nameV= hit.getString("nameV");
								String video_apoyo= hit.getString("video_apoyo");
								String projecting= hit.getString("projecting");
								String challenge= hit.getString("challenge");
								String doing= hit.getString("doing");
								String bibliography= hit.getString("bibliography");
								String content= hit.getString("content");
								String id_course= hit.getString("id_course");
								String type= hit.getString("type");*/


								munidadesItems.add(new UnidadesItem(name, topic));// hability, presentation, competences_e1, competences_e2, competences_e3, competences_t1, competences_t2, competences_t3, result1, result2, result3, unit, result4, comper11, comper12, comper13, comper21, comper22, comper23, comper31, comper32, comper33, comper41, comper42, comper43, question, ready, nameV, video_apoyo, projecting, topic, challenge, doing, bibliography, content, id_course, type));

							}

							mUnidadesAdaptor = new UnidadesAdaptor(CursosDetalle.this, munidadesItems);
							mRecyclerView.setAdapter(mUnidadesAdaptor);
//							mUnidadesAdaptor.setOnClickItemListener((UnidadesAdaptor.OnItemClickListener) CursosDetalle.this);

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
			}
		});

		mRequestQueue.add(request);
	}

	public void mostrar(){
		ChocoBar.builder().setBackgroundColor(Color.parseColor("#007883"))
				.setTextSize(18)
				.setTextColor(Color.parseColor("#FFFFFF"))
				.setActionClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {


						AlertDialog alertDialog = new AlertDialog.Builder(CursosDetalle.this, R.style.Botones).create();
						alertDialog.setTitle("Curso en promoción");
						alertDialog.setMessage("Hola tenemos un curso en promoción");
						alertDialog.setCancelable(false);
						alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int which) {
										dialog.dismiss();

										//  guardarBaseDatos();
										//Intent intent = new Intent(PrimerCurso.this, CompraActivity.class);
										// startActivity(intent);
										mostrar();
									}
								});
						alertDialog.show();
					}
				})
				//.setText("This is a custom Chocobar")
				.setMaxLines(4)
				.centerText()
				.setActionText("OBTÉN UN DESCUENTO DEL 50% EN CURSOS PREMIUM")
				.setActionTextColor(Color.parseColor("#FFFFFF"))
				.setActionTextSize(20)
				.setActivity(CursosDetalle.this)
				.setDuration(ChocoBar.LENGTH_INDEFINITE)
				.build()
				.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.boton_arriba, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.mybutton) {
			//  sessionManager = new SessionManager(this);
			// if (sessionManager.isLoggin()){
			saveGroceryToDB();
			// }else{
			//   alert();
			// }
		}
		return super.onOptionsItemSelected(item);
	}
	public void saveGroceryToDB() {
		Grocery grocery = new Grocery();
		String nombrecurso = txtNombreCurso.getText().toString();
		String descripcioncurso = txtDescripcion.getText().toString();
		String imagencurso = txtImagen.getText().toString();

		grocery.setName(nombrecurso);
		grocery.setQuantity(descripcioncurso);
		grocery.setImagen(imagencurso);

		//Save to DB
		int cuenta = databaseHandler.contar(nombrecurso);
		if (cuenta>0){
			new AlertDialog.Builder(CursosDetalle.this, R.style.Botones)
					.setTitle("Este curso ya fue agregado al carrito de compras")
					.setMessage("¿Desea ir al carrito de compras?")
					.setIcon(R.drawable.carrito)
					.setPositiveButton("Si",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int id) {
									Intent intent = new Intent(CursosDetalle.this, CarritoActivity.class);
									startActivity(intent);

									dialog.cancel();
								}
							})
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {;
							dialog.cancel();
						}
					}).show();


		}else{

			databaseHandler.addGrocery(grocery);
			new AlertDialog.Builder(CursosDetalle.this, R.style.Botones)
					.setTitle("Curso Agregado al carrito")
					.setMessage("¿Ir al carrito de compras?")
					.setIcon(R.drawable.carrito)
					.setPositiveButton("Si",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int id) {
									Intent intent = new Intent(CursosDetalle.this, CarritoActivity.class);
									startActivity(intent);
									dialog.cancel();
								}
							})
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {;
							dialog.cancel();
						}
					}).show();


		}

	}

}
