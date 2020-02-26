package com.dybcatering.live4teach.Tutor.Consulta.ConsultasyTutorias;

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
import android.widget.TextView;

import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasyTutorias.Fragments.ChatsFragment;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasyTutorias.Fragments.UsersFragment;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasyTutorias.Model.Chat;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasyTutorias.Model.User;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ConsultasTutor extends Fragment {

	public ConsultasTutor() {
		// Required empty public constructor
	}

	CircleImageView profile_image;
	TextView username;

	FirebaseUser firebaseUser;
	DatabaseReference reference;
	Query query;
	SessionManager sessionManager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_consultas_tutor, container, false);

		//Toolbar toolbar = view.findViewById(R.id.toolbar);
	//	((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
	//	((AppCompatActivity)getActivity()).setTitle("");

		//profile_image= view.findViewById(R.id.profile_image);
		//username = view.findViewById(R.id.username_profile);

		//firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
		sessionManager = new SessionManager(getContext());
		HashMap<String, String> user = sessionManager.getUserDetail();
		final String username = user.get(SessionManager.USER_NAME);

		query = FirebaseDatabase.getInstance().getReference("Users")
				.orderByChild("username")
				.equalTo(username);

		query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				User user  = dataSnapshot.getValue(User.class);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});
	//	reference = FirebaseDatabase.getInstance().getReference("Users").child("mildredfigueroaq+1");//firebaseUser.getUid());

	/*	reference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				User user  = dataSnapshot.getValue(User.class);
		//		username.setText(user.getUsername());
			/*	if (user.getImageURL().equals("default")){
					profile_image.setImageResource(R.drawable.perfil);
				}else {
					Picasso.with(getContext()).load(user.getImageURL()).into(profile_image);
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});*/

		final TabLayout tabLayout = view.findViewById(R.id.tablayout);
		final ViewPager viewPager = view.findViewById(R.id.view_pager);



		query = FirebaseDatabase.getInstance().getReference("Chats");
		query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
				int unread = 0;
				for (DataSnapshot snapshot : dataSnapshot.getChildren()){
					Chat chat = snapshot.getValue(Chat.class);
					//if (chat.getReceiver().equals(usernamefirebaseUser.getUid()) && !chat.isIsseen()){
					if (chat.getReceiver().equals(username) && !chat.isIsseen()){
						unread++;
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


	/*private void status(String status){
		query =  FirebaseDatabase.getInstance().getReference("Users").orderByChild("username")
				.equalTo(username);//child(firebaseUser.getUid());

		HashMap<String , Object> hashMap = new HashMap<>();
		hashMap.put("status", status);

		//reference.updateChildren(hashMap);
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
	}*/
}
