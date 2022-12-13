package com.teknos.m8uf2.wwydryszek.screen;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;

public class GaleryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galery_screen);

    }
    // Function for change screem using toolBar
    public void toolBarClick(View view) {
        Singletone.getInstance().goTo(this, view);
    }
}
