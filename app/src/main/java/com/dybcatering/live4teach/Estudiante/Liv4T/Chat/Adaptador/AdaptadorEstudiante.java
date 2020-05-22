package com.dybcatering.live4teach.Estudiante.Liv4T.Chat.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.Estudiante.Liv4T.Chat.Model.ItemUsuario;
import com.dybcatering.live4teach.R;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorEstudiante extends RecyclerView.Adapter<AdaptadorEstudiante.UsuarioViewHolder> {
		private Context mContext;
		private ArrayList<Object> mExampleList;
	private List<ItemUsuario> items = new ArrayList<>();


	public AdaptadorEstudiante(Context context, ArrayList<Object> exampleList) {
		mContext = context;
		mExampleList = exampleList;
	}


	@NonNull
	@Override
	public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(mContext).inflate(R.layout.item_usuarios, parent, false);
		return new UsuarioViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
		ItemUsuario mismoitem = (ItemUsuario) mExampleList.get(position);

		String nombregrupo =mismoitem.getNombre();

		holder.mNombreUsuario.setText(nombregrupo);
	}

	@Override
	public int getItemCount() {
		return mExampleList.size();
	}


	void loadItems(List<ItemUsuario> tournaments) {
		this.items = tournaments;
		notifyDataSetChanged();
	}

	public class UsuarioViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener{


		public AppCompatCheckedTextView mNombreUsuario;


		public UsuarioViewHolder(View itemView) {
			super(itemView);
			mNombreUsuario= itemView.findViewById(R.id.checked_text_view);

			//mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
			//mTextViewDescription = itemView.findViewById(R.id.text_view_description);
			//mBarrio = itemView.findViewById(R.id.text_barrio);
			//mVistas = itemView.findViewById(R.id.text_view_vistas);


		}

		@Override
		public void onClick(View v) {
			int adapterPosition = getAdapterPosition();
			if (!items.get(adapterPosition).getChecked()) {
				mNombreUsuario.setChecked(true);
				items.get(adapterPosition).setChecked(true);
			}
			else  {
				mNombreUsuario.setChecked(false);
				items.get(adapterPosition).setChecked(false);
			}
		}
	}



}
