package com.dybcatering.live4teach.Splash.Estudiante.Perfil;

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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class ActualizarDatosFragment extends Fragment {
    private static final String TAG = ActualizarDatosFragment.class.getSimpleName(); //getting the info
    private static String URL_READ = "https://dybcatering.com/back_live_app/read_detail.php";
    private static String URL_EDIT = "https://dybcatering.com/back_live_app/edit_detail.php";
    private static String URL_UPLOAD = "https://dybcatering.com/back_live_app/upload.php";
    EditText edtNombre, edtApellido, edtTelefono, edtCorreo;
    TextView txtUsuario, btnSubirFoto;
    CircleImageView imvprofile;
    Button update;
    private Bitmap bitmap;
    SessionManager sessionManager;

    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_actualizar_datos, container, false);
        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);
        edtNombre = view.findViewById(R.id.nombreActualizarDatos);
        edtApellido = view.findViewById(R.id.apellidoActualizarDatos);
        edtTelefono = view.findViewById(R.id.telefonoActualizarDatos);
        edtCorreo= view.findViewById(R.id.emailActualizarDatos);
        txtUsuario = view.findViewById(R.id.txtUsuarioActualizarDatos);
        btnSubirFoto = view.findViewById(R.id.subir_fotoActualizarDatos);
        imvprofile = view.findViewById(R.id.profilepic);
        update = view.findViewById(R.id.update);
        final String nombre = edtNombre.getText().toString();
        final String apellido = edtApellido.getText().toString();
        final String telefono = edtApellido.getText().toString();
        final String email = edtCorreo.getText().toString();
        final String usuario = txtUsuario.getText().toString();

        getUserDetail();
        btnSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirFoto();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEditDetail();
            }
        });

        return view;
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
                imvprofile.setImageBitmap(bitmap);

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

    private void SaveEditDetail() {

        final String nombre = edtNombre.getText().toString();
        final String apellido = edtApellido.getText().toString();
        final String telefono = edtTelefono.getText().toString();
        final String email = edtCorreo.getText().toString();
        final String usuario = txtUsuario.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Guardando...");
        progressDialog.show();

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
                params.put("user_name", usuario);
                params.put("email", email);
                params.put("phone", telefono);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    private void getUserDetail() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
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
                                    String strApellido = object.getString("last_name").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strUsuario = object.getString("user_name").trim();
                                    String strPicture= object.getString("picture").trim();
                                    String strTelefono = object.getString("phone").trim();
                                    edtNombre.setText(strName);
                                    edtApellido.setText(strApellido);
                                    edtTelefono.setText(strTelefono);
                                    edtCorreo.setText(strEmail);
                                    txtUsuario.setText(strUsuario);
                                    if (strPicture.equals("")) {
                                        imvprofile.setImageResource(R.drawable.imagenperfil);
                                    } else {
                                        Picasso.with(getActivity()).load(strPicture).centerCrop()
                                                .placeholder(R.drawable.internetconnection).fit().into(imvprofile, new Callback() {
                                            @Override public    void onSuccess() {}
                                            @Override public void onError() {}
                                        });
                                        //Picasso.with(getActivity()).load(strPicture).into(imvprofile);
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
