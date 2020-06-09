package com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.PlanificacionGeneral.Trimestral.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dybcatering.live4teach.R;

import java.util.ArrayList;

public class AdaptadorPlanificacionTrimestral extends RecyclerView.Adapter<AdaptadorPlanificacionTrimestral.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<PlanificacionTrimestralItem> mExampleList;
    private OnItemClickListener mListener;


    public AdaptadorPlanificacionTrimestral(Context context, ArrayList<PlanificacionTrimestralItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_planificaciontrimestral, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        PlanificacionTrimestralItem currentItem = mExampleList.get(position);


        String unidad= currentItem.getUnit_name();
        String contenido= currentItem.getContent();


        holder.unidad.setText(unidad);
        holder.contenido.setText(contenido);





    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public EditText unidad, contenido;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            unidad= itemView.findViewById(R.id.edtUnidad);
            contenido= itemView.findViewById(R.id.edtContenido);
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
