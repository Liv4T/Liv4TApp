package com.dybcatering.live4teach.Perfil;

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

public class Perfil extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View myView;
    private TextView tvemail,tvphone;

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
        myView = inflater.inflate(R.layout.perfil_fragment, container, false);

        initialize();

        inflateImageSlider();
        namebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Actualizar.class);
                startActivity(intent);
            }
        });

        return myView;
    }

    private void initialize() {

        addressview = myView.findViewById(R.id.addressview);
        primage=myView.findViewById(R.id.profilepic);
        tvemail=myView.findViewById(R.id.emailview);
        tvphone=myView.findViewById(R.id.mobileview);
        namebutton=myView.findViewById(R.id.btn_actualizar);
     //   updateDetails=myView.findViewById(R.id.updatedetails);




/*
        addressview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,Wishlist.class));
            }
        });*/
    }

    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = myView.findViewById(R.id.slider);

        //populating Image slider
        ArrayList<String> sliderImages= new ArrayList<>();
        //sliderImages.add("https://dev-res.thumbr.io/libraries/27/08/11/lib/1469777955350_1.jpg?size=854x493s&ext=jpg");
        sliderImages.add("http://192.168.1.102/imagenes/cover1.png");
        sliderImages.add("http://192.168.1.102/imagenes/cover2.png");
        sliderImages.add("http://192.168.1.102/imagenes/cover3.png");
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
