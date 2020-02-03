package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.ArrayList;

public class MisCursosAdaptor extends RecyclerView.Adapter<MisCursosAdaptor.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<MisCursosItem> mExampleList;
    private OnItemClickListener mListener;


    public MisCursosAdaptor(Context context, ArrayList<MisCursosItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_miscursos, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        MisCursosItem currentItem = mExampleList.get(position);

        String id = currentItem.getId();
        String nombre = currentItem.getName();
        String methodology = currentItem.getMethodology();
        String welcome = currentItem.getWelcome();
        String intention = currentItem.getIntention();
        String intensityAC = currentItem.getIntensityAC();
        String competences = currentItem.getCompetences();
        String intensityTA = currentItem.getIntensityTA();
        String achievement = currentItem.getAchievement();
        String indicatorA = currentItem.getIndicatorA();
        String methodologyG = currentItem.getMethodologyG();
        String type = currentItem.getType();
        String description = currentItem.getDescription();
        String descriptionO = currentItem.getDescriptionO();
        String updated_at = currentItem.getUpdated_at();
        String state = currentItem.getState();
        String image = currentItem.getImage();
        String video_presentacion = currentItem.getVideo_presentacion();
        String topic = currentItem.getTopic();
        String publish = currentItem.getPublish();
        String idtemas = currentItem.getIdtemas();



        holder.mTextNombreCurso.setText(nombre);
        holder.mEstadoCurso.setText(publish);
        holder.mTemaUnidad.setText(descriptionO);
//        holder.mActividad.setText(topic);
       // holder.mImageDestacado.setText(destacado);
        //holder.mBarrio.setText("B.° : " + barrio);
        //holder.mVistas.setText("Veces Visto : " + cantidad_vistas);
    //    Picasso.with(mContext).load(imagen).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

   // public void setOnClickItemListener(MisCursosFragment misCursosFragment) {
    //}/

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView  mTextNombreCurso, mEstadoCurso, mTemaUnidad, mActividad;

        public ExampleViewHolder(View itemView) {
            super(itemView);
           // mImageView = itemView.findViewById(R.id.imagen_curso);
            mTextNombreCurso= itemView.findViewById(R.id.txtnombre_curso);
            mEstadoCurso= itemView.findViewById(R.id.estado_curso);
            mTemaUnidad = itemView.findViewById(R.id.tema_unidad);
            mActividad = itemView.findViewById(R.id.actividad);
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
