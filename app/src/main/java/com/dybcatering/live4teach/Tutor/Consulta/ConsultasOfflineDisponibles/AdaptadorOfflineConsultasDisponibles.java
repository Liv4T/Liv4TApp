package com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

import java.util.List;

public class AdaptadorOfflineConsultasDisponibles extends RecyclerView.Adapter<AdaptadorOfflineConsultasDisponibles.ViewHolder>{
private List<ItemOfflineConsultasDisponibles> listData;

public AdaptadorOfflineConsultasDisponibles(List<ItemOfflineConsultasDisponibles> listData) {
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
			ItemOfflineConsultasDisponibles ld=listData.get(position);

			String categoria = ld.getCategoria();
			String estado = ld.getEstado();
			String mensaje = ld.getMensaje();
			String remitente = ld.getRemitente();
			String hora = ld.getHora();


			holder.txtCategoria.setText("Categoria: "+categoria);
			holder.txtEstado.setText("Estado: "+estado);
			holder.txtMensaje.setText("Consulta: " +mensaje);
			holder.txtRemitente.setText("Usuario: "+remitente);
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
