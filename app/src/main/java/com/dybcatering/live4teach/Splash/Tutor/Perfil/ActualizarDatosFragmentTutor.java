package com.dybcatering.live4teach.Splash.Tutor.Perfil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class ActualizarDatosFragmentTutor extends Fragment {

 //   public String nombre;

    public EditText etnombre, etapellido, etdireccion, ettelefono, etidentificacion, etcorreoelectronico;
    public TextView tvusuario, btnsubirfoto;

    SessionManager sessionManager;

    String id;
    Button btnupdate;

    public ImageView profilepic;
    private Bitmap bitmap;
    private static String URL_READ = "https://dybcatering.com/back_live_app/read_detail.php";
    private static String URL_EDIT = "https://dybcatering.com/back_live_app/edit_detail_tutor.php";
    private static String URL_UPLOAD = "https://dybcatering.com/back_live_app/upload.php";
    private static final String TAG = ActualizarDatosFragmentTutor.class.getSimpleName(); //getting the info
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_actualizar_datos_tutor, container, false);
        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);
        etnombre= view.findViewById(R.id.nombre_update);
        etapellido= view.findViewById(R.id.apellido_update);
        etdireccion= view.findViewById(R.id.address_update);
        ettelefono = view.findViewById(R.id.telefono_update);
        etidentificacion= view.findViewById(R.id.identificacion_update);
        etcorreoelectronico= view.findViewById(R.id.correo_update);
        tvusuario= view.findViewById(R.id.txtUsuarioActualizarDatos);
        profilepic = view.findViewById(R.id.profilepic);
        btnsubirfoto= view.findViewById(R.id.subirfoto);
        btnupdate =view.findViewById(R.id.btnupdate);
        btnsubirfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirFoto();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDatos();
            }
        });
        getUserDetail();

        return view;
    }

    private void actualizarDatos() {
        final String nombre = etnombre.getText().toString();
        final String apellido = etapellido.getText().toString();
        final String direccion= etdireccion.getText().toString();
        final String telefono = ettelefono.getText().toString();
        final String identificacion= etidentificacion.getText().toString();
        final String email = etcorreoelectronico.getText().toString();
        final String usuario = tvusuario.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Guardando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toasty.success(getContext(), "Exito al Actualizar los datos", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(nombre, usuario, id, "3");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", nombre);
                params.put("last_name", apellido);
                params.put("address", direccion);
                params.put("phone", telefono);
                params.put("id_number", identificacion);
                params.put("email", email);
                params.put("user_name", usuario);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    private void subirFoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona tu imagen de preferencia"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                profilepic.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            UploadPicture(id, getStringImage(bitmap));

        }
    }
    private void UploadPicture(final String id, final String picture) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Subiendo...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i(TAG, response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {

                                Toast.makeText(getActivity(), "Exito!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Intenta de nuevo por favor!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Try Again!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("picture", picture);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    public String getStringImage(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }

    private void getUserDetail() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();
                                    String strLastName = object.getString("last_name").trim();
                                    String strAdress= object.getString("address").trim();
                                    String strTelefono = object.getString("phone").trim();
                                    String strIdentificacion= object.getString("id_number").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strUser= object.getString("user_name").trim();
                                    String strPicture= object.getString("picture").trim();
                                    etnombre.setText(strName);
                                    etapellido.setText(strLastName);
                                    etdireccion.setText(strAdress);
                                    ettelefono.setText(strTelefono);
                                    etidentificacion.setText(strIdentificacion);
                                    etcorreoelectronico.setText(strEmail);
                                    tvusuario.setText(strUser);
                                    if (strPicture.equals("")) {
                                        profilepic.setImageResource(R.drawable.imagenperfil);
                                    } else {
                                        Picasso.with(getActivity()).load(strPicture).into(profilepic);
                                    }


                                }


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error de conexión ", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error de conexión  ", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

}
