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
import com.android.volley.toolbox.StringRequest;
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
import java.util.HashMap;
import java.util.Map;

import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAACTUALIZADOEN;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRABIENVENIDA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRACATEGORIA;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRACOMPENTENCIAS;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRACREADOEN;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRADESCRIPCION;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRADESCRIPCIONO;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAESTADO;
import static com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.CursosFragment.EXTRAID;
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
		final String id = intent.getStringExtra(EXTRAID);
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


		ObtenerDatos(id);
	}

	private void ObtenerDatos(final String id) {

		String url = "http://dybcatering.com/back_live_app/cursos/cursos_unidades.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							JSONArray jsonArray = jsonObject.getJSONArray("Registros");
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject hit = jsonArray.getJSONObject(i);
								String name= hit.getString("name");
								String topic= hit.getString("topic");
								munidadesItems.add(new UnidadesItem(name, topic));
							}

							mUnidadesAdaptor = new UnidadesAdaptor(CursosDetalle.this, munidadesItems);
							mRecyclerView.setAdapter(mUnidadesAdaptor);
//							mExampleAdaptor.setOnClickItemListener(CursosFragment.this);

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
			}
		}) {
			@Override
			protected Map<String, String > getParams(){
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(CursosDetalle.this);
		requestQueue.add(stringRequest);
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
