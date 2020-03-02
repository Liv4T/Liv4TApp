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
import com.dybcatering.live4teach.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class Fcm extends FirebaseMessagingService {

	@Override
	public void onNewToken(String s) {
		super.onNewToken(s);
		guardarToken(s);
	}

	private void guardarToken(String s) {
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Usuarios");
		ref.child("Usuario").setValue(s);

	}

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		super.onMessageReceived(remoteMessage);

		String from  = remoteMessage.getFrom();

		Log.e("TAG", "Mensaje Recibido de"+ from);

		if (remoteMessage.getData().size() >0){

			String titulo = remoteMessage.getData().get("titulo");
			String detalle = remoteMessage.getData().get("detalle");

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

				mayorqueoreo(titulo, detalle);
			}
			if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O){

				menorqueoreo();
			}
		}
	}

	private void menorqueoreo() {
	}

	private void mayorqueoreo(String titulo, String detalle){
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
				.setContentTitle("Nuevo Consulta: " + detalle)
				.setSmallIcon(R.drawable.logo)
				.setContentText("Usuario: " + titulo)
				.setContentIntent(clickintent())
				.setContentInfo("nuevo");
		Random random = new Random();
		int idNotify = random.nextInt(8000);



		assert  nm  !=null;
		nm.notify(idNotify, builder.build());
	}

	public PendingIntent clickintent(){
		Intent nf  = new Intent(getApplicationContext(), InicioActivity.class);
		nf.putExtra("color", "rojo");
		nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return PendingIntent.getActivity(this, 0, nf, 0);
	}
}
