package com.teknos.m8uf2.wwydryszek.screen;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;

public class QrScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Singletone.getInstance().getTheme();
        setContentView(R.layout.qr_screen);
    }

    // Function for change screem using toolBar
    public void toolBarClick(View view) {

        Singletone.getInstance().goTo(this, view);
    }
}
