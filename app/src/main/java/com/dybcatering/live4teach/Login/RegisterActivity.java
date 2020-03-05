package com.dybcatering.live4teach.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.dybcatering.live4teach.Estudiante.Inicio.InicioActivity;
import com.dybcatering.live4teach.Login.Request.RegisterRequest;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.rilixtech.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText edtnombre, edtapellido, edtemail, edtusuario, edtpassword, edtc_password, edttelefono;
    private String verificar, nombre, apellido, email, usuario, password, c_password, telefono, fnacimiento, edad;
    private Button btn_regist, btn_date;
	private static String URL_CARGAR = "https://dybcatering.com/back_live_app/listarcategorias.php";

	private TextView txtDate, ejemplo;

    public Spinner spinnerRegistro, categoria;

    public CountryCodePicker ccp;


    public String ageS;

    RequestQueue requestQueue;
	ProgressDialog progressDialog;
	private Spinner SpinnerSubcategoria;
	ArrayList<String> Subcategoria;


	FirebaseAuth auth;
	DatabaseReference reference, tokens;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		spinnerRegistro = findViewById(R.id.spinnerregistro);
		SpinnerSubcategoria= findViewById(R.id.spinnersubcategoria);
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerRegistro.setAdapter(arrayAdapter);
		spinnerRegistro.setOnItemSelectedListener(this);
		edtnombre = findViewById(R.id.txtNombre);
		edtapellido = findViewById(R.id.txtApellido);
		edtemail = findViewById(R.id.txtMail);
		edtusuario = findViewById(R.id.txtUsuario);
		edtpassword = findViewById(R.id.txtPassword);
		edtc_password = findViewById(R.id.txtPasswordConfirm);
		edttelefono = findViewById(R.id.txtTelefono);
		btn_regist = findViewById(R.id.btn_registrarse);
		btn_date = findViewById(R.id.btn_date);
		txtDate = findViewById(R.id.TxtFecha);
		Subcategoria=new ArrayList<>();

		edtnombre.setEnabled(false);
		edtapellido.setEnabled(false);
		edtemail.setEnabled(false);
		edtusuario.setEnabled(false);
		edtpassword.setEnabled(false);
		edtc_password.setEnabled(false);
		edttelefono.setEnabled(false);
		btn_regist.setEnabled(false);

		new CheckInternetConnection(this).checkConnection();

		edtpassword.addTextChangedListener(watcherContrasena);
		edtc_password.addTextChangedListener(watcherConfirmarContrasena);


		requestQueue = Volley.newRequestQueue(RegisterActivity.this);

		// loading = findViewById(R.id.loading);
		// name = findViewById(R.id.name);
		// email = findViewById(R.id.email);
		// password = findViewById(R.id.password);
		/// c_password = findViewById(R.id.c_password);
		// btn_regist = findViewById(R.id.btn_regist);
		ccp = findViewById(R.id.ccp);

		ccp.registerPhoneNumberTextView(edttelefono);

		loadSpinner(URL_CARGAR);

		btn_date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				final int year = calendar.get(Calendar.YEAR);
				final int month = calendar.get(Calendar.MONTH);
				final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, R.style.DialogTheme,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int year, int month, int day) {

								txtDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

								Toast.makeText(RegisterActivity.this, getAge(year, month, day), Toast.LENGTH_SHORT).show();
							}
						}, year, month, dayOfMonth);
				datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
				datePickerDialog.show();




			}
		});

		auth = FirebaseAuth.getInstance();

		btn_regist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ValidarContrasena() && ValidarConfirmarContrasena()){

					nombre = edtnombre.getText().toString();
					apellido = edtapellido.getText().toString();
					email = edtemail.getText().toString();
					telefono = edttelefono.getText().toString();
					usuario = edtusuario.getText().toString();
					password = edtpassword.getText().toString();
					fnacimiento = txtDate.getText().toString();
					 edad = getAge(2020, 1, 24);
					//cambio de firebaseUser.getUid a random generico de 20 caracteres

					final KProgressHUD progressDialog=  KProgressHUD.create(RegisterActivity.this)
							.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
							.setLabel("Por favor espera un momento")
							.setCancellable(false)
							.setAnimationSpeed(2)
							.setDimAmount(0.5f)
							.show();


					auth.createUserWithEmailAndPassword(email, password)
							.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
								@Override
								public void onComplete(@NonNull Task<AuthResult> task) {
									if (task.isSuccessful()){
										FirebaseUser firebaseUser = auth.getCurrentUser();
										assert firebaseUser != null;
										String userid = firebaseUser.getUid();
										//cambio de firebaseUser.getUid a random generico de 20 caracteres

										reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

										HashMap<String, String> hashMap = new HashMap<>();
										hashMap.put("id",userid);
										hashMap.put("username", usuario);
										hashMap.put("imageURL", "default");
										hashMap.put("status", "Desconectado");
										hashMap.put("search", usuario.toLowerCase());
										hashMap.put("type_user", "3");

										reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
											@Override
											public void onComplete(@NonNull Task<Void> task) {
												if (task.isSuccessful()){
													Toasty.success(RegisterActivity.this, "Registrado exitosamente", Toast.LENGTH_SHORT).show();
												}
											}
										});

										String myRefreshedToken = FirebaseInstanceId.getInstance().getToken();

										tokens = FirebaseDatabase.getInstance().getReference("Tokens").child(userid);

										HashMap<String, String> hashMap1 = new HashMap<>();
										hashMap1.put(userid, myRefreshedToken);

										tokens.setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
											@Override
											public void onComplete(@NonNull Task<Void> task) {
												Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
												//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
												startActivity(intent);
												//finish();
												//	Toasty.success(RegisterActivity.this,"Registrado Satisfactoriamente",Toast.LENGTH_SHORT,true).show();

												sendRegistrationEmail(nombre,email);
											}
										});

										RegisterRequest registerRequest = new RegisterRequest(userid, nombre, apellido, email,usuario, password,edad, fnacimiento, telefono, new Response.Listener<String>() {
											@Override
											public void onResponse(String response) {
												progressDialog.dismiss();

												Log.e("Response from server", response);

												try {
													if (new JSONObject(response).getBoolean("success")) {

														Toasty.success(RegisterActivity.this,"Registrado Satisfactoriamente",Toast.LENGTH_SHORT,true).show();

														//sendRegistrationEmail(nombre,email);
														//register(usuario, email, password, "3");
														//finish();

													} else
														Toasty.error(RegisterActivity.this,"El usuario ya existe, por favor intenta nuevamente",Toast.LENGTH_SHORT,true).show();
												} catch (JSONException e) {
													e.printStackTrace();
													Toasty.error(RegisterActivity.this,"Falló el registro, por favor intenta nuevamente",Toast.LENGTH_LONG,true).show();
												}
											}
										});
										requestQueue.add(registerRequest);


									}
								}
							});

				}

			}
		});

	}

	@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =  parent.getItemAtPosition(position).toString();
       if (text.equals("Tutor")){

           final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(this)
                   .setBackgroundColor(R.color.white)
                   //.setimageResource(R.drawable.internetconnection)
                   .setTextTitle("Alerta")
                   .setTextSubTitle("No es posible registrarse como tutor, por favor registrese desde la web")
                   //.setBody("Iniciar Sesión ")
                   .setPositiveButtonText("Aceptar")
                   .setPositiveColor(R.color.colorbonton)
                   .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                       @Override
                       public void OnClick(View view, Dialog dialog) {
                           String url = "http://www.dybcatering.com";
                           Intent i = new Intent(Intent.ACTION_VIEW);
                           i.setData(Uri.parse(url));
                           startActivity(i);
                           edtnombre.setEnabled(false);
                           edtapellido.setEnabled(false);
                           edtemail.setEnabled(false);
                           edtusuario.setEnabled(false);
                           edtpassword.setEnabled(false);
                           edtc_password.setEnabled(false);
                           edttelefono.setEnabled(false);
                           btn_regist.setEnabled(false);
                           finish();
                       }
                   })
                   .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                   .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                   .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                   .setCancelable(false)
                   .build();
           alert.show();



        } else if (text.equals("Estudiante")) {
           edtnombre.setEnabled(true);
           edtapellido.setEnabled(true);
           edtemail.setEnabled(true);
           edtusuario.setEnabled(true);
           edtpassword.setEnabled(true);
           edtc_password.setEnabled(true);
           edttelefono.setEnabled(true);
           btn_regist.setEnabled(true);
        } else if (text.equals("Seleccionar...")){
           edtnombre.setEnabled(false);
           edtapellido.setEnabled(false);
           edtemail.setEnabled(false);
           edtusuario.setEnabled(false);
           edtpassword.setEnabled(false);
           edtc_password.setEnabled(false);
       //   telefono.setEnabled(false);
           btn_regist.setEnabled(false);

       }

    }

    private boolean ValidarContrasena(){

		verificar = edtpassword.getText().toString();
		return !(verificar.length() <6);
	}

	private boolean ValidarConfirmarContrasena(){
    	verificar = edtc_password.getText().toString();
    	return verificar.equals(edtc_password.getText().toString());
	}

	TextWatcher watcherConfirmarContrasena = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			//
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			//
		}

		@Override
		public void afterTextChanged(Editable s) {
			verificar = s.toString();

			if (!verificar.equals(edtpassword.getText().toString())) {
				edtc_password.setError("Las contraseñas no son iguales");
			}
		}
	};

	TextWatcher watcherContrasena = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			//
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			//
		}

		@Override
		public void afterTextChanged(Editable s) {
			verificar= s.toString();

			if (verificar.length()<6 ){
				edtpassword.setError("La contraseña debe ser mayor a 6 carácteres");
			}
		}
	};
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    private void sendRegistrationEmail(final String name, final String emails) {


        BackgroundMail.newBuilder(RegisterActivity.this)
                .withSendingMessage("Envío de saludos de bienvenida a su correo electrónico !")
                .withSendingMessageSuccess("Compruebe amablemente su correo electrónico ahora!")
                .withSendingMessageError("¡Error al enviar la contraseña! Inténtalo de nuevo !")
                .withUsername("prueba.correo7521@gmail.com")
                .withPassword("N1m2g3e4r57791")
                .withMailto(emails)
                .withType(BackgroundMail.TYPE_HTML)
                .withSubject("Saludos desde la aplicación")
                .withBody("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"x-apple-disable-message-reformatting\" /><meta name=\"apple-mobile-web-app-capable\" content=\"yes\" /><meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\" /><meta name=\"format-detection\" content=\"telephone=no\" /><title></title><style type=\"text/css\">\n" +
                        "/* Resets */\n" +
                        ".ReadMsgBody { width: 100%; background-color: #ebebeb;}\n" +
                        ".ExternalClass {width: 100%; background-color: #ebebeb;}\n" +
                        ".ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height:100%;}\n" +
                        "a[x-apple-data-detectors]{\n" +
                        "color:inherit !important;\n" +
                        "text-decoration:none !important;\n" +
                        "font-size:inherit !important;\n" +
                        "font-family:inherit !important;\n" +
                        "font-weight:inherit !important;\n" +
                        "line-height:inherit !important;\n" +
                        "}        \n" +
                        "body {-webkit-text-size-adjust:none; -ms-text-size-adjust:none;}\n" +
                        "body {margin:0; padding:0;}\n" +
                        ".yshortcuts a {border-bottom: none !important;}\n" +
                        ".rnb-del-min-width{ min-width: 0 !important; }\n" +
                        "\n" +
                        "/* Add new outlook css start */\n" +
                        ".templateContainer{\n" +
                        "max-width:590px !important;\n" +
                        "width:auto !important;\n" +
                        "}\n" +
                        "/* Add new outlook css end */\n" +
                        "\n" +
                        "/* Image width by default for 3 columns */\n" +
                        "img[class=\"rnb-col-3-img\"] {\n" +
                        "max-width:170px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Image width by default for 2 columns */\n" +
                        "img[class=\"rnb-col-2-img\"] {\n" +
                        "max-width:264px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Image width by default for 2 columns aside small size */\n" +
                        "img[class=\"rnb-col-2-img-side-xs\"] {\n" +
                        "max-width:180px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Image width by default for 2 columns aside big size */\n" +
                        "img[class=\"rnb-col-2-img-side-xl\"] {\n" +
                        "max-width:350px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Image width by default for 1 column */\n" +
                        "img[class=\"rnb-col-1-img\"] {\n" +
                        "max-width:550px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Image width by default for header */\n" +
                        "img[class=\"rnb-header-img\"] {\n" +
                        "max-width:590px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Ckeditor line-height spacing */\n" +
                        ".rnb-force-col p, ul, ol{margin:0px!important;}\n" +
                        ".rnb-del-min-width p, ul, ol{margin:0px!important;}\n" +
                        "\n" +
                        "/* tmpl-2 preview */\n" +
                        ".rnb-tmpl-width{ width:100%!important;}\n" +
                        "\n" +
                        "/* tmpl-11 preview */\n" +
                        ".rnb-social-width{padding-right:15px!important;}\n" +
                        "\n" +
                        "/* tmpl-11 preview */\n" +
                        ".rnb-social-align{float:right!important;}\n" +
                        "\n" +
                        "/* Ul Li outlook extra spacing fix */\n" +
                        "li{mso-margin-top-alt: 0; mso-margin-bottom-alt: 0;}        \n" +
                        "\n" +
                        "/* Outlook fix */\n" +
                        "table {mso-table-lspace:0pt; mso-table-rspace:0pt;}\n" +
                        "\n" +
                        "/* Outlook fix */\n" +
                        "table, tr, td {border-collapse: collapse;}\n" +
                        "\n" +
                        "/* Outlook fix */\n" +
                        "p,a,li,blockquote {mso-line-height-rule:exactly;} \n" +
                        "\n" +
                        "/* Outlook fix */\n" +
                        ".msib-right-img { mso-padding-alt: 0 !important;}\n" +
                        "\n" +
                        "/* Fix text line height on preview */ \n" +
                        ".content-spacing div {display: list-item; list-style-type: none;}\n" +
                        "\n" +
                        "@media only screen and (min-width:590px){\n" +
                        "/* mac fix width */\n" +
                        ".templateContainer{width:590px !important;}\n" +
                        "}\n" +
                        "\n" +
                        "@media screen and (max-width: 360px){\n" +
                        "/* yahoo app fix width \"tmpl-2 tmpl-10 tmpl-13\" in android devices */\n" +
                        ".rnb-yahoo-width{ width:360px !important;}\n" +
                        "}\n" +
                        "\n" +
                        "@media screen and (max-width: 380px){\n" +
                        "/* fix width and font size \"tmpl-4 tmpl-6\" in mobile preview */\n" +
                        ".element-img-text{ font-size:24px !important;}\n" +
                        ".element-img-text2{ width:230px !important;}\n" +
                        ".content-img-text-tmpl-6{ font-size:24px !important;}\n" +
                        ".content-img-text2-tmpl-6{ width:220px !important;}\n" +
                        "}\n" +
                        "\n" +
                        "@media screen and (max-width: 480px) {\n" +
                        "td[class=\"rnb-container-padding\"] {\n" +
                        "padding-left: 10px !important;\n" +
                        "padding-right: 10px !important;\n" +
                        "}\n" +
                        "\n" +
                        "/* force container nav to (horizontal) blocks */\n" +
                        "td.rnb-force-nav {\n" +
                        "display: inherit;\n" +
                        "}\n" +
                        "\n" +
                        "/* fix text alignment \"tmpl-11\" in mobile preview */\n" +
                        ".rnb-social-text-left {\n" +
                        "width: 100%;\n" +
                        "text-align: center;\n" +
                        "margin-bottom: 15px;\n" +
                        "}\n" +
                        ".rnb-social-text-right {\n" +
                        "width: 100%;\n" +
                        "text-align: center;\n" +
                        "}\n" +
                        "}\n" +
                        "\n" +
                        "@media only screen and (max-width: 600px) {\n" +
                        "\n" +
                        "/* center the address &amp; social icons */\n" +
                        ".rnb-text-center {text-align:center !important;}\n" +
                        "\n" +
                        "/* force container columns to (horizontal) blocks */\n" +
                        "td.rnb-force-col {\n" +
                        "display: block;\n" +
                        "padding-right: 0 !important;\n" +
                        "padding-left: 0 !important;\n" +
                        "width:100%;\n" +
                        "}\n" +
                        "\n" +
                        "table.rnb-container {\n" +
                        " width: 100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "table.rnb-btn-col-content {\n" +
                        "width: 100% !important;\n" +
                        "}\n" +
                        "table.rnb-col-3 {\n" +
                        "/* unset table align=\"left/right\" */\n" +
                        "float: none !important;\n" +
                        "width: 100% !important;\n" +
                        "\n" +
                        "/* change left/right padding and margins to top/bottom ones */\n" +
                        "margin-bottom: 10px;\n" +
                        "padding-bottom: 10px;\n" +
                        "/*border-bottom: 1px solid #eee;*/\n" +
                        "}\n" +
                        "\n" +
                        "table.rnb-last-col-3 {\n" +
                        "/* unset table align=\"left/right\" */\n" +
                        "float: none !important;\n" +
                        "width: 100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "table.rnb-col-2 {\n" +
                        "/* unset table align=\"left/right\" */\n" +
                        "float: none !important;\n" +
                        "width: 100% !important;\n" +
                        "\n" +
                        "/* change left/right padding and margins to top/bottom ones */\n" +
                        "margin-bottom: 10px;\n" +
                        "padding-bottom: 10px;\n" +
                        "/*border-bottom: 1px solid #eee;*/\n" +
                        "}\n" +
                        "\n" +
                        "table.rnb-col-2-noborder-onright {\n" +
                        "/* unset table align=\"left/right\" */\n" +
                        "float: none !important;\n" +
                        "width: 100% !important;\n" +
                        "\n" +
                        "/* change left/right padding and margins to top/bottom ones */\n" +
                        "margin-bottom: 10px;\n" +
                        "padding-bottom: 10px;\n" +
                        "}\n" +
                        "\n" +
                        "table.rnb-col-2-noborder-onleft {\n" +
                        "/* unset table align=\"left/right\" */\n" +
                        "float: none !important;\n" +
                        "width: 100% !important;\n" +
                        "\n" +
                        "/* change left/right padding and margins to top/bottom ones */\n" +
                        "margin-top: 10px;\n" +
                        "padding-top: 10px;\n" +
                        "}\n" +
                        "\n" +
                        "table.rnb-last-col-2 {\n" +
                        "/* unset table align=\"left/right\" */\n" +
                        "float: none !important;\n" +
                        "width: 100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "table.rnb-col-1 {\n" +
                        "/* unset table align=\"left/right\" */\n" +
                        "float: none !important;\n" +
                        "width: 100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "img.rnb-col-3-img {\n" +
                        "/**max-width:none !important;**/\n" +
                        "width:100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "img.rnb-col-2-img {\n" +
                        "/**max-width:none !important;**/\n" +
                        "width:100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "img.rnb-col-2-img-side-xs {\n" +
                        "/**max-width:none !important;**/\n" +
                        "width:100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "img.rnb-col-2-img-side-xl {\n" +
                        "/**max-width:none !important;**/\n" +
                        "width:100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "img.rnb-col-1-img {\n" +
                        "/**max-width:none !important;**/\n" +
                        "width:100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "img.rnb-header-img {\n" +
                        "/**max-width:none !important;**/\n" +
                        "width:100% !important;\n" +
                        "margin:0 auto;\n" +
                        "}\n" +
                        "\n" +
                        "img.rnb-logo-img {\n" +
                        "/**max-width:none !important;**/\n" +
                        "width:100% !important;\n" +
                        "}\n" +
                        "\n" +
                        "td.rnb-mbl-float-none {\n" +
                        "float:inherit !important;\n" +
                        "}\n" +
                        "\n" +
                        ".img-block-center{text-align:center !important;}\n" +
                        "\n" +
                        ".logo-img-center\n" +
                        "{\n" +
                        "float:inherit !important;\n" +
                        "}\n" +
                        "\n" +
                        "/* tmpl-11 preview */\n" +
                        ".rnb-social-align{margin:0 auto !important; float:inherit !important;}\n" +
                        "\n" +
                        "/* tmpl-11 preview */\n" +
                        ".rnb-social-center{display:inline-block;}\n" +
                        "\n" +
                        "/* tmpl-11 preview */\n" +
                        ".social-text-spacing{margin-bottom:0px !important; padding-bottom:0px !important;}\n" +
                        "\n" +
                        "/* tmpl-11 preview */\n" +
                        ".social-text-spacing2{padding-top:15px !important;}\n" +
                        "\n" +
                        "}</style><!--[if gte mso 11]><style type=\"text/css\">table{border-spacing: 0; }table td {border-collapse: separate;}</style><![endif]--><!--[if !mso]><!--><style type=\"text/css\">table{border-spacing: 0;} table td {border-collapse: collapse;}</style> <!--<![endif]--><!--[if gte mso 15]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]--><!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]--></head><body>\n" +
                        "\n" +
                        "<table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"main-template\" bgcolor=\"#ffffff\" style=\"background-color: rgb(255, 255, 255);\">\n" +
                        "\n" +
                        "<tbody><tr style=\"display:none !important; font-size:1px; mso-hide: all;\"><td></td><td></td></tr><tr>\n" +
                        "<td align=\"center\" valign=\"top\">\n" +
                        "<!--[if gte mso 9]>\n" +
                        "<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"590\" style=\"width:590px;\">\n" +
                        "<tr>\n" +
                        "<td align=\"center\" valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
                        "<![endif]-->\n" +
                        "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\" style=\"max-width:590px!important; width: 590px;\">\n" +
                        "<tbody><tr>\n" +
                        "\n" +
                        "<td align=\"center\" valign=\"top\">\n" +
                        "\n" +
                        "<table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_2620\" id=\"Layout_2620\">\n" +
                        "<tbody><tr>\n" +
                        "<td class=\"rnb-del-min-width\" valign=\"top\" align=\"center\" style=\"min-width:590px;\">\n" +
                        "<table width=\"100%\" cellpadding=\"0\" border=\"0\" height=\"30\" cellspacing=\"0\">\n" +
                        "<tbody><tr>\n" +
                        "<td valign=\"top\" height=\"30\">\n" +
                        "<img width=\"20\" height=\"30\" style=\"display:block; max-height:30px; max-width:20px;\" alt=\"\" src=\"http://img.mailinblue.com/new_images/rnb/rnb_space.gif\">\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "</td>\n" +
                        "</tr><tr>\n" +
                        "\n" +
                        "<td align=\"center\" valign=\"top\">\n" +
                        "\n" +
                        "<div style=\"background-color: rgb(255, 255, 255); border-radius: 0px;\">\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                        "<tr>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "<td valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
                        "<![endif]-->\n" +
                        "<table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_1\" id=\"Layout_1\">\n" +
                        "<tbody><tr>\n" +
                        "<td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\" style=\"min-width:590px;\">\n" +
                        "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-container\" bgcolor=\"#ffffff\" style=\"background-color: rgb(255, 255, 255); border-bottom: 1px solid rgb(200, 200, 200); border-radius: 0px; padding-left: 20px; padding-right: 20px; border-collapse: separate;\">\n" +
                        "<tbody><tr>\n" +
                        "<td height=\"40\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td valign=\"top\" class=\"rnb-container-padding\" align=\"left\">\n" +
                        "<table width=\"100%\" cellpadding=\"0\" border=\"0\" align=\"center\" cellspacing=\"0\">\n" +
                        "<tbody><tr>\n" +
                        "<td valign=\"top\" align=\"center\">\n" +
                        "<table cellpadding=\"0\" border=\"0\" align=\"left\" cellspacing=\"0\" class=\"logo-img-center\"> \n" +
                        "<tbody><tr>\n" +
                        "<td valign=\"middle\" align=\"center\" style=\"line-height: 1px;\">\n" +
                        "<div style=\"border-top:0px None #000;border-right:0px None #000;border-bottom:0px None #000;border-left:0px None #000;display:inline-block; \" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><div><img width=\"200\" vspace=\"0\" hspace=\"0\" border=\"0\" alt=\"Sendinblue\" style=\"float: left;max-width:200px;display:block;\" class=\"rnb-logo-img\" src=\"https://imageneslive4teach.000webhostapp.com/imagenes/liveteach_logo.png\"></div></div></td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td height=\"40\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "<!--[if mso]>\n" +
                        "</td>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "</tr>\n" +
                        "</table>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "</div></td>\n" +
                        "</tr><tr>\n" +
                        "\n" +
                        "<td align=\"center\" valign=\"top\">\n" +
                        "\n" +
                        "<div style=\"background-color: rgb(255, 255, 255); border-radius: 0px;\">\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                        "<tr>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "<td valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
                        "<![endif]-->\n" +
                        "<table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:100%;\" name=\"Layout_5\">\n" +
                        "<tbody><tr>\n" +
                        "<td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\">\n" +
                        "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-container\" bgcolor=\"#ffffff\" style=\"background-color: rgb(255, 255, 255); padding-left: 20px; padding-right: 20px; border-collapse: separate; border-radius: 0px; border-bottom: 2px solid rgb(200, 200, 200);\">\n" +
                        "\n" +
                        "<tbody><tr>\n" +
                        "<td height=\"30\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td valign=\"top\" class=\"rnb-container-padding\" align=\"left\">\n" +
                        "\n" +
                        "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-columns-container\">\n" +
                        "<tbody><tr>\n" +
                        "<td class=\"rnb-force-col\" valign=\"top\" style=\"padding-right: 0px;\">\n" +
                        "\n" +
                        "<table border=\"0\" valign=\"top\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" align=\"left\" class=\"rnb-col-1\">\n" +
                        "\n" +
                        "<tbody><tr>\n" +
                        "<td class=\"content-spacing\" style=\"font-size:16px; font-family:'Arial',Helvetica,sans-serif, sans-serif; color:#999; line-height: 21px;\"><div>\n" +
                        "<div style=\"line-height:150%;\"><span style=\"font-size:18px;\"><span style=\"color:#33c0c9;\"><strong>Hola Sr/Srta "+ name +" ,</strong></span></span></div>\n" +
                        "\n" +
                        "<div style=\"line-height:150%;\">&nbsp;</div>\n" +
                        "\n" +
                        "<div style=\"line-height:150%;\">¡Este es Diago y Benitez, CEO de Web API! ¡Bienvenido a bordo con nosotros en nombre de nuestro equipo!,<a href=\"#\" style=\"text-decoration: solid; color: rgb(51, 192, 201);\" target=\"_blank\"></a>. ¡Prometemos brindarle una experiencia de compra inolvidable en el futuro!.</div>\n" +
                        "\n" +
                        "<div style=\"line-height:150%;\">&nbsp;</div>\n" +
                        "\n" +
                        "<div style=\"line-height:150%;\"><strong>Entonces, ¿qué estás esperando, comienza a comprar y obtén lo mejor de nuestros productos justo en tu puerta!           ¡Estamos felices de ayudarte! ¡En caso de cualquier consulta, contáctenos a nuestros manejadores mencionados a continuación!</strong></div>\n" +
                        "\n" +
                        "<div style=\"line-height:150%;\">&nbsp;</div>\n" +
                        "\n" +
                        "<div style=\"line-height:150%;\"><span style=\"color:#33c0c9;\"><span style=\"font-size:18px;\"><strong>Saludos cordiales,</strong></span></span></div>\n" +
                        "\n" +
                        "<div style=\"line-height:150%;\">&nbsp;</div>\n" +
                        "\n" +
                        "<div style=\"line-height:100%;\">Live4Teach<br>\n" +
                        "&nbsp;</div>\n" +
                        "\n" +
                        "<div style=\"line-height:100%;\"><span style=\"font-size:12px;\"><em>President/CEO<br>\n" +
                        "AirBound</em></span></div>\n" +
                        "</div>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "\n" +
                        "</td></tr>\n" +
                        "</tbody></table></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td height=\"30\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table><!--[if mso]>\n" +
                        "</td>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "</tr>\n" +
                        "</table>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "</div></td>\n" +
                        "</tr><tr>\n" +
                        "\n" +
                        "<td align=\"center\" valign=\"top\">\n" +
                        "\n" +
                        "<div style=\"background-color: rgb(255, 255, 255); border-radius: 0px;\">\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                        "<tr>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "<td valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "</td>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "<!--[if mso]>\n" +
                        "</tr>\n" +
                        "</table>\n" +
                        "<![endif]-->\n" +
                        "\n" +
                        "</div></td>\n" +
                        "</tr><tr>\n" +
                        "\n" +
                        "<td align=\"center\" valign=\"top\">\n" +
                        "\n" +
                        "<div style=\"background-color: rgb(255, 255, 255);\">\n" +
                        "\n" +
                        "<table class=\"rnb-del-min-width rnb-tmpl-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_3\" id=\"Layout_3\">\n" +
                        "<tbody><tr>\n" +
                        "<td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\" bgcolor=\"#ffffff\" style=\"min-width:590px; background-color: #ffffff; text-align: center;\">\n" +
                        "<table width=\"590\" class=\"rnb-container\" cellpadding=\"0\" border=\"0\" align=\"center\" cellspacing=\"0\" bgcolor=\"#ffffff\" style=\"padding-right: 20px; padding-left: 20px; background-color: rgb(255, 255, 255);\">\n" +
                        "<tbody><tr>\n" +
                        "<td height=\"10\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td>\n" +
                        "<div style=\"font-size:14px; color:#919191; font-weight:normal; text-align:center; font-family:'Arial',Helvetica,sans-serif;\"><div>\n" +
                        "<div style=\"line-height:200%;\">Este correo fue enviado a "+emails+"</div>\n" +
                        "\n" +
                        "<div style=\"line-height:200%;\">You received this email because you are registered with Your Company</div>\n" +
                        "\n" +
                        "<div>&nbsp;</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "<div style=\"display: block; text-align: center;\">\n" +
                        "<span style=\"font-size:14px; font-weight:normal; display: inline-block; text-align:center; font-family:'Arial',Helvetica,sans-serif;\">\n" +
                        "<a style=\"text-decoration:none; color:#ccc;font-size:14px;font-weight:normal;font-family:'Arial',Helvetica,sans-serif;\" target=\"_blank\" href=\"{{ unsubscribe }}\"></a></span>\n" +
                        "</div>\n" +
                        "</td></tr>\n" +
                        "<tr>\n" +
                        "<td height=\"10\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "\n" +
                        "</tr><tr>\n" +
                        "<td height=\"10\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr></tbody></table>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "\n" +
                        "</div></td>\n" +
                        "</tr><tr>\n" +
                        "\n" +
                        "<td align=\"center\" valign=\"top\">\n" +
                        "\n" +
                        "<div style=\"background-color: rgb(51, 192, 201);\">\n" +
                        "\n" +
                        "<table class=\"rnb-del-min-width rnb-tmpl-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_4\" id=\"Layout_4\">\n" +
                        "<tbody><tr>\n" +
                        "<td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\" style=\"min-width:590px;\">\n" +
                        "<table width=\"100%\" cellpadding=\"0\" border=\"0\" align=\"center\" cellspacing=\"0\" bgcolor=\"#33c0c9\" style=\"padding-right: 20px; padding-left: 20px; background-color: rgb(51, 192, 201);\">\n" +
                        "<tbody><tr>\n" +
                        "<td height=\"20\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td style=\"font-size:14px; color:#ffffff; font-weight:normal; text-align:center; font-family:'Arial',Helvetica,sans-serif;\">\n" +
                        "<div><div>© 2020 Live4Teach</div>\n" +
                        "</div>\n" +
                        "</td></tr>\n" +
                        "<tr>\n" +
                        "<td height=\"20\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "\n" +
                        "</div></td>\n" +
                        "</tr></tbody></table>\n" +
                        "<!--[if gte mso 9]>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</table>\n" +
                        "<![endif]-->\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody></table>\n" +
                        "\n" +
                        "</body></html>")
                //"<p>Hola Mr/Miss, <b>'"+ name + "'</b></p>"+ "\n " + getString(R.string.registermail1))
                .send();



    }

	private void loadSpinner(String url){
		RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
		StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				try{
					JSONObject jsonObject=new JSONObject(response);
					JSONArray jsonArray=jsonObject.getJSONArray("Registros");
					for(int i=0;i<jsonArray.length();i++){
						JSONObject jsonObject1=jsonArray.getJSONObject(i);
						String subcat=jsonObject1.getString("name_category");
						Subcategoria.add(subcat);
					}

					SpinnerSubcategoria.setAdapter(new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, Subcategoria));
				}catch (JSONException e){e.printStackTrace();}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
			}
		});
		int socketTimeout = 30000;
		RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
		stringRequest.setRetryPolicy(policy);
		requestQueue.add(stringRequest);
	}


	private String getAge(int year, int month, int day){
		Calendar dob = Calendar.getInstance();
		Calendar today = Calendar.getInstance();

		dob.set(year, month, day);

		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
			age--;
		}

		Integer ageInt = new Integer(age);
		String ageS = ageInt.toString();

		return ageS;
	}

	private void register(final String username, final String email, String password, final String tipousuario){
		auth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()){
							FirebaseUser firebaseUser = auth.getCurrentUser();
							assert firebaseUser != null;
							String userid = firebaseUser.getUid();
							//cambio de firebaseUser.getUid a random generico de 20 caracteres

							reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

							HashMap<String, String> hashMap = new HashMap<>();
							hashMap.put("id",userid);
							hashMap.put("username", username);
							hashMap.put("imageURL", "default");
							hashMap.put("status", "Desconectado");
							hashMap.put("search", username.toLowerCase());
							hashMap.put("type_user", tipousuario);


							reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
								@Override
								public void onComplete(@NonNull Task<Void> task) {
									if (task.isSuccessful()){
										Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
										//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
										startActivity(intent);
										finish();
									}
								}
							});
						} else  {
							Toast.makeText(RegisterActivity.this, "No puedes registrarte con este email y contraseña", Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

}

