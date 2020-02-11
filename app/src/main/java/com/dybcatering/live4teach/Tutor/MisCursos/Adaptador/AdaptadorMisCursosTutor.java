package com.dybcatering.live4teach.Tutor.MisCursos.Adaptador;

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
import com.dybcatering.live4teach.Tutor.MisCursos.MisCursosTutorFragment;

import java.util.ArrayList;

public class AdaptadorMisCursosTutor extends RecyclerView.Adapter<AdaptadorMisCursosTutor.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<ItemMisCursosTutor> mExampleList;
    private OnItemClickListener mListener;


    public AdaptadorMisCursosTutor(Context context, ArrayList<ItemMisCursosTutor> exampleList) {
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
        ItemMisCursosTutor currentItem = mExampleList.get(position);


        String id= currentItem.getId();
        String name = currentItem.getName();
        String id_category = currentItem.getId_category();
        String id_subcategory = currentItem.getId_subcategory();
        String methodology = currentItem.getMethodology();
        String welcome = currentItem.getWelcome();
        String intention = currentItem.getIntention();
        String intensityAC = currentItem.getIntensityAC();
        String competences = currentItem.getCompetences();
        String intensityTA = currentItem.getIntensityTA();
        String achievement = currentItem.getAchievement();
        String indicatorA = currentItem.getIndicatorA();
        String map = currentItem.getMap();
        String methodologyG = currentItem.getMehodologyG();
        String type = currentItem.getType();
        String description = currentItem.getDescription();
        String presentation =currentItem.getPresentation();
        String iduser = currentItem.getId_user();
        String descriptionO = currentItem.getDescriptionO();
        String updated_at = currentItem.getUpdated_at();
        String created_at = currentItem.getCreated_at();
        String state = currentItem.getState();
        String publish = currentItem.getPublish();
        String image = currentItem.getImage();
        String price = currentItem.getPrice();
        String video_presentation = currentItem.getVideo_presentation();
        String totalinscritos = currentItem.getTotalinscritos();


      //  holder.mNombreCurso.setText(nombre);
       // holder.mDescripcionCurso.setText(descripcion);
       // holder.mImageDestacado.setText(destacado);
        //holder.mBarrio.setText("B.° : " + barrio);
        //holder.mVistas.setText("Veces Visto : " + cantidad_vistas);
       // Picasso.with(mContext).load(imagen).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void setOnClickItemListener(MisCursosTutorFragment misCursosTutorFragment) {
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
