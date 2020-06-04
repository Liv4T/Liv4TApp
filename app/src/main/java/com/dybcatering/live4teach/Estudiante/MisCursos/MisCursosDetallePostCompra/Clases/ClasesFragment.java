package com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.Clases;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Estudiante.MisCursos.AdapterDetalleVideosMisCursos.VideosAdaptor;
import com.dybcatering.live4teach.Estudiante.MisCursos.AdapterDetalleVideosMisCursos.VideosItem;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ClasesFragment extends Fragment implements VideosAdaptor.OnItemClickListener{
	View view;
	PlayerView playerView;
	private boolean playWhenReady = true;
	private int currentWindow = 0;
	private long playbackPosition = 0;
	//public ClasesFragment() {
		// Required empty public constructor
	//}
	private RecyclerView mRecyclerView;
	private VideosAdaptor misVideosAdaptor;
	private ArrayList<VideosItem> mvideosItems;
	private RequestQueue mRequestQueue;
	private SimpleExoPlayer player;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view =inflater.inflate(R.layout.fragment_clases, container, false);

		new CheckInternetConnection(getActivity()).checkConnection();
		mRecyclerView = view.findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		mvideosItems= new ArrayList<>();

		mRequestQueue = Volley.newRequestQueue(getActivity());



		Bundle arguments = getParentFragment().getArguments();

		String idrecibido = arguments.getString("id");

		ObtenerDatos(idrecibido);


		return view;
	}




	private void ObtenerDatos(final String id) {

		String url = "http://dybcatering.com/back_live_app/miscursos/listarvideos.php";
		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Cargando...");
		progressDialog.show();
		progressDialog.setCancelable(false);
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						progressDialog.dismiss();
						try {
							JSONObject jsonObject = new JSONObject(response);
							JSONArray jsonArray = jsonObject.getJSONArray("Registros");
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject hit = jsonArray.getJSONObject(i);
								String id = hit.getString("id");
								String id_course = hit.getString("id_course");
								String id_courseunit = hit.getString("id_courseunit");
								String video = hit.getString("video");
								String nombre_video = hit.getString("nombre_video");
								String duration = hit.getString("duration");
								mvideosItems.add(new VideosItem(id, id_course, id_courseunit,video, nombre_video, duration));
							}
							misVideosAdaptor = new VideosAdaptor(getActivity(), mvideosItems);
							mRecyclerView.setAdapter(misVideosAdaptor);
							misVideosAdaptor.setOnClickItemListener(ClasesFragment.this);

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toasty.error(getContext(), "Parece que algo salió mal o aun no has agregado cursos", Toast.LENGTH_SHORT).show();
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
				progressDialog.dismiss();
				Toasty.error(getContext(), "Parece que algo salió mal o aun no has agregado cursos", Toast.LENGTH_SHORT).show();
			}
		}) {
			@Override
			protected Map<String, String > getParams(){
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);


	}


	@Override
	public void onItemClick(int position) {

		VideosItem videosItem = mvideosItems.get(position);
		String link = videosItem.getVideo();

		initializePlayer(link);

	}

	private void initializePlayer(String link) {

		SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
				new DefaultRenderersFactory(getContext()),
				new DefaultTrackSelector(), new DefaultLoadControl());
		//String filePath = "https://dybcatering.com/back_live_app/videos/htmlalta.mp4";
		//Environment.getExternalStorageDirectory() + File.separator +
//				"video" + File.separator + "video1.mp4";
		Log.e("filepath", link);
		Uri uri = Uri.parse(link);
		ExtractorMediaSource audioSource = new ExtractorMediaSource(
				uri,
				new DefaultDataSourceFactory(getContext(), "MyExoplayer"),
				new DefaultExtractorsFactory(),
				null,
				null
		);

//		player.prepare(audioSource);
//		playerView.setPlayer(player);
//		player.setPlayWhenReady(true);

		player.setPlayWhenReady(playWhenReady);
		player.seekTo(currentWindow, playbackPosition);
		playerView.setPlayer(player);
		player.prepare(audioSource, true, true);

//		player.prepare(audioSource);
//		playerView.setPlayer(player);
//		player.setPlayWhenReady(false);


	}



	@Override
	public void onPause() {
		super.onPause();
		if (Util.SDK_INT < 24) {
			releasePlayer();
		}
	}


	
	private void releasePlayer() {
		if (player != null) {
			playWhenReady = player.getPlayWhenReady();
			playbackPosition = player.getCurrentPosition();
			currentWindow = player.getCurrentWindowIndex();
			player.release();
			player = null;
		}
	}
}
