package com.dybcatering.live4teach.Tutor.Liv4T.MisClases;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

public class MisClasesFragment extends Fragment {

    public MisClasesFragment() {
        // Required empty public constructor
    }

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mis_clases, container, false);

        return myView;
    }

    public void Alert(){
/*

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Información");

            LayoutInflater inflater = this.getLayoutInflater();
            View order_address_comment = inflater.inflate(R.layout.sopas_item, null);




                alertDialog.setView(order_address_comment);


                alertDialog.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    }



            });


                alertDialog.show();

 */

            }
    }



