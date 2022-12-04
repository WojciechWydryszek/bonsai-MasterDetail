package com.teknos.m8uf2.wwydryszek.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.teknos.m8uf2.wwydryszek.R;

public class AlarmScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_screen);

    }

    // Function for change screem using toolBar
    public void toolBarClick(View view) {
        Button button = (Button) view;

        Intent intent = new Intent();

        if(button.getText().equals("Llista"))
            intent = new Intent(AlarmScreen.this, ListScreen.class);

        else if(button.getText().equals("Galeria"))
            intent = new Intent(AlarmScreen.this, GaleryScreen.class);

        startActivity(intent);

    }
}
