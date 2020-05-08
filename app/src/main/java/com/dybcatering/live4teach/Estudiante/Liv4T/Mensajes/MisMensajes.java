package com.dybcatering.live4teach.Estudiante.Liv4T.Mensajes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class MisMensajes extends Fragment {


    public MisMensajes() {
        // Required empty public constructor
    }
    View myView;
    CardView mensajeuno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

                myView = inflater.inflate(R.layout.fragment_mis_mensajes, container, false);
        // Inflate the layout for this fragment

        mensajeuno = myView.findViewById(R.id.carduno);
        final String correo = "correo@gmail.com";
        mensajeuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarMensajesDetalle(correo);
            }
        });

        return myView;
    }

    private void IniciarMensajesDetalle(String destinatario) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", destinatario, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, "Enviar Correo..."));

    }
}
