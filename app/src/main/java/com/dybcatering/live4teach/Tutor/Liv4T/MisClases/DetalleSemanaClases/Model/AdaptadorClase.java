package com.dybcatering.live4teach.Tutor.Liv4T.MisClases.DetalleSemanaClases.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.Model.MisClasesItem;

import java.util.ArrayList;

public class AdaptadorClase extends RecyclerView.Adapter<AdaptadorClase.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<ClaseItem> mExampleList;
    private OnItemClickListener mListener;


    public AdaptadorClase(Context context, ArrayList<ClaseItem> exampleList) {
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
        ClaseItem currentItem = mExampleList.get(position);


        String strNombre = currentItem.getNombre();
        String strDescription = currentItem.getDescripcion();
        String strNombre_Documento = currentItem.getNombre_Documento();
        String strDocumento = currentItem.getDocumento();
        String strUrl = currentItem.getUrl();
        String strVideo = currentItem.getVideo();
        String strId_Weekly = currentItem.getId_Weekly();





        holder.semana.setText("Semana "+ strNombre);





    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView semana;

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
