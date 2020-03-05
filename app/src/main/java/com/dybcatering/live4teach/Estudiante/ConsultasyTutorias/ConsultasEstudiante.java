package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Fragments.ChatsFragment;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Fragments.UsersFragment;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Model.Chat;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Model.User;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.Clases.ClasesFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.ViewPagerAdapter;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConsultasEstudiante extends Fragment {

	public ConsultasEstudiante() {
		// Required empty public constructor
	}

	CircleImageView profile_image;
	TextView username;

	FirebaseUser firebaseUser;
	DatabaseReference reference;

	SharedPreferences sharedPreferences;

	SessionManager sessionManager;
	String uuid;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_consultas_estudiante, container, false);

		//Toolbar toolbar = view.findViewById(R.id.toolbar);
	//	((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
	//	((AppCompatActivity)getActivity()).setTitle("");

		//profile_image= view.findViewById(R.id.profile_image);
		//username = view.findViewById(R.id.username_profile);

		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		uuid = user.get(SessionManager.UUID);

		firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
		reference = FirebaseDatabase.getInstance().getReference("Users").child(uuid);

		reference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				User user  = dataSnapshot.getValue(User.class);
		//		username.setText(user.getUsername());
			/*	if (user.getImageURL().equals("default")){
					profile_image.setImageResource(R.drawable.perfil);
				}else {
					Picasso.with(getContext()).load(user.getImageURL()).into(profile_image);
				}*/
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});

		final TabLayout tabLayout = view.findViewById(R.id.tablayout);
		final ViewPager viewPager = view.findViewById(R.id.view_pager);



		reference = FirebaseDatabase.getInstance().getReference("Chats");
		reference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
				int unread = 0;
				for (DataSnapshot snapshot : dataSnapshot.getChildren()){
					Chat chat = snapshot.getValue(Chat.class);
					if (chat.getReceiver().equals(uuid) && !chat.isIsseen()){
						unread++;
						viewPager.setAdapter(viewPagerAdapter);
					}
				}

				if (unread == 0) {
					viewPagerAdapter.addFragment(new ChatsFragment(), "Chats");
				//	viewPagerAdapter.AddFragment(new ChatsFragment(), "Chats");
				} else {
					viewPagerAdapter.addFragment(new ChatsFragment(), "("+unread+") Chats");
				}
				viewPagerAdapter.addFragment(new UsersFragment(), "Usuarios");
				//viewPagerAdapter.AddFragment(new ProfileFragment(), "Perfil");

				viewPager.setAdapter(viewPagerAdapter);

				tabLayout.setupWithViewPager(viewPager);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});



		return view;
	}


	class ViewPagerAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> fragments;
		private ArrayList<String> titles;

		ViewPagerAdapter(FragmentManager fragmentManager){
			super(fragmentManager);
			this.fragments = new ArrayList<>();
			this.titles = new ArrayList<>();
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		public  void addFragment(Fragment fragment, String title){
			fragments.add(fragment);
			titles.add(title);
		}


		@Nullable
		@Override
		public CharSequence getPageTitle(int position) {
			return titles.get(position);
		}
	}


	private void status(String status){

		reference = FirebaseDatabase.getInstance().getReference("Users").child(uuid);

		HashMap<String , Object> hashMap = new HashMap<>();
		hashMap.put("status", status);

		reference.updateChildren(hashMap);
	}


	@Override
	public void onResume() {
		super.onResume();
		status("Conectado");
	}

	@Override
	public void onPause() {
		super.onPause();
		status("Desconectado");
	}
}
