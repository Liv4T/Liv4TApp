package com.dybcatering.live4teach.Estudiante.MisCursos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.dybcatering.live4teach.R;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import java.util.List;

public class MisCursosDetalleFragment extends Fragment {

	YouTubePlayer youTubePlayer;
	LottieAnimationView scrolldebajo;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate (R.layout.fragment_mis_cursos_detalle, container, false);

		scrolldebajo = view.findViewById(R.id.buttoniniciar);

		scrolldebajo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "hola", Toast.LENGTH_SHORT).show();
			}
		});


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



		return view;
	}

}
