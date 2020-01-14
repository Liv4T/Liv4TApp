package com.dybcatering.live4teach.Splash.Estudiante;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.dybcatering.live4teach.R;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubPrimerActivity extends AppCompatActivity {

    private TextView tvemail,tvphone;

    private TextView namebutton;
    private CircleImageView primage;
    private TextView updateDetails;
    private LinearLayout addressview;

    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17;
    private TextView ed;
    ArrayList botones;
    RelativeLayout X;
    int contador,total=0,intento=0;
    Bundle temp;
    //to get user session data
   // private UserSession session;
    private HashMap<String,String> user;
    private String name,email,photo,mobile;
    private SliderLayout sliderShow;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_primer);


      /*  b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        b7=(Button)findViewById(R.id.button7);
        b8=(Button)findViewById(R.id.button8);
        b9=(Button)findViewById(R.id.button9);
        b10=(Button)findViewById(R.id.button10);
        b11=(Button)findViewById(R.id.button11);
        b12=(Button)findViewById(R.id.button12);
        b13=(Button)findViewById(R.id.button13);
        b14=(Button)findViewById(R.id.button14);
        b15=(Button)findViewById(R.id.button15);
        b16=(Button)findViewById(R.id.button16);
        b17=(Button)findViewById(R.id.button17);
        botones=new ArrayList<Button>();
        X= (RelativeLayout)findViewById(R.id.game);
        ed = (TextView)findViewById(R.id.textView);
        ed.setText("Intentos : "+intento+"\r\ntotal : "+total);
        contador=16;


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText("1");
                botones.add(b1);
                comparar2();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setText("1");
                botones.add(b2);
                comparar2();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3.setText("2");
                botones.add(b3);
                comparar2();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4.setText("3");
                botones.add(b4);
                comparar2();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b5.setText("4");
                botones.add(b5);
                comparar2();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b6.setText("2");
                botones.add(b6);
                comparar2();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b7.setText("4");
                botones.add(b7);
                comparar2();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b8.setText("3");
                botones.add(b8);
                comparar2();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b9.setText("8");
                botones.add(b9);
                comparar2();
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b10.setText("7");
                botones.add(b10);
                comparar2();
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b11.setText("6");
                botones.add(b11);
                comparar2();
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b12.setText("5");
                botones.add(b12);
                comparar2();
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b13.setText("7");
                botones.add(b13);
                comparar2();
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b14.setText("5");
                botones.add(b14);
                comparar2();
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b15.setText("8");
                botones.add(b15);
                comparar2();
            }
        });
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b16.setText("6");
                botones.add(b16);
                comparar2();
            }
        });

        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciar();
            }
        });
      /*  Button button = findViewById(R.id.button8);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubPrimerActivity.this, "Eureka", Toast.LENGTH_SHORT).show();
            }
        });*/
           // initialize();

           // inflateImageSlider();
    }

    private void reiniciar() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

   /* private void initialize() {

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


    /*private void inflateImageSlider() {

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

    }*/

    public void comparar2() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                comparar();

            }
        }, 100);
    }

    public void comparar(){


        if(botones.size()==2){
            //    new Timer().schedule(new TimerTask() {
            //      @Override
            //    public void run() {

            Button a = (Button) botones.get(0);
            Button b = (Button) botones.get(1);
            botones.clear();
            intento = intento + 1;
            if (a.getText().equals(b.getText())) {


                total = total + 1;

                a.setEnabled(false);
                b.setEnabled(false);
                contador -= 2;
                if (contador == 0) {
                    Toast.makeText(getApplicationContext(), "Has Ganado", Toast.LENGTH_LONG).show();
                }
            } else {

                a.setText("");
                b.setText("");


            }
            if(botones.size()>2){
                botones.clear();
                a.setText("");
                b.setText("");
            }

            ed.setText("Intentos : " + intento + "\r\nNo Parejas: " + total);
            //}
            //}, 2000);


        }



    }

}
