package com.dybcatering.live4teach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubPrimerActivity extends AppCompatActivity {

    private TextView tvemail,tvphone;

    private TextView namebutton;
    private CircleImageView primage;
    private TextView updateDetails;
    private LinearLayout addressview;


    //to get user session data
   // private UserSession session;
    private HashMap<String,String> user;
    private String name,email,photo,mobile;
    private SliderLayout sliderShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_primer);

      /*  Button button = findViewById(R.id.button8);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubPrimerActivity.this, "Eureka", Toast.LENGTH_SHORT).show();
            }
        });*/
            initialize();

            inflateImageSlider();
    }

    private void initialize() {

        addressview = findViewById(R.id.addressview);
        primage=findViewById(R.id.profilepic);
        tvemail=findViewById(R.id.emailview);
        tvphone=findViewById(R.id.mobileview);
        namebutton=findViewById(R.id.name_button);
       // updateDetails=findViewById(R.id.updatedetails);


        /*   updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,UpdateData.class));
                finish();
            }
        });

        addressview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,Wishlist.class));
            }
        });*/
    }

    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = findViewById(R.id.slider);

        //populating Image slider
        ArrayList<String> sliderImages= new ArrayList<>();
        sliderImages.add("http://uploads.printland.in/fnf/online2017/home_republic_day.jpg");
        sliderImages.add("http://uploads.printland.in/fnf/online2017/calender-homepage-29-dec.jpg");
        sliderImages.add("http://uploads.printland.in/fnf/online2017/notebook-homepage-05-dec.jpg");
        sliderImages.add("http://uploads.printland.in/fnf/online2017/home-vtds.jpg");

        for (String s:sliderImages){
            DefaultSliderView sliderView=new DefaultSliderView(this);
            sliderView.image(s);
            sliderShow.addSlider(sliderView);
        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }
}
