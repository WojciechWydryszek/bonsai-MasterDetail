package com.teknos.m8uf2.wwydryszek.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;

public class ConfigScreen extends AppCompatActivity {

    private RadioButton RBDark, RBLigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Singletone.getInstance().getTheme();

        setContentView(R.layout.configuration_screen);

        RBDark = findViewById(R.id.idRBDark);
        RBLigth = findViewById(R.id.idRBLight);

        if(Singletone.getInstance().getDarkTheme())
            RBDark.setChecked(true);
        else
            RBLigth.setChecked(true);
    }

    // Function for change screem using toolBar
    public void toolBarClick(View view) {

        if(RBDark.isChecked())
            Singletone.getInstance().setDarkTheme(true);
        else
            Singletone.getInstance().setDarkTheme(false);

        Singletone.getInstance().goTo(this, view);
    }
}
