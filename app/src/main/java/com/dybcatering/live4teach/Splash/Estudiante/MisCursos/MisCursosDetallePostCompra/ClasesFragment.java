package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class ClasesFragment extends Fragment {
	View view;
	PlayerView playerView;
	public ClasesFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view =inflater.inflate(R.layout.fragment_clases, container, false);


		playerView = view.findViewById(R.id.video_view);

		initializePlayer("https://dybcatering.com/back_live_app/videos/videoplayback.mp4");

		return view;
	}

	private void initializePlayer(String link) {
		SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
				new DefaultRenderersFactory(getContext()),
				new DefaultTrackSelector(), new DefaultLoadControl());
		String filePath = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdcc8_5-mix-wet-and-cry-batter-together-brownies/5-mix-wet-and-cry-batter-together-brownies.mp4";
		//Environment.getExternalStorageDirectory() + File.separator +
//				"video" + File.separator + "video1.mp4";
		Log.e("filepath", filePath);
		Uri uri = Uri.parse(link);

		ExtractorMediaSource audioSource = new ExtractorMediaSource(
				uri,
				new DefaultDataSourceFactory(getContext(), "MyExoplayer"),
				new DefaultExtractorsFactory(),
				null,
				null
		);

		player.prepare(audioSource);
		playerView.setPlayer(player);
		player.setPlayWhenReady(false);
	}

}
