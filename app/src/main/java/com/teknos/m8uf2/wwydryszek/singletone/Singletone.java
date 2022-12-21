package com.teknos.m8uf2.wwydryszek.singletone;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatDelegate;

import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.screen.AlarmScreen;
import com.teknos.m8uf2.wwydryszek.screen.ConfigScreen;
import com.teknos.m8uf2.wwydryszek.screen.GaleryScreen;
import com.teknos.m8uf2.wwydryszek.screen.ListScreen;
import com.teknos.m8uf2.wwydryszek.screen.QrScreen;
import java.util.ArrayList;

public class Singletone {

    private Bonsai bonsai;
    private ArrayList<Bonsai> bonsaiList = new ArrayList<>();
    private boolean edit = false,
                    photoEdit = false,
                    darkTheme = false;
    private int position;

    public void goTo(Context parentActivity, View view) {

        ImageButton imageButton = (ImageButton) view;

        Intent intent = new Intent();

        if(imageButton.getId() == R.id.btnList)
            intent = new Intent(parentActivity, ListScreen.class);
        else if (imageButton.getId() == R.id.btnGallery)
            intent = new Intent(parentActivity, GaleryScreen.class);
        else if (imageButton.getId() == R.id.btnConfig)
            intent = new Intent(parentActivity, ConfigScreen.class);
        else if (imageButton.getId() == R.id.btnQr)
            intent = new Intent(parentActivity, QrScreen.class);

        parentActivity.startActivity(intent);
    }

    public boolean getDarkTheme() {
        if(darkTheme)
            return true;
        else
            return false;
    }

    public void setDarkTheme(Boolean darkTheme) { this.darkTheme = darkTheme; }

    public void getTheme() {
        if(Singletone.getInstance().getDarkTheme())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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