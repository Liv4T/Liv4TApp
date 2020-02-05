package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.AdapterDetalleVideosMisCursos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.ArrayList;

public class VideosAdaptor extends RecyclerView.Adapter<VideosAdaptor.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<VideosItem> mExampleList;
    private OnItemClickListener mListener;


    public VideosAdaptor(Context context, ArrayList<VideosItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.videos_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        VideosItem currentItem = mExampleList.get(position);

        String id = currentItem.getId();
        String id_course = currentItem.getId_course();
        String id_courseunit = currentItem.getId_courseunit();
        String video = currentItem.getVideo();
        String duracionvideo = currentItem.getDuracion();
        String nombrevideo = currentItem.getNombre_video();


//        String resultadoaprendizaje= currentItem.getResult4();
  //      String competenciasevaluacion= currentItem.getResult4();



//        holder.mId.setText(id);
        holder.mNombreVideo.setText(nombrevideo);
        holder.mDuracion.setText("Duración: "+ duracionvideo);
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

        public TextView  mNombreVideo, mId, mDuracion;

        public ExampleViewHolder(View itemView) {
            super(itemView);
           // mImageView = itemView.findViewById(R.id.imagen_curso);
            mNombreVideo= itemView.findViewById(R.id.txtNombre);
            //mId= itemView.findViewById(R.id.txtId);
            mDuracion = itemView.findViewById(R.id.txtDuracion);
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
