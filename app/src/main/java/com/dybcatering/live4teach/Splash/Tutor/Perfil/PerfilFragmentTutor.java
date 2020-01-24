package com.dybcatering.live4teach.Splash.Tutor.Perfil;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dybcatering.live4teach.BuildConfig;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragmentTutor extends Fragment {
    View myView;
    private TextView tvname, tvemail,tvphone, tvidenti, txtVersion;

    private Button namebutton;
    private CircleImageView primage;
    private Button updateDetails;
    private LinearLayout cambiar_contrasena, cerrar_sesion;

    //to get user session data
    // private UserSession session;
    private HashMap<String,String> user;
    private String name,email,photo,mobile;
    private SliderLayout sliderShow;


    NotificationBadge mBadge;
    private int count =0;

    public String nombre;

    SessionManager sessionManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_perfil_tutor, container, false);
        cambiar_contrasena = myView.findViewById(R.id.cambiar_contrasena);
        primage=myView.findViewById(R.id.profilepic);
        tvname=myView.findViewById(R.id.nameview);
        tvemail=myView.findViewById(R.id.emailview);
        tvphone=myView.findViewById(R.id.mobileview);
        tvidenti= myView.findViewById(R.id.cedula);
        namebutton=myView.findViewById(R.id.btn_actualizar_perfil);
        txtVersion = myView.findViewById(R.id.txtPerfilVersion);
        cerrar_sesion= myView.findViewById(R.id.cerrar_sesion_tutor);
        txtVersion.setText("Versión: "+ BuildConfig.VERSION_NAME);
       // final String nombre = tvname.getText().toString();
      //  final String correo = tvemail.getText().toString();
        //final String telefono = tvphone.getText().toString();
        //final String identificacion = tvidenti.getText().toString();
        inflateImageSlider();
        namebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                swapFragment();
               // Intent intent = new Intent(getActivity(), ActualizarDatos.class);
               // intent.putExtra("nombre", nombre);
               // intent.putExtra("correo", correo);
               // intent.putExtra("telefono", telefono);
               // intent.putExtra("identificacion", identificacion);
               // startActivity(intent);
            }
        });

        cambiar_contrasena.setOnClickListener(new View.OnClickListener() {
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
                                sessionManager.logout();
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

        return myView;
    }



    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = myView.findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Descripción 1", "http://digitalandroidservices.com/personal/cover1.jpg");
        url_maps.put("Descripción 2", "http://digitalandroidservices.com/personal/cover2.png");
        url_maps.put("Descripción 3", "http://digitalandroidservices.com/personal/cover3.png");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Descripción 1",R.drawable.cover1);
        file_maps.put("Descripción 2",R.drawable.cover2);
        file_maps.put("Descripción 3",R.drawable.cover3);

        for (String s:url_maps.keySet()){
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
        Fragment actualizarDatosFragment = new ActualizarDatosFragmentTutor();

        Bundle bundle = new Bundle();
        //tvname.setText("Daniel");
        String nombre = "Daniel";//tvname.getText().toString();
        bundle.putString("nombre", nombre);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, actualizarDatosFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		actualizarDatosFragment.setArguments(bundle);
		transaction.commit();

    }

    private void cambiarContrasena(){
        Fragment actualizarDatosFragment = new CambiarContrasenaFragmentTutor();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, actualizarDatosFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
