package com.teknos.m8uf2.wwydryszek.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;

public class AlarmScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_screen);

    }

    // Function for change screem using toolBar
    public void toolBarClick(View view) {
        Singletone.getInstance().goTo(this, view);
    }
}
