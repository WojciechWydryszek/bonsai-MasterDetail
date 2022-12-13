package com.teknos.m8uf2.wwydryszek.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.adapter.AdapterBonsai;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.screen.screenHelpers.EditItem;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;

import java.util.ArrayList;

public class ListScreen extends AppCompatActivity {

    private ArrayList<Bonsai> bonsaiArrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_screen);

        this.bonsaiArrayList = Singletone.getInstance().getBonsaiList();

        if(bonsaiArrayList.size() > 0) {
            this.recyclerView = findViewById(R.id.recyclerView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            this.recyclerView.setLayoutManager(linearLayoutManager);

            AdapterBonsai adapterBonsai = new AdapterBonsai(Singletone.getInstance().getBonsaiList(), this);
            this.recyclerView.setAdapter(adapterBonsai);
        }
    }

    public void addElement(View view) {

        Singletone.getInstance().setEdit(false);

        Intent intent = new Intent(ListScreen.this, EditItem.class);

        startActivity(intent);
    }

    // Function for change screem using toolBar
    public void toolBarClick(View view) {

        Singletone.getInstance().goTo(this, view);
    }
}