package com.dybcatering.live4teach.Perfil;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.R;
import com.nex3z.notificationbadge.NotificationBadge;

public class Perfil extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View myView;



    NotificationBadge mBadge;
    private int count =0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.perfil_fragment, container, false);



        return myView;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
