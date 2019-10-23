package com.dybcatering.live4teach.Tutor.Perfil;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.dybcatering.live4teach.R;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilTutor extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View myView;
    private TextView tvname, tvemail,tvphone, tvidenti;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.perfil_tutor_fragment, container, false);
        addressview = myView.findViewById(R.id.addressview);
        primage=myView.findViewById(R.id.profilepic);
        tvname=myView.findViewById(R.id.nameview);
        tvemail=myView.findViewById(R.id.emailview);
        tvphone=myView.findViewById(R.id.mobileview);
        tvidenti= myView.findViewById(R.id.cedula);
        namebutton=myView.findViewById(R.id.btn_actualizar);

        final String nombre = tvname.getText().toString();
        final String correo = tvemail.getText().toString();
        final String telefono = tvphone.getText().toString();
        final String identificacion = tvidenti.getText().toString();
        inflateImageSlider();
        namebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActualizarDatosTutor.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("correo", correo);
                intent.putExtra("telefono", telefono);
                intent.putExtra("identificacion", identificacion);
                startActivity(intent);
            }
        });

        return myView;
    }



    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = myView.findViewById(R.id.slider);

        //populating Image slider
        ArrayList<String> sliderImages= new ArrayList<>();
        //sliderImages.add("https://dev-res.thumbr.io/libraries/27/08/11/lib/1469777955350_1.jpg?size=854x493s&ext=jpg");
        sliderImages.add("http://192.168.1.101/imagenes/cover2.png");
        sliderImages.add("http://pruebalive4teach.000webhostapp.com/imagenes/cover1.png");
        sliderImages.add("http://192.168.1.101/imagenes/cover3.png");
        //sliderImages.add("https://dev-res.thumbr.io/libraries/27/08/11/lib/1469777955350_1.jpg?size=854x493s&ext=jpg");

        for (String s:sliderImages){
            DefaultSliderView sliderView=new DefaultSliderView(getActivity());
            sliderView.image(s);
            sliderShow.addSlider(sliderView);
        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
