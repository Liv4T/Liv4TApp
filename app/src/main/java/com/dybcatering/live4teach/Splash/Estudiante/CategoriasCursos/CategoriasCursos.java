package com.dybcatering.live4teach.Splash.Estudiante.CategoriasCursos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dybcatering.live4teach.Splash.Estudiante.CategoriasCursos.SubCursoCategoria.ComunicacionCursos;
import com.dybcatering.live4teach.Splash.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.Perfil.ActualizarDatosFragment;

public class CategoriasCursos extends Fragment {

    View myView;
    public ImageView imagen, imagen_segunda, imagen_tercera, imagen_cuarta, imagen_quinta, imagen_novena;
    public CardView primer_card,segundo_card, tercer_card, cuarto_card, quinto_card, sexto_card, septimo_card, octavo_card, noveno_card, decimo_card, onceavo_card, doceavo_card ;

    private Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.categorias_cursos, container, false);
        new CheckInternetConnection(getActivity()).checkConnection();

        primer_card = myView.findViewById(R.id.primer_card);

        primer_card.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				transicionFragment();
			}
		});
        /*  imagen = myView.findViewById(R.id.imagen_primera);
        imagen_segunda = myView.findViewById(R.id.imagen_segunda);
        imagen_tercera = myView.findViewById(R.id.imagen_tercera);
        imagen_cuarta= myView.findViewById(R.id.imagen_cuarta);
        imagen_quinta = myView.findViewById(R.id.imagen_quinta);
        primer_card = myView.findViewById(R.id.primer_card);
        segundo_card = myView.findViewById(R.id.card_two);
        tercer_card = myView.findViewById(R.id.card_third);
        cuarto_card = myView.findViewById(R.id.card_fourth);
        // btn = myView.findViewById(R.id.btnAgregarCurso);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "has hecho click", Toast.LENGTH_SHORT).show();
            }
        });*/
		//Picasso.with(getActivity()).load("http://192.168.1.101/imagenes/primer_curso.jpg")
        /*****************
         Picasso.with(getActivity()).load("http://digitalandroidservices.com/personal/cover1.jpg")
**
  **              .fit().
    **            //      resize(400,400).
      **          // resize(100,100).
        **                centerInside().
**
**                   into(imagen);
**
  */
		/*
        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/html.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });
        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/imagencurso.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen_segunda, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });

        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/marketing.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen_tercera, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });

        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/quimica.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen_cuarta, new Callback() {
            @Override public void onSuccess() {}
            @Override public void onError() {}
        });

        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/varios.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen_quinta, new Callback() {
            @Override public void onSuccess() {}
            @Override public void onError() {}
        });


        primer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrimerCurso.class);
                startActivity(intent);
               // getActivity().finish();
            }
        });


        segundo_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SegundoCurso.class);
                startActivity(intent);
               // getActivity().finish();
            }
        });
        tercer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TercerCurso.class);
                startActivity(intent);
          //      getActivity().finish();
            }
        });
        cuarto_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CuartoCurso.class);
                startActivity(intent);
            //    getActivity().finish();
            }
        });

     /*   imagen_quinta = myView.findViewById(R.id.imagen_quinta);
        Picasso.with(getActivity()).load("https://pruebalive4teach.000webhostapp.com/imagenes/primer_curso.jpg")
                .placeholder(R.drawable.internetconnection).fit().into(imagen_quinta, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });


        imagen_novena= myView.findViewById(R.id.imagen_novena);
        Picasso.with(getActivity()).load("https://pruebalive4teach.000webhostapp.com/imagenes/primer_curso.jpg")
                .placeholder(R.drawable.internetconnection).fit().into(imagen_novena, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });*/



        return myView;
    }

	private void transicionFragment() {
		Fragment someFragment = new ComunicacionCursos();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();
	}

}
