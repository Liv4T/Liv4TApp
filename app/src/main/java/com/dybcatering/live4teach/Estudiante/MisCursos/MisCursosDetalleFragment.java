package com.dybcatering.live4teach.Estudiante.MisCursos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.dybcatering.live4teach.Estudiante.MisCursos.Tabs.SectionsPageAdapter;
import com.dybcatering.live4teach.Estudiante.MisCursos.Tabs.Tab1Fragment;
import com.dybcatering.live4teach.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import java.util.List;

public class MisCursosDetalleFragment extends Fragment {

	YouTubePlayer youTubePlayer;
	LottieAnimationView botoniniciar;
	View view;
	TextView descText, segunda_desc, tercera_desc;
	ProgressBar progressBar;
	ImageButton show, hide, show2, hide2, show3, hide3;
	private static final String TAG = "MisCursosDetalleTutor";


	private SectionsPageAdapter sectionsPageAdapter;

	private ViewPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate (R.layout.fragment_mis_cursos_detalle, container, false);

		//botoniniciar = view.findViewById(R.id.buttoniniciar);

/*		botoniniciar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Iniciando vídeo", Toast.LENGTH_SHORT).show();

				inicioyoutube(view);

			}
	});


		sectionsPageAdapter = new SectionsPageAdapter(getFragmentManager());
		setupViewPager(mViewPager);

		TabLayout tabLayout = view.findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);
*/
		return view;
	}

	public void inicioyoutube(View view) {
		YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
		youTubePlayerView.initialize(new YouTubePlayerInitListener() {
			@Override
			public void onInitSuccess(final YouTubePlayer initializedYouTubePlayer) {
				initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
					@Override
					public void onReady() {
						initializedYouTubePlayer.loadVideo("v9oyNBBXDi8", 0);

					}

				});

			}
		}, true);
	}


	private void setupViewPager (ViewPager viewPager){
		SectionsPageAdapter adapter = new SectionsPageAdapter(getFragmentManager());
		adapter.addFragment(new Tab1Fragment(), "TAB1");
		//adapter.addFragment(new Tab2Fragment(), "TAB2");
	//	adapter.addFragment(new Tab3Fragment(), "TAB3");
		viewPager.setAdapter(adapter);
	}

}
