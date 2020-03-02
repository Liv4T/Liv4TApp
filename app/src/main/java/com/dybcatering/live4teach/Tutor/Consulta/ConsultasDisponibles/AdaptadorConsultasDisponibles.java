package com.dybcatering.live4teach.Tutor.Consulta.ConsultasDisponibles;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.List;

public class AdaptadorConsultasDisponibles extends RecyclerView.Adapter<AdaptadorConsultasDisponibles.ViewHolder>{
private List<ItemConsultasDisponibles> listData;

public AdaptadorConsultasDisponibles(List<ItemConsultasDisponibles> listData) {
		this.listData = listData;
		}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consultas_disponibles,parent,false);
			return new ViewHolder(view);
			}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			ItemConsultasDisponibles ld=listData.get(position);

			String categoria = ld.getCategoria();
			String estado = ld.getEstado();
			String mensaje = ld.getMensaje();
			String remitente = ld.getRemitente();


			holder.txtCategoria.setText(categoria);
			holder.txtEstado.setText(estado);
			holder.txtMensaje.setText(mensaje);
			holder.txtRemitente.setText(remitente);
			}

	@Override
	public int getItemCount() {
			return listData.size();
			}

	public class ViewHolder extends RecyclerView.ViewHolder{
		private TextView txtCategoria,txtEstado,txtMensaje, txtRemitente;
		public ViewHolder(View itemView) {
			super(itemView);
			txtCategoria=itemView.findViewById(R.id.txtCategoria);
			txtEstado=itemView.findViewById(R.id.txtEstado);
			txtMensaje=itemView.findViewById(R.id.txtMensaje);
			txtRemitente = itemView.findViewById(R.id.txtRemitente);
		}
	}


}
