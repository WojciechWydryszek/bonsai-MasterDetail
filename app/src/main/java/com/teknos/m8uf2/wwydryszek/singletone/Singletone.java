package com.teknos.m8uf2.wwydryszek.singletone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;

import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.screen.AlarmScreen;
import com.teknos.m8uf2.wwydryszek.screen.GaleryScreen;
import com.teknos.m8uf2.wwydryszek.screen.ListScreen;

import java.util.ArrayList;

public class Singletone {

    private Bonsai bonsai;
    private ArrayList<Bonsai> bonsaiList = new ArrayList<>();
    private boolean edit = false,
                    photoEdit = false;
    private int position;

    public void goTo(Context parentActivity, View view) {
        Button button = (Button) view;

        Intent intent = new Intent();

        if(button.getText().equals("Llista"))
            intent = new Intent(parentActivity, ListScreen.class);
        else if (button.getText().equals("Alarmes"))
            intent = new Intent(parentActivity, AlarmScreen.class);
        else if(button.getText().equals("Galeria"))
            intent = new Intent(parentActivity, GaleryScreen.class);

        parentActivity.startActivity(intent);
    }

    private static class SingletoneInstance {
        private static Singletone INSTANCE = new Singletone();
    }

    public static Singletone getInstance() { return SingletoneInstance.INSTANCE; }

    public Bonsai createBonsai() {
        bonsai = new Bonsai();
        return getBonsai();
    }

    public boolean isPhotoEdit() { return photoEdit; }

    public void setPhotoEdit(boolean photoEdit) { this.photoEdit = photoEdit; }

    public Bonsai getBonsai() { return bonsai; }

    public ArrayList<Bonsai> getBonsaiList() { return bonsaiList; }

    public void addBonsai(Bonsai bonsai) { bonsaiList.add(bonsai); }

    public boolean getEdit() { return edit; }

    public void setEdit(boolean edit) { this.edit = edit; }

    public int getPosition() { return position; }

    public void setPosition(int position) { this.position = position; }
}