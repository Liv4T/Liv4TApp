package com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.Semana.CrearSemana.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.ArrayList;

public class AdaptadorSemanas extends RecyclerView.Adapter<AdaptadorSemanas.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<SemanaItem> mExampleList;
    private OnItemClickListener mListener;


    public AdaptadorSemanas(Context context, ArrayList<SemanaItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_plansemanal, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        SemanaItem currentItem = mExampleList.get(position);


        String preguntaconductora= currentItem.getDriving_question();
        String classdevelopment= currentItem.getClass_development();
        String observation = currentItem.getObservation();
        String week = currentItem.getWeeek();



        holder.preguntaconductora.setText("Pregunta Conductora Semana "+week);
        holder.edtPreguntaConductora.setText(preguntaconductora);
   //     holder.edtDesarrolloClase.setText(classdevelopment);
    //    holder.edtObservacion.setText(observation);



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public EditText edtPreguntaConductora, edtDesarrolloClase, edtObservacion;

        public TextView preguntaconductora;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            edtPreguntaConductora= itemView.findViewById(R.id.edtPreguntaConductora);
          //  edtDesarrolloClase= itemView.findViewById(R.id.edtDesarrolloClase);
         //   edtObservacion= itemView.findViewById(R.id.edtObservacion);
            preguntaconductora = itemView.findViewById(R.id.textPreguntaConductora);
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
