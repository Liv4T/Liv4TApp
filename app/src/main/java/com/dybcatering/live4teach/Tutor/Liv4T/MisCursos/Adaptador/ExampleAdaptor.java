package com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dybcatering.live4teach.Estudiante.MisCursos.Adapter.ExampleItem;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.Model.Curso;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdaptor extends RecyclerView.Adapter<ExampleAdaptor.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<Curso> mExampleList;
    private OnItemClickListener mListener;


    public ExampleAdaptor(Context context, ArrayList<Curso> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cursos_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Curso currentItem = mExampleList.get(position);

        String nombre = currentItem.getNombreCurso();


        holder.mNombreCurso.setText(nombre);
   //     holder.mDescripcionCurso.setText(descripcion);
       // holder.mImageDestacado.setText(destacado);
        //holder.mBarrio.setText("B.° : " + barrio);
        //holder.mVistas.setText("Veces Visto : " + cantidad_vistas);
//        Picasso.with(mContext).load(imagen).fit().centerInside().into(holder.mImageView);


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

            mNombreCurso= itemView.findViewById(R.id.titulo);

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
