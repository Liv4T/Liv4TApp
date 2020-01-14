package com.dybcatering.live4teach.Splash.Estudiante.MisCursos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.dybcatering.live4teach.R;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

public class MisCursosDetalleFragment extends Fragment {

	YouTubePlayer youTubePlayer;
	LottieAnimationView scrolldebajo;
	View view;
	TextView descText, segunda_desc, tercera_desc;
	ProgressBar progressBar;
	ImageButton show, hide, show2, hide2, show3, hide3;
	private static final String TAG = "MisCursosDetalle";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate (R.layout.fragment_mis_cursos_detalle, container, false);

		scrolldebajo = view.findViewById(R.id.buttoniniciar);

		scrolldebajo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "hola", Toast.LENGTH_SHORT).show();
				reproducirvideo(view);


			}
		});





		progressBar = view.findViewById(R.id.progressBar_horizontal);

		descText = (TextView)  view.findViewById(R.id.description_text);
		show = (ImageButton)  view.findViewById(R.id.show);
		show.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				show.setVisibility(View.INVISIBLE);
				hide.setVisibility(View.VISIBLE);
				descText.setMaxLines(Integer.MAX_VALUE);
				//ocultar los otros, se debe implementar en los demas

				hide2.setVisibility(View.INVISIBLE);
				show2.setVisibility(View.VISIBLE);
				segunda_desc.setMaxLines(5);
				hide3.setVisibility(View.INVISIBLE);
				show3.setVisibility(View.VISIBLE);
				tercera_desc.setMaxLines(5);

			}
		});
		hide = (ImageButton) view.findViewById(R.id.hide);
		hide.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hide.setVisibility(View.INVISIBLE);
				show.setVisibility(View.VISIBLE);
				descText.setMaxLines(5);

			}
		});

		segunda_desc = view.findViewById(R.id.segunda_descripcion);
		show2 = view.findViewById(R.id.vermassegundo);
		show2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				show2.setVisibility(View.INVISIBLE);
				hide2.setVisibility(View.VISIBLE);
				segunda_desc.setMaxLines(Integer.MAX_VALUE);

				hide.setVisibility(View.INVISIBLE);
				show.setVisibility(View.VISIBLE);
				descText.setMaxLines(5);
				hide3.setVisibility(View.INVISIBLE);
				show3.setVisibility(View.VISIBLE);
				tercera_desc.setMaxLines(5);

			}
		});
		hide2 = view.findViewById(R.id.hide2);
		hide2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				hide2.setVisibility(View.INVISIBLE);
				show2.setVisibility(View.VISIBLE);
				segunda_desc.setMaxLines(5);
			}
		});

		tercera_desc = view.findViewById(R.id.tercera_descripcion);
		show3= view.findViewById(R.id.vermastercero);
		show3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				show3.setVisibility(View.INVISIBLE);
				hide3.setVisibility(View.VISIBLE);
				tercera_desc.setMaxLines(Integer.MAX_VALUE);

				hide.setVisibility(View.INVISIBLE);
				show.setVisibility(View.VISIBLE);
				descText.setMaxLines(5);
				hide2.setVisibility(View.INVISIBLE);
				show2.setVisibility(View.VISIBLE);
				segunda_desc.setMaxLines(5);


			}
		});
		hide3 = view.findViewById(R.id.hide3);
		hide3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hide3.setVisibility(View.INVISIBLE);
				show3.setVisibility(View.VISIBLE);
				tercera_desc.setMaxLines(5);
			}
		});

		return view;
	}

	public void reproducirvideo(View view) {
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

	public void Ejemplo(){

	}

}
