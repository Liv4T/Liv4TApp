package com.dybcatering.live4teach.Tutor.Liv4T.Inicio;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.dybcatering.live4teach.Estudiante.CursosDisponibles.CursosFragment;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Estudiante.Liv4T.Anuncios.AnunciosFragment;
import com.dybcatering.live4teach.Estudiante.Liv4T.Boletin.BoletinFragment;
import com.dybcatering.live4teach.Estudiante.Liv4T.Calendario.CalendarioFragment;
import com.dybcatering.live4teach.Estudiante.Liv4T.Chat.ListadoConversaciones;
import com.dybcatering.live4teach.Estudiante.Liv4T.Horario.HorarioFragment;
import com.dybcatering.live4teach.Estudiante.Liv4T.Mensajes.MisMensajes;
import com.dybcatering.live4teach.Estudiante.Liv4T.Notas.NotasFragment;
import com.dybcatering.live4teach.Estudiante.Liv4T.Perfil.PerfilEstudiante;
import com.dybcatering.live4teach.Estudiante.Liv4T.Tareas.TareasFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.Adapter.ExampleAdaptor;
import com.dybcatering.live4teach.Estudiante.MisCursos.Adapter.ExampleItem;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.MisClasesFragment;
import com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.MisCursosTutorLiv4T;
import com.geniusforapp.fancydialog.FancyAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;

public class InicioTutor extends Fragment implements ExampleAdaptor.OnItemClickListener {

    public InicioTutor() {
        // Required empty public constructor
    }

    public ImageView imagen, imagen_segunda, imagen_tercera, imagen_cuarta, imagen_quinta, imagen_novena;
    public CardView primer_card,segundo_card, tercer_card, cuarto_card, quinto_card, sexto_card, septimo_card, octavo_card, noveno_card, decimo_card, onceavo_card, doceavo_card ;

    private Button btn;

    private SessionManager session;

    private RecyclerView mRecyclerView;
    private ExampleAdaptor mExampleAdaptor;
    private ArrayList<ExampleItem> mexampleItems;
    private RequestQueue mRequestQueue;
    LinearLayout perfil, mensajes, miscursos,  horario, notas, boletin, anuncios, chat, clases, actividades;

    public TextView nombreperfil, salir;
    View myView;

    String id, name;

    /*
    calendario,
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_inicio_tutor, container, false);

        perfil = myView.findViewById(R.id.linearperfil);
        mensajes = myView.findViewById(R.id.layoutmensajes);
        horario = myView.findViewById(R.id.linearhorario);
        miscursos = myView.findViewById(R.id.linearmiscursos);
        clases = myView.findViewById(R.id.linearclases);
        actividades = myView.findViewById(R.id.linearactividades);
        //calendario = myView.findViewById(R.id.linearcalendario);
        notas = myView.findViewById(R.id.linearnotas);
        boletin = myView.findViewById(R.id.linearboletin);
        anuncios = myView.findViewById(R.id.linearanuncios);
        nombreperfil = myView.findViewById(R.id.txtNombreUsuario);
        chat = myView.findViewById(R.id.linearchat);
        salir = myView.findViewById(R.id.salir);



        new CheckInternetConnection(getContext()).checkConnection();
        session = new SessionManager(getContext());

        HashMap<String, String> user = session.getUserDetail();
        id = user.get(SessionManager.ID);
        name = user.get(SessionManager.NAME);
        nombreperfil.setText(name);

        chat.setOnClickListener(v -> {
            Intent chat = new Intent(getContext(), ListadoConversaciones.class);
            startActivity(chat);
        });

        miscursos.setOnClickListener(v -> {
            transicionMisCursos();
        });

        salir.setOnClickListener(v -> {
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
                            session = new SessionManager(getContext());
                            session.logout();
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
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfil();
            }
        });

        mensajes.setOnClickListener(v -> mensajes());

        clases.setOnClickListener(v -> transicionclases());

        /*
        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transicionCalendario();
            }
        });
         */


        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transicionHorario();
            }
        });
        notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transicionNotas();
            }
        });
        boletin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transicionBoletin();
            }
        });

        anuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transicionAnuncios();
            }
        });


        return myView;
    }

    private void transicionMisCursos() {
        Fragment miscursos = new MisCursosTutorLiv4T();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, miscursos); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void transicionAnuncios() {
        Fragment perfil = new AnunciosFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();

    }

    private void transicionBoletin() {

        Fragment perfil = new BoletinFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();

    }

    private void transicionNotas() {

        Fragment perfil = new NotasFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();

    }

    private void transicionHorario() {
        Fragment perfil = new HorarioFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void transicionCalendario() {
        Fragment perfil = new CalendarioFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void transicionCalendario2() {


    }

    private void transicionclases() {
        Fragment perfil = new MisClasesFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void perfil(){
        Fragment perfil = new PerfilEstudiante();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
    private void mensajes(){
        Fragment perfil = new MisMensajes();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }


    private void transicionFragment() {
        Fragment someFragment = new CursosFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    @Override
    public void onItemClick(int position) {

    }
}
