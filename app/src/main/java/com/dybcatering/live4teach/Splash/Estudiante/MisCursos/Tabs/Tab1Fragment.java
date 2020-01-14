package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.Tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.dybcatering.live4teach.R;

public class Tab1Fragment extends Fragment {
	public static final String TAG = "Tab1Fragment";

	private Button btnTest;
	View view;
	TextView descText, segunda_desc, tercera_desc;
	ProgressBar progressBar;
	ImageButton show, hide, show2, hide2, show3, hide3;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tab1, container, false);


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

		/**onInitializedListener = new com.google.android.youtube.player.YouTubePlayer.OnInitializedListener() {
		@Override
		public void onInitializationSuccess(com.google.android.youtube.player.YouTubePlayer.Provider provider, com.google.android.youtube.player.YouTubePlayer youTubePlayer, boolean b) {
		Log.d(TAG, "Iniciando video");
		youTubePlayer.loadVideo("YQRHrco73g4");
		}

		@Override
		public void onInitializationFailure(com.google.android.youtube.player.YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
		Log.d(TAG, "Falla");
		}
		};

		 button.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
		Log.d(TAG,"se realizo click");
		youTubePlayerView.initialize(YouTubeConfig.getApiKey(), onInitializedListener);


		Log.d(TAG, "exito");

		if (progressStatus == 100) {
		progressStatus = 0;
		}

		new Thread(new Runnable() {
		@Override
		public void run() {
		while (progressStatus < 100) {
		// Update the progress status
		progressStatus += 1;

		// Try to sleep the thread for 20 milliseconds
		try {
		Thread.sleep(80);  //3 seconds
		} catch (InterruptedException e) {
		e.printStackTrace();
		}

		// Update the progress bar
		handler.post(new Runnable() {
		@Override
		public void run() {
		progressBar.setProgress(progressStatus);
		// Show the progress on TextView
		//tv.setText(progressStatus + "/100");
		}
		});
		}
		}
		}).start();
		}
		});
		 **/


		btnTest = view.findViewById(R.id.btn_test1);

		btnTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Prueba de testing", Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}
}