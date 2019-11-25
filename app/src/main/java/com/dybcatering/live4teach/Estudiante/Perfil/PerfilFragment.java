package com.dybcatering.live4teach.Estudiante.Perfil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dybcatering.live4teach.BuildConfig;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetalleFragment;
import com.dybcatering.live4teach.R;
import com.nex3z.notificationbadge.NotificationBadge;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragment extends Fragment {
    View myView;
    private TextView tvname, tvemail,tvphone, tvidenti, txtVersion;

    private Button namebutton;
    private CircleImageView primage;
    private Button updateDetails;
    private LinearLayout addressview;

    //to get user session data
    // private UserSession session;
    private HashMap<String,String> user;
    private String name,email,photo,mobile;
    private SliderLayout sliderShow;


    NotificationBadge mBadge;
    private int count =0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_perfil, container, false);
        addressview = myView.findViewById(R.id.addressview);
        primage=myView.findViewById(R.id.profilepic);
        tvname=myView.findViewById(R.id.nameview);
        tvemail=myView.findViewById(R.id.emailview);
        tvphone=myView.findViewById(R.id.mobileview);
        tvidenti= myView.findViewById(R.id.cedula);
        namebutton=myView.findViewById(R.id.btn_actualizar_perfil);
        txtVersion = myView.findViewById(R.id.txtPerfilVersion);
        txtVersion.setText("Versión: "+ BuildConfig.VERSION_NAME);
        final String nombre = tvname.getText().toString();
        final String correo = tvemail.getText().toString();
        final String telefono = tvphone.getText().toString();
        final String identificacion = tvidenti.getText().toString();
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
        Fragment someFragment = new ActualizarDatosFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
