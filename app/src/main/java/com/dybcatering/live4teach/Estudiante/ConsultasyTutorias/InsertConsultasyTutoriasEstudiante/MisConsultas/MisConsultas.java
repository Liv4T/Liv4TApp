package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante.MisConsultas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Fragments.ChatsFragment;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Model.Chat;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MisConsultas extends Fragment {

	public MisConsultas() {
		// Required empty public constructor
	}

	View MyView;

	DatabaseReference reference;
	ValueEventListener seenListener;
	FirebaseUser firebaseUser;

	SessionManager sessionManager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		MyView = inflater.inflate(R.layout.fragment_mis_consultas, container, false);

		firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		final String uuid = user.get(SessionManager.UUID);
		reference = FirebaseDatabase.getInstance().getReference("ConsultasEnviadasOffline").child(uuid);

		Query query = FirebaseDatabase.getInstance().getReference("ConsultasEnviadasOffline").orderByChild("remitente").equalTo(uuid);

		seenListener = query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				for (DataSnapshot snapshot : dataSnapshot.getChildren()){
					Chat chat = snapshot.getValue(Chat.class);

				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});
		final TabLayout tabLayout = MyView.findViewById(R.id.tablayout);
		final ViewPager viewPager = MyView.findViewById(R.id.view_pager);
		return MyView;
	}
}

