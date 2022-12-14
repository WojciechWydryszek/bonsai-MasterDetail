package com.teknos.m8uf2.wwydryszek.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.screen.EditItem;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;

import java.util.ArrayList;

public class AdapterBonsai extends RecyclerView.Adapter<AdapterBonsai.ViewHolder> {

    private ArrayList<Bonsai> bonsaiArrayList;
    private Context activityParent;

    public AdapterBonsai(ArrayList<Bonsai> bonsaiArrayList) { this.bonsaiArrayList = bonsaiArrayList; }

    public AdapterBonsai(ArrayList<Bonsai> bonsais, Context activity) {
        this.bonsaiArrayList = bonsais;
        this.activityParent = activity;
    }

    //View holder class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvName, tvAge, tvAlive;
        private ImageView imageBonsai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAlive = itemView.findViewById(R.id.tvAlive);
            imageBonsai = itemView.findViewById(R.id.imageBonsai);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activityParent, EditItem.class);

                    Singletone.getInstance().setEdit(true);

                    Singletone.getInstance().setPosition(getAdapterPosition());

                    activityParent.startActivity(intent);
                }
            });
        }

        public TextView getTvName() { return tvName; }

        public TextView getTvAge() { return tvAge; }

        public TextView getCbAlive() { return tvAlive; }

        public ImageView getImageBonsai() { return imageBonsai; }

        @Override
        public void onClick(View view) {  }
    }

    @NonNull
    @Override
    public AdapterBonsai.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBonsai.ViewHolder item, @SuppressLint("RecyclerView") int position) {

        Bonsai bonsai = this.bonsaiArrayList.get(position);

        item.getTvName().setText(bonsai.getName());
        item.getTvAge().setText(bonsai.getAge() + " Anys");
        item.getImageBonsai().setImageBitmap(bonsai.getImage());
        if(bonsai.isAlive())
            item.getCbAlive().setText("Esta viu");
        else
            item.getCbAlive().setText("Esta mort");

        item.getTvName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activityParent, EditItem.class);

                Singletone.getInstance().setEdit(true);

                Singletone.getInstance().setPosition(position);

                activityParent.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return this.bonsaiArrayList.size(); }

}
