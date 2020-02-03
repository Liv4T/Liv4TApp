package com.dybcatering.live4teach.Splash.Estudiante.MisCursos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.MisCursosAdaptor;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.MisCursosItem;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePreCompra.MisCursosDetalleFragment;

import java.util.ArrayList;

public class MisCursosFragment extends Fragment {
    View myView;
	private RecyclerView mRecyclerView;
	private MisCursosAdaptor misCursosAdaptor;
	private ArrayList<MisCursosItem> mcursosItems;
	private RequestQueue mRequestQueue;
	public static final String EXTRAID = "id";
	public static final String EXTRANAME = "name";
	public static final String EXTRAMETHODOLOGY = "methodology";
	public static final String EXTRAWELCOME = "welcome";
	public static final String EXTRAINTENTION = "intention";
	public static final String EXTRAINTENSITYAC = "intensityAC";
	public static final String EXTRACOMPETENCES = "competences";
	public static final String EXTRAINTENSITYTA = "intensityTA";
	public static final String EXTRAACHIEVEMENT = "achievement";
	public static final String EXTRAINDICATORA = "indicatorA";
	public static final String EXTRAMETHODOLOGYG = "methodologyG";
	public static final String EXTRATYPE = "type";
	public static final String EXTRADESCRIPTION = "description";
	public static final String EXTRADESCRIPTIONO = "descriptionO";
	public static final String EXTRAUPDATED_AT = "updated_at";
	public static final String EXTRASTATE = "state";
	public static final String EXTRAIMAGE = "image";
	public static final String EXTRAVIDEO_PRESENTACION = "video_presentacion";
	public static final String EXTRATOPIC = "topic";
	public static final String EXTRAPUBLISH = "publish";
	public static final String EXTRAIDTEMAS = "idtemas";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mis_cursos, container, false);
		new CheckInternetConnection(getActivity()).checkConnection();

        return myView;
    }


    private void swapFragment(){
        Fragment someFragment = new MisCursosDetalleFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment ).addToBackStack("tag"); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }


}
