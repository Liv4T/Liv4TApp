package com.dybcatering.live4teach.Tutor.Liv4T.PlanificacionGeneral;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.Clases.ClasesFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.ViewPagerAdapter;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.PlanificacionGeneral.Anual.PlanificacionAnual;
import com.dybcatering.live4teach.Tutor.Liv4T.PlanificacionGeneral.Trimestral.PlanificacionTrimestral;


public class PlanificacionGeneralTutorFragment extends Fragment {


    public PlanificacionGeneralTutorFragment() {
        // Required empty public constructor
    }

    View myView;

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_planificacion_general_tutor, container, false);


        tabLayout = myView.findViewById(R.id.tablayout_id);
        appBarLayout = myView.findViewById(R.id.appBarId);
        viewPager = myView.findViewById(R.id.viewpager);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //adapter = new ViewPagerAdapter(getChildFragmentManager(), MisCursosDetalleFragment.this);
        adapter.AddFragment(new PlanificacionAnual(), "Anual");
        adapter.AddFragment(new PlanificacionTrimestral(), "Trimestral");
        //adapter.AddFragment(new TerceroFragment(), "TerceroFragment");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return myView;
    }
}
