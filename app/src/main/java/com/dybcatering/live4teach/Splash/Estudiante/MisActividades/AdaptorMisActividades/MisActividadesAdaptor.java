package com.dybcatering.live4teach.Splash.Estudiante.MisActividades.AdaptorMisActividades;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.Adapter.ExampleItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MisActividadesAdaptor extends RecyclerView.Adapter<MisActividadesAdaptor.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;


    public MisActividadesAdaptor(Context context, ArrayList<ExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.mis_actividades_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        String nombre = currentItem.getNombre();
        String categoria = currentItem.getCategoria();
        String subCategoria = currentItem.getSubCategoria();
        String metodologia = currentItem.getMetodologia();
        String bienvenida = currentItem.getBienvenida();
        String intensidad = currentItem.getIntensidad();
        String intensidadac = currentItem.getIntensidadAC();
        String competencias = currentItem.getCompetencias();
        String intensidadta = currentItem.getIntensidadTA();
        String logro = currentItem.getLogro();
        String indicadora = currentItem.getIndicadorA();
        String mapa = currentItem.getMapa();
        String metodologiag = currentItem.getMetodologiaG();
        String tipo = currentItem.getTipo();
        String descripcion = currentItem.getDescripcion();
        String presentacion = currentItem.getPresentacion();
        String idusuario = currentItem.getIdUsuario();
        String descripciono = currentItem.getDescripcionO();
        String actualizadoen= currentItem.getActualizadoEn();
        String creadoen = currentItem.getCreadoEn();
        String estado = currentItem.getEstado();
        String publicar = currentItem.getPublicar();
        String imagen = currentItem.getImagen();
        String precio = currentItem.getPrecio();
        String videoapoyo = currentItem.getVideoPresentacion();


        holder.mNombreCurso.setText(nombre);
        holder.mDescripcionCurso.setText(descripcion);
       // holder.mImageDestacado.setText(destacado);
        //holder.mBarrio.setText("B.° : " + barrio);
        //holder.mVistas.setText("Veces Visto : " + cantidad_vistas);
        Picasso.with(mContext).load(imagen).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView  mNombreCurso, mDescripcionCurso;
        public Button mBarrio;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagen_curso);
            mNombreCurso= itemView.findViewById(R.id.titulo);
            mDescripcionCurso= itemView.findViewById(R.id.descripcion);
            //mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            //mTextViewDescription = itemView.findViewById(R.id.text_view_description);
            //mBarrio = itemView.findViewById(R.id.text_barrio);
            //mVistas = itemView.findViewById(R.id.text_view_vistas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
