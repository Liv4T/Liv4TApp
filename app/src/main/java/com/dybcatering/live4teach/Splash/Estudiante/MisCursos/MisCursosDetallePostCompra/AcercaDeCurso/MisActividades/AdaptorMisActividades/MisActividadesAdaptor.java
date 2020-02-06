package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades.AdaptorMisActividades;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.ArrayList;

public class MisActividadesAdaptor extends RecyclerView.Adapter<MisActividadesAdaptor.ExampleViewHolder> {


    private Context mContext;
    private ArrayList<ItemMisActividades> mExampleList;
    private OnItemClickListener mListener;


    public MisActividadesAdaptor(Context context, ArrayList<ItemMisActividades> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.mis_actividades_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ItemMisActividades currentItem = mExampleList.get(position);
        String id = currentItem.getId();
        String name = currentItem.getName();
        String nombrecurso = currentItem.getNombrecurso();
        String id_user = currentItem.getId_user();
        String activitytype = currentItem.getActivitytype();
        String estimated_duration_platform = currentItem.getDuracionestimadaplataforma();
        String estimated_duration_autonomous_work = currentItem.getDuracionestimadatrabajoautonomo();
        String theme_contextualization = currentItem.getContextualizaciondeltema();
        String activity = currentItem.getActividad();
        String type_resources_1 = currentItem.getTiporecursos1();
        String type_resources_2 = currentItem.getTiporecursos2();
        String type_resources_3 = currentItem.getTiporecursos3();
        String origin_resources1 = currentItem.getOrigenrecurso1();
        String origin_resources2 = currentItem.getOrigenrecurso2();
        String origin_resources3 = currentItem.getOrigenrecurso3();
        String deliverables = currentItem.getEntregables();
        String evaluation_criteria1 = currentItem.getCriteriosevaluacion1();
        String evaluation_criteria2 = currentItem.getCriteriosevaluacion2();
        String evaluation_criteria3 = currentItem.getCriteriosevaluacion3();
        String work_time = currentItem.getTiempotrabajo();
        String moment_evaluation_from = currentItem.getMomentoevaluaciondesde();
        String moment_evaluation_up = currentItem.getMomentoevaluacionhasta();
        String evidence_send = currentItem.getEvidenciaenvio();
        String intervening_actor = currentItem.getActorinterviniente();
        String feedback_date = currentItem.getFecharetroalimentacion();

        holder.mtxtMisActividades.setText(name);
        holder.mtxtTipoActividad.setText(activitytype);
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

        public TextView  mtxtMisActividades, mtxtTipoActividad;
        public Button mBarrio;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            mtxtMisActividades= itemView.findViewById(R.id.txtMisActividades);
            mtxtTipoActividad= itemView.findViewById(R.id.txtCategorias);
//            mtxtFechaEvalaucion = itemView.findViewById(R.id.txtFechaEvalaucion);

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
