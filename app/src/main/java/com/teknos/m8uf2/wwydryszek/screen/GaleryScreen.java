package com.teknos.m8uf2.wwydryszek.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.teknos.m8uf2.wwydryszek.R;

public class GaleryScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galery_screen);

    }

    // Function for change screem using toolBar
    public void toolBarClick(View view) {
        Button button = (Button) view;

        Intent intent = new Intent();

        if(button.getText().equals("Llista"))
            intent = new Intent(GaleryScreen.this, ListScreen.class);

        else if(button.getText().equals("Alarmes"))
            intent = new Intent(GaleryScreen.this, AlarmScreen.class);

        startActivity(intent);

    }
}
