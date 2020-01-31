package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.AdapterUnidadesSinComprar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.ArrayList;

public class UnidadesAdaptor extends RecyclerView.Adapter<UnidadesAdaptor.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<UnidadesItem> mExampleList;
    private OnItemClickListener mListener;


    public UnidadesAdaptor(Context context, ArrayList<UnidadesItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.unidades_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        UnidadesItem currentItem = mExampleList.get(position);

        String nombre = currentItem.getName();
       /* String habilidad = currentItem.getHability();
        String presentacion = currentItem.getPresentation();
        String competencia1 = currentItem.getCompetences_e1();
        String competencia2 = currentItem.getCompetences_e2();
        String competencia3 = currentItem.getCompetences_e3();
        String resultado1 = currentItem.getResult1();
        String resultado2 = currentItem.getResult2();
        String resultado3 = currentItem.getResult3();
        String unidad = currentItem.getUnit();
        String resultado4 = currentItem.getResult4();
        String comper11 = currentItem.getComper11();
        String comper12 = currentItem.getComper12();
        String comper13 = currentItem.getComper13();
        String comper21 = currentItem.getComper21();
        String comper22 = currentItem.getComper22();
        String comper23 = currentItem.getComper23();
        String comper31 = currentItem.getComper31();
        String comper32 = currentItem.getComper32();
        String comper33 = currentItem.getComper33();
        String comper41 = currentItem.getComper33();
        String comper42= currentItem.getComper33();
        String comper43 = currentItem.getComper33();
        String question= currentItem.getQuestion();
        String ready= currentItem.getReady();
        String nameV= currentItem.getNameV();
        String video_apoyo =currentItem.getVideo_apoyo();
        String projecting = currentItem.getProjecting();
        String tema = currentItem.getTopic();
        String challenge = currentItem.getChallenge();
        String doing = currentItem.getDoing();
        String bibliography = currentItem.getBibliography();
        String content = currentItem.getContent();
        String id_course = currentItem.getId_course();*/
        String topic = currentItem.getTopic();


//        String resultadoaprendizaje= currentItem.getResult4();
  //      String competenciasevaluacion= currentItem.getResult4();



        holder.mNombreUnidad.setText(nombre);
        holder.mNombreTema.setText(topic);
       // holder.mImageDestacado.setText(destacado);
        //holder.mBarrio.setText("B.° : " + barrio);
        //holder.mVistas.setText("Veces Visto : " + cantidad_vistas);
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

        public TextView  mNombreUnidad, mNombreTema;

        public ExampleViewHolder(View itemView) {
            super(itemView);
           // mImageView = itemView.findViewById(R.id.imagen_curso);
            mNombreUnidad= itemView.findViewById(R.id.nombre_unidad);
            mNombreTema= itemView.findViewById(R.id.tema_unidad);
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
