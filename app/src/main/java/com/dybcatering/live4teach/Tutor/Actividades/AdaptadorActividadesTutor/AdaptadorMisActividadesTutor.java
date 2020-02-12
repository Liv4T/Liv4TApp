package com.dybcatering.live4teach.Tutor.Actividades.AdaptadorActividadesTutor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.ArrayList;

public class AdaptadorMisActividadesTutor extends RecyclerView.Adapter<AdaptadorMisActividadesTutor.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<ItemAdaptadorActividadesTutor> mExampleList;
    private OnItemClickListener mListener;


    public AdaptadorMisActividadesTutor(Context context, ArrayList<ItemAdaptadorActividadesTutor> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_misactividadestutor, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ItemAdaptadorActividadesTutor currentItem = mExampleList.get(position);

        String id = currentItem.getId();
        String nombrecurso = currentItem.getNombreCurso();
        String name = currentItem.getName();
        String id_user = currentItem.getIdUsuario();
        String activitytype = currentItem.getActivityType();
        String estimated_duration_platform = currentItem.getEstimatedDurationPlatform();
        String estimated_duration_autonomous_work = currentItem.getEstimatedDurationAutonomousWork();
        String theme_contextualization = currentItem.getTheme_contextualization();
        String activity = currentItem.getActivity();
        String type_resources_1 = currentItem.getType_resources_1();
        String type_resources_2 = currentItem.getType_resources_2();
        String type_resources_3 = currentItem.getType_resources_3();
        String origin_resource1= currentItem.getOrigin_resourses_1();
        String origin_resource2= currentItem.getOrigin_resourses_2();
        String origin_resource3= currentItem.getOrigin_resourses_3();
        String deliverables= currentItem.getDeliverables();
        String evaluation_criteria1 = currentItem.getEvaluation_criteria1();
        String evaluation_criteria2 = currentItem.getEvaluation_criteria2();
        String evaluation_criteria3 = currentItem.getEvaluation_criteria3();
        String work_time= currentItem.getWork_time();
        String moment_evaluation_from= currentItem.getMoment_evaluation_from();
        String moment_evaluation_up= currentItem.getMoment_evaluation_up();
        String evidence_send= currentItem.getEvidence_send();
        String intervening_actor= currentItem.getIntervening_actor();
        String feedback_date= currentItem.getFeedback_date();
        String name_category= currentItem.getName_category();


        holder.txtNombreCurso.setText(nombrecurso);
        holder.txtCategoria.setText(name_category);
        holder.txtTipoActividad.setText(activitytype);
        holder.txtAgregadoHace.setText("Agregado hace: " + feedback_date);


       // holder.mTextNombreCurso.setText(name);
       // holder.mEstadoCurso.setText(publish);
       // holder.mTemaUnidad.setText(descriptionO);
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


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView  txtNombreCurso, txtCategoria, txtTipoActividad, txtFechaEvaluacion, txtAgregadoHace;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            txtNombreCurso= itemView.findViewById(R.id.txtNombreCurso);
            txtCategoria= itemView.findViewById(R.id.txtCategoria);
            txtTipoActividad = itemView.findViewById(R.id.txtTipoActividad);
            txtFechaEvaluacion = itemView.findViewById(R.id.txtFechaEvaluacion);
            txtAgregadoHace = itemView.findViewById(R.id.txtAgregadoHace);
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
