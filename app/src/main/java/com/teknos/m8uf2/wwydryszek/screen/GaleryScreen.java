package com.teknos.m8uf2.wwydryszek.screen;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.adapter.AdapterGallery;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;
import java.util.ArrayList;

public class GaleryScreen extends AppCompatActivity {

    private ArrayList<Bonsai> bonsaiArrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galery_screen);

        this.bonsaiArrayList = Singletone.getInstance().getBonsaiList();

        if(bonsaiArrayList.size() > 0) {

            this.recyclerView = findViewById(R.id.galleryRecycleView);

            GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
            this.recyclerView.setLayoutManager(linearLayoutManager);

            AdapterGallery adapterGallery = new AdapterGallery(Singletone.getInstance().getBonsaiList(), this);
            this.recyclerView.setAdapter(adapterGallery);
        }

    }


    // Function for change screem using toolBar
    public void toolBarClick(View view) {
        Singletone.getInstance().goTo(this, view);
    }
}
