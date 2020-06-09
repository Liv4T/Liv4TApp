package com.dybcatering.live4teach.Tutor.Liv4T.MisClases.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.PlanificacionGeneral.Trimestral.Model.PlanificacionTrimestralItem;

import java.util.ArrayList;

public class AdaptadorMisClases extends RecyclerView.Adapter<AdaptadorMisClases.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<MisClasesItem> mExampleList;
    private OnItemClickListener mListener;


    public AdaptadorMisClases(Context context, ArrayList<MisClasesItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_clases, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        MisClasesItem currentItem = mExampleList.get(position);


        String strsemana= currentItem.getSemana();
        String strId = currentItem.getId();



        holder.semana.setText("Semana "+ strsemana);





    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public Button semana;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            semana= itemView.findViewById(R.id.btnSemana);
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
