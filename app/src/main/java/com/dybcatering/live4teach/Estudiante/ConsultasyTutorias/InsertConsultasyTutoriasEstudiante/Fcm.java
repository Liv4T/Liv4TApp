package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dybcatering.live4teach.Estudiante.Inicio.InicioActivity;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles.ListadoOfflineConsultasDisponibles;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasOnlineDisponibles.DetalleConsultaOnline;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Fcm extends FirebaseMessagingService {

	FirebaseUser firebaseUser;
	SessionManager sessionManager;

	@Override
	public void onNewToken(String s) {
		super.onNewToken(s);
	//	guardarToken(s);
	}

	private void guardarToken(String s) {

		sessionManager = new SessionManager(Fcm.this);
		HashMap<String, String> user = sessionManager.getUserDetail();
		String uuid  = user.get(SessionManager.UUID);

		DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Tokens");
		ref.child(uuid).child(s).setValue(s);

	}

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		super.onMessageReceived(remoteMessage);

		String from  = remoteMessage.getFrom();

		Log.e("TAG", "Mensaje Recibido de"+ from);

		if (remoteMessage.getData().size() >0){

			String titulo = remoteMessage.getData().get("titulo");
			String detalle = remoteMessage.getData().get("detalle");
			String categoria = remoteMessage.getData().get("categoria");

			String categoriaonline = "Consulta Online";
			String categoriaoffline = "Consulta Offline";

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && categoria.equals(categoriaoffline)){

					mayorqueoreooffline(titulo, detalle);
				} else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O){

					menorqueoreooffline();
				}


				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && categoria.equals(categoriaonline)){

					mayorqueoreoonline(titulo, detalle);
				} else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O && categoria.equals("Consulta Online")){

					menorqueoreoonline();
				}


		}
	}

	private void menorqueoreooffline() {
	}

	private void mayorqueoreooffline(String titulo, String detalle){
		String id = "mensaje";

		NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
			NotificationChannel nc = new NotificationChannel(id, "nuevo", NotificationManager.IMPORTANCE_HIGH);
			nc.setShowBadge(true);
			assert nm != null;
			nm.createNotificationChannel(nc);

		}
		builder.setAutoCancel(true)
				.setWhen(System.currentTimeMillis())
				.setContentTitle("Nueva Consulta Offline: " + detalle)
				.setSmallIcon(R.drawable.logo)
				.setContentText("Usuario: " + titulo)
				.setContentIntent(clickintentoffline(titulo, detalle))
				.setContentInfo("nuevo");
		Random random = new Random();
		int idNotify = random.nextInt(8000);



		assert  nm  !=null;
		nm.notify(idNotify, builder.build());
	}

	public PendingIntent clickintentoffline(String titulo, String detalle){
		Intent nf  = new Intent(getApplicationContext(), ListadoOfflineConsultasDisponibles.class);
		nf.putExtra("titulo", titulo);
		nf.putExtra("detalle", detalle);
		nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return PendingIntent.getActivity(this, 0, nf, 0);
	}

	private void menorqueoreoonline() {

	}

	private void mayorqueoreoonline(String titulo, String detalle) {
		String id = "mensaje";

		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel nc = new NotificationChannel(id, "nuevo", NotificationManager.IMPORTANCE_HIGH);
			nc.setShowBadge(true);
			assert nm != null;
			nm.createNotificationChannel(nc);

		}
		builder.setAutoCancel(true)
				.setWhen(System.currentTimeMillis())
				.setContentTitle("Nueva Consulta Online: " + detalle)
				.setSmallIcon(R.drawable.logo)
				.setContentText("Usuario: " + titulo)
				.setContentIntent(clickintentonline(titulo, detalle))
				.setContentInfo("nuevo");
		Random random = new Random();
		int idNotify = random.nextInt(8000);


		assert nm != null;
		nm.notify(idNotify, builder.build());
	}
	public PendingIntent clickintentonline(String titulo, String detalle){
		Intent nf  = new Intent(getApplicationContext(), DetalleConsultaOnline.class);
		nf.putExtra("titulo", titulo);
		nf.putExtra("detalle", detalle);
		nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return PendingIntent.getActivity(this, 0, nf, 0);
	}
}
