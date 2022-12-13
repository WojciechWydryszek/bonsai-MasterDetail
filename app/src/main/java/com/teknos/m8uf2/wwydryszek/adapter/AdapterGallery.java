package com.teknos.m8uf2.wwydryszek.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;
import java.util.ArrayList;

public class AdapterGallery extends RecyclerView.Adapter<AdapterGallery.ViewHolder> {

    private ArrayList<Bonsai> bonsaiArrayList;
    private Context activityParent;

    public AdapterGallery(ArrayList<Bonsai> bonsaiArrayList) { this.bonsaiArrayList = bonsaiArrayList; }

    public AdapterGallery(ArrayList<Bonsai> bonsais, Context activity) {
        this.bonsaiArrayList = bonsais;
        this.activityParent = activity;
    }

    //View holder class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageButton);

            Button button = itemView.findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Singletone.getInstance().setPosition(getAdapterPosition());

                    AlertDialog.Builder builder = new AlertDialog.Builder(activityParent);
                    builder.setCancelable(true);
                    builder.setTitle("Eliminar imatge: " + (Singletone.getInstance().getPosition() + 1));
                    builder.setMessage("Segür que vols eliminar la imatge?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) { // confirm action

                                    Bonsai bonsai = bonsaiArrayList.get(Singletone.getInstance().getPosition());
                                    bonsai.setImage(null);

                                } });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // cancel action
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { } });
        }

        public ImageView getImage() { return image; }

        @Override
        public void onClick(View view) {  }
    }

    @NonNull
    @Override
    public AdapterGallery.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gallery, parent, false);

        return new AdapterGallery.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGallery.ViewHolder item, @SuppressLint("galleryRecyclerView") int position) {

        Bonsai bonsai = this.bonsaiArrayList.get(position);

        item.getImage().setImageBitmap(bonsai.getImage());

        item.getImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { } });
    }

    @Override
    public int getItemCount() { return this.bonsaiArrayList.size(); }

}

