package com.dybcatering.live4teach.Estudiante.MisCalificaciones.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.ArrayList;

public class AdaptorMisCalificaciones extends RecyclerView.Adapter<AdaptorMisCalificaciones.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<ItemMisCalificaciones> mExampleList;
    private OnItemClickListener mListener;


    public AdaptorMisCalificaciones(Context context, ArrayList<ItemMisCalificaciones> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.calificaciones_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ItemMisCalificaciones currentItem = mExampleList.get(position);

        String nombrecurso= currentItem.getNombreCurso();
        String actividad = currentItem.getActividad();
        String usuario = currentItem.getUserName();
        String tipoactividad = currentItem.getTipoactividad();
        String lastdate = currentItem.getLastdate();
        String date = currentItem.getDate();
        String subtotal = currentItem.getSubTotal();
        String total = currentItem.getTotal();
        String observacion = currentItem.getObservacion();
        String actualizadodate = currentItem.getActualizadoDate();


        holder.nombrecurso.setText(nombrecurso);
        holder.actividad.setText(actividad);
        holder.tipoactividad.setText(tipoactividad);
        holder.fecha.setText("Fecha de calificación: " + lastdate);
        holder.nota.setText("Nota final: " + total);
    //    Picasso.with(mContext).load(imagen).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {


        public TextView  nombrecurso, actividad,tipoactividad, nota, fecha;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            //mImageView = itemView.findViewById(R.id.imagen_curso);
            nombrecurso= itemView.findViewById(R.id.txtNombreCursoCalificaciones);
            actividad= itemView.findViewById(R.id.txtActividadCursoCalificaciones);
            tipoactividad= itemView.findViewById(R.id.txtTipoActividad);
            nota = itemView.findViewById(R.id.txtNotaFinal);
            fecha= itemView.findViewById(R.id.txtFechaCalificaciones);


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
