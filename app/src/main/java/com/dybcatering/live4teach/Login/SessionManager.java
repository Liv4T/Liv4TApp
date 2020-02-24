package com.dybcatering.live4teach.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dybcatering.live4teach.Estudiante.Inicio.InicioActivity;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.SplashActivity;
import com.dybcatering.live4teach.Tutor.InicioActivityTutor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String USER_NAME = "USER_NAME";
    public static final String ID = "ID";
    public static final String TYPE_USER = "TYPE_USER";
    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String name, String user_name, String id, String typeuser){

        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(USER_NAME, user_name);
        editor.putString(ID, id);
        editor.putString(TYPE_USER, typeuser);
        editor.apply();

    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if (!this.isLoggin()){
            Intent i = new Intent(context, SplashActivity.class);
            context.startActivity(i);
            ((InicioActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(USER_NAME, sharedPreferences.getString(USER_NAME, null));
        user.put(ID, sharedPreferences.getString(ID, null));
        user.put(TYPE_USER, sharedPreferences.getString(TYPE_USER, null));
        return user;
    }

    public void logout(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, SplashActivity.class);
        context.startActivity(i);
        ((InicioActivityTutor) context).finish();
        FirebaseAuth.getInstance().signOut();
        FirebaseMessaging.getInstance().unsubscribeFromTopic("tutores")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(context, "se ha cerrado la sesion", Toast.LENGTH_SHORT).show();

                    }
                });

    }


    public void logoutEstudiante(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, SplashActivity.class);
        context.startActivity(i);
        ((InicioActivity) context).finish();
        FirebaseAuth.getInstance().signOut();
        FirebaseMessaging.getInstance().unsubscribeFromTopic("estudiantes")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(context, "se ha cerrado la sesion", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

}

