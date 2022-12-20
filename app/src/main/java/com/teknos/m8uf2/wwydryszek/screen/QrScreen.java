package com.teknos.m8uf2.wwydryszek.screen;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.zxing.WriterException;
import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.adapter.AdapterQr;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;
import java.util.ArrayList;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrScreen extends AppCompatActivity {

    private EditText editText;
    private ImageView imageView;
    private ArrayList<Bonsai> bonsaiArrayList;
    private RecyclerView recyclerView;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Singletone.getInstance().getTheme();
        setContentView(R.layout.qr_screen);

        editText = findViewById(R.id.editTextTextPersonName);
        imageView = findViewById(R.id.imageView);

        this.bonsaiArrayList = Singletone.getInstance().getBonsaiList();

        if(bonsaiArrayList.size() > 0) {

            this.recyclerView = findViewById(R.id.galleryRecycleView);

            GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
            this.recyclerView.setLayoutManager(linearLayoutManager);

            AdapterQr adapterQr = new AdapterQr(Singletone.getInstance().getBonsaiList(), this);
            this.recyclerView.setAdapter(adapterQr);
        }
    }

    public void generateQr(){

        String text = generateStringQr();

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        Display display = manager.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);

        int width = point.x, height = point.y;

        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        qrgEncoder = new QRGEncoder(editText.getText().toString(), null, QRGContents.Type.TEXT, dimen);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateStringQr() {

        return null;
    }

    // Function for change screem using toolBar
    public void toolBarClick(View view) {

        Singletone.getInstance().goTo(this, view);
    }
}
