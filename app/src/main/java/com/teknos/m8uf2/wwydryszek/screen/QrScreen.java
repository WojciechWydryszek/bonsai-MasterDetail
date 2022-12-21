package com.teknos.m8uf2.wwydryszek.screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;

import java.io.IOException;
import java.util.ArrayList;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrScreen extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private EditText editText;
    private ImageView imageView;
    private ArrayList<Bonsai> bonsaiArrayList;


    private static final int RESULT_CAPTURE_IMAGE = 100;
    private static final int PERMISSION_REQUEST_CAMERA = 1;
    private TextView tv_text;
    private ConstraintLayout contentFrame;
    private ZXingScannerView mScannerView;
    private boolean cameraOn;


    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Singletone.getInstance().getTheme();
        setContentView(R.layout.qr_screen);
        this.mScannerView = new ZXingScannerView(this);
        contentFrame =findViewById(R.id.contentFrame);
        tv_text = findViewById(R.id.textView2);
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


    public void scanQr(View view){
        onCaptureQR();
    }
    public void startCamera() {
        imageView.setVisibility(View.GONE);
        contentFrame.addView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        cameraOn = true;
    }
    public void stopCamera() {
        imageView.setVisibility(View.VISIBLE);
        mScannerView.stopCamera();
        contentFrame.removeView(mScannerView);
        cameraOn = false;
    }
    private void onCaptureQR() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                QrScreen.this.startCamera();
            } else {
                this.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==RESULT_CAPTURE_IMAGE){
            if (resultCode==RESULT_OK){
                Bitmap photo=(Bitmap) data.getExtras().get("data");
                System.out.println(photo.toString());
                imageView.setImageBitmap(photo);
            }if(resultCode==RESULT_CANCELED) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSION_REQUEST_CAMERA){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Usuari ok
                //onCaptureCamara();
                onCaptureQR();
            }else{
                // Usuari ko
                // No se’ns ha otorgat permís, aquesta funcionalitat no està habilitada
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void handleResult(Result rawResult) {
        if (rawResult!=null) {
           // rawResult.getRawBytes();
            this.tv_text.setText(rawResult.getText());
        }
        stopCamera();
    }

    @Override
    public void onBackPressed() {
        if (cameraOn) {
            stopCamera();
        } else {
            // Estat per defecte
            super.onBackPressed();
        }
    }
    @Override
    protected void onPause() {
        if (cameraOn)
            stopCamera();
        super.onPause();
    }


    // Function for change screem using toolBar
    public void toolBarClick(View view) {

        Singletone.getInstance().goTo(this, view);
    }
}
