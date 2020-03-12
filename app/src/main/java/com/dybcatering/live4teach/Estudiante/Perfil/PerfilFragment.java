package com.dybcatering.live4teach.Estudiante.Perfil;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dybcatering.live4teach.BuildConfig;
import com.dybcatering.live4teach.ConferenciaOnline.ConferenciaOnline;
import com.dybcatering.live4teach.Estudiante.Inicio.InicioActivity;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Perfil.PerfilFragmentFotoPerfilTutor;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragment extends Fragment {
    View myView;
    private TextView tvname, tvemail,tvphone, tvidenti, txtVersion;

    private Button namebutton;
    private CircleImageView primage;
    private Button updateDetails;
    private LinearLayout changepassword, cerrar_sesion;

    //to get user session data
    // private UserSession session;
    private HashMap<String,String> user;
    private String name,email,photo,mobile;
    private SliderLayout sliderShow;


    NotificationBadge mBadge;
    private int count =0;
    SessionManager sessionManager;

    String id;

    private static String URL_READ = "https://dybcatering.com/back_live_app/read_detail.php";

    private static final String TAG = PerfilFragment.class.getSimpleName(); //getting the info

    private ImageLoader imageLoader;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_perfil, container, false);
        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);
        changepassword = myView.findViewById(R.id.changepassword);
        primage=myView.findViewById(R.id.profilepicestudiante);
        tvname=myView.findViewById(R.id.nameview);
        tvemail=myView.findViewById(R.id.emailview);
        tvphone=myView.findViewById(R.id.mobileview);
        //tvidenti= myView.findViewById(R.id.cedula);
        namebutton=myView.findViewById(R.id.btn_actualizar_perfil);
        txtVersion = myView.findViewById(R.id.txtPerfilVersion);
        cerrar_sesion = myView.findViewById(R.id.cerrar_sesion_estudiante);
        txtVersion.setText("Versión: "+ BuildConfig.VERSION_NAME);
        final String nombre = tvname.getText().toString();
        final String correo = tvemail.getText().toString();
        final String telefono = tvphone.getText().toString();
//        final String identificacion = tvidenti.getText().toString();

        inflateImageSlider();

        getUserDetail();
        namebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConferenciaOnline.class);
                startActivity(intent);
                //swapFragment();
               // Intent intent = new Intent(getActivity(), HomeActivity.class);
              //  startActivity(intent);
               // intent.putExtra("nombre", nombre);
               // intent.putExtra("correo", correo);
               // intent.putExtra("telefono", telefono);
               // intent.putExtra("identificacion", identificacion);
               // startActivity(intent);
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarContrasena();
            }
        });

        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
                        .setBackgroundColor(R.color.white)
                        //.setimageResource(R.drawable.internetconnection)
                        .setTextTitle("Información")
                        .setTextSubTitle("¿Deseas Cerrar la Sesión?")
                        .setCancelable(false)
                        //.setBody("Iniciar Sesión ")
                        .setPositiveButtonText("Aceptar")
                        .setPositiveColor(R.color.colorbonton)
                        .setNegativeButtonText("Cancelar")
                        .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                sessionManager = new SessionManager(getContext());
                                sessionManager.logoutEstudiante();
                                getActivity().finish();
                            }
                        })
                        .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setCancelable(false)
                        .build();
                alert.show();
            }
        });
        primage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment actualizarDatosFragment = new PerfilFragmentFotoPerfilTutor();
                //tvname.setText("Daniel");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, actualizarDatosFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });

        return myView;
    }

    private void getUserDetail() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
		progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    final String strPicture= object.getString("picture").trim();
                                    String strTelefono = object.getString("phone").trim();
                                    tvname.setText(strName);
                                    tvemail.setText(strEmail);
                                    tvphone.setText(strTelefono);
                                    if (strPicture.equals("")) {
                                        primage.setImageResource(R.drawable.imagenperfil);
                                    } else {

                                        Picasso.with(getContext()).load(strPicture).into(primage);

                                    }

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error de conexión ", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error de conexión  ", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

        primage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
                        .setBackgroundColor(R.color.white)
                        .setimageResource( R.drawable.imagenperfil)
                        .setTextTitle("Perfil")
                        .setPositiveButtonText("Aceptar")
                        .setPositiveColor(R.color.colorbonton)
                        .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {

                                dialog.dismiss();


                            }
                        })
                        .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setCancelable(false)
                        .build();
                alert.show();
            }
        });

    }

    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = myView.findViewById(R.id.slider);

       // HashMap<String,String> url_maps = new HashMap<String, String>();
       // url_maps.put("Descripción 1", "http://digitalandroidservices.com/personal/cover1.jpg");
       // url_maps.put("Descripción 2", "http://digitalandroidservices.com/personal/cover2.png");
       // url_maps.put("Descripción 3", "http://digitalandroidservices.com/personal/cover3.png");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Descripción 1",R.drawable.cover1);
        file_maps.put("Descripción 2",R.drawable.cover2);
        file_maps.put("Descripción 3",R.drawable.cover3);

        for (String s:file_maps.keySet()){
            //  DefaultSliderView sliderView=new DefaultSliderView(PrimerCurso.this);
            // sliderView.image(s);
            // sliderShow.addSlider(sliderView);
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(s)
                    .image(file_maps.get(s))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",s);

            sliderShow.addSlider(textSliderView);

        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
    }


    private void swapFragment(){
        Fragment someFragment = new ActualizarDatosFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void cambiarContrasena(){
        Fragment actualizarDatosFragment = new CambiarContrasenaFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, actualizarDatosFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

}
