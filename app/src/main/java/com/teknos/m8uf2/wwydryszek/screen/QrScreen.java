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

import com.google.zxing.WriterException;
import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;
import java.util.ArrayList;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrScreen extends AppCompatActivity {

    private EditText editText;
    private ImageView imageView;
    private ArrayList<Bonsai> bonsaiArrayList;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Singletone.getInstance().getTheme();
        setContentView(R.layout.qr_screen);

        editText = findViewById(R.id.qrName);
        imageView = findViewById(R.id.imageView);

        this.bonsaiArrayList = Singletone.getInstance().getBonsaiList();

    }

    public void generateQr(View view){

        if (editText.getText().toString().equals("") || !checkValidName())
            Toast.makeText(this, "Nom Incorrecte", Toast.LENGTH_SHORT).show();
        else {
            Bonsai bonsai;
            int validBonsiPosition = 0;
            for (int i = 0; i < bonsaiArrayList.size(); i++) {
                bonsai = bonsaiArrayList.get(i);
                if (bonsai.getName().equals(editText.getText().toString()))
                     validBonsiPosition = i;
            }

            bonsai = bonsaiArrayList.get(validBonsiPosition);

            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

            Display display = manager.getDefaultDisplay();

            Point point = new Point();
            display.getSize(point);

            int width = point.x, height = point.y;

            int dimen = width < height ? width : height;
            dimen = dimen * 3 / 4;

            qrgEncoder = new QRGEncoder(generateStringQr(bonsai), null, QRGContents.Type.TEXT, dimen);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkValidName() {
        boolean validName = false;
        for (int i = 0; i < bonsaiArrayList.size(); i++) {
            Bonsai bonsai = bonsaiArrayList.get(i);
            if (bonsai.getName().equals(editText.getText().toString()))
                validName = true;
        }
        return validName;
    }

    private String generateStringQr(Bonsai bonsai) {
        String data = bonsai.getImage() + "," + bonsai.getName() + "," + bonsai.getAge() + "," + bonsai.getOrigin() + "," + bonsai.getPrice() + "," +
                bonsai.getFamili() + "," + bonsai.isAlive() + "," + bonsai.getNote();
        return data;
    }

    // Function for change screem using toolBar
    public void toolBarClick(View view) {

        Singletone.getInstance().goTo(this, view);
    }
}
