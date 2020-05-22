package com.dybcatering.live4teach.Tutor.Liv4T;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;

import java.util.HashMap;

public class InicioTutor extends Fragment {

    public InicioTutor() {
        // Required empty public constructor
    }

    View myView;
    SessionManager sessionManager;
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_inicio_tutor, container, false);
        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.NAME);
        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();


        return myView;
    }
}
