package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante.MisConsultas.ConsultasOffline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles.ItemOfflineConsultasDisponibles;

import java.util.ArrayList;

public class AdaptadorMisConsultasOffline extends RecyclerView.Adapter<AdaptadorMisConsultasOffline.ViewHolder>  {

	private ArrayList<ItemMisConsultasOffline> MlistData;
	private Context mContext;
	private OnItemClickListener mListener;




	public AdaptadorMisConsultasOffline(Context context, ArrayList<ItemMisConsultasOffline> itemConsultasOffline) {
		mContext = context;
		MlistData = itemConsultasOffline;
	}



	public void setOnClickItemListener(OnItemClickListener listener) {
		mListener = listener;

	}
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view= LayoutInflater.from(mContext).inflate(R.layout.item_consultas_disponibles,parent,false);
			return new ViewHolder(view);
	}



	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			ItemMisConsultasOffline currentItem=MlistData.get(position);

			String categoria = currentItem.getCategoria();
			String estado = currentItem.getEstado();
			String mensaje = currentItem.getMensaje();
			String remitente = currentItem.getRemitente();
			String hora = currentItem.getHora();


			holder.txtCategoria.setText("Categoria: "+categoria);
			holder.txtEstado.setText("Estado: "+estado);
			holder.txtMensaje.setText("Consulta: " +mensaje);
			holder.txtRemitente.setText("Usuario: "+remitente);
			}

	@Override
	public int getItemCount() {
			return MlistData.size();
			}

	public interface OnItemClickListener {
		void onItemClick(int position);
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		private TextView txtCategoria,txtEstado,txtMensaje, txtRemitente;
		public ViewHolder(View itemView) {
			super(itemView);
			txtCategoria=itemView.findViewById(R.id.txtCategoria);
			txtEstado=itemView.findViewById(R.id.txtEstado);
			txtMensaje=itemView.findViewById(R.id.txtMensaje);
			txtRemitente = itemView.findViewById(R.id.txtRemitente);
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
