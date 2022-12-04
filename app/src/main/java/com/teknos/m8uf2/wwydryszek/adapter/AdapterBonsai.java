package com.teknos.m8uf2.wwydryszek.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

        private final TextView tvName, tvAge;
        private final CheckBox cbAlive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            cbAlive = itemView.findViewById(R.id.cbAlive);
        }

        public TextView getTvName() { return tvName; }

        public TextView getTvAge() { return tvAge; }

        public CheckBox getCbAlive() { return cbAlive; }

        @Override
        public void onClick(View view) {}
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
        item.getTvAge().setText(bonsai.getAge());
        item.getCbAlive().setChecked(bonsai.isAlive());

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