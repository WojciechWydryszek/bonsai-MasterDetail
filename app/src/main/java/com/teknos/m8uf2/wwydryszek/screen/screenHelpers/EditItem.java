package com.teknos.m8uf2.wwydryszek.screen.screenHelpers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.teknos.m8uf2.wwydryszek.R;
import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;
import com.teknos.m8uf2.wwydryszek.screen.ListScreen;
import com.teknos.m8uf2.wwydryszek.singletone.Singletone;
import java.util.ArrayList;

public class EditItem extends AppCompatActivity {

    private Bonsai bonsai;
    private ArrayList<Bonsai> bonsaiArrayList;
    private EditText etName, numAge, numPrice,
                     etOrigin, etFamili, etNote;
    private CheckBox checkAlive;
    private ImageView imgBonsai;
    private final int PERMISSION_REQUEST_CAMERA = 1,
                      RESULT_CAPTURE_IMAGE = 100;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_element);

        Singletone.getInstance().setPhotoEdit(false);

        this.bonsaiArrayList = Singletone.getInstance().getBonsaiList();

        if(!Singletone.getInstance().getEdit())
            this.bonsai = Singletone.getInstance().createBonsai();

        loadForms();
    }

    public void btnImage(View view) {
        Singletone.getInstance().setPhotoEdit(true);
        OnCaptureCamera();
    }

    public void OnCaptureCamera() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, RESULT_CAPTURE_IMAGE);
        } else {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_CAPTURE_IMAGE) {
            if (resultCode == RESULT_OK) {
                photo = (Bitmap) data.getExtras().get("data");
                imgBonsai.setImageBitmap(photo);
            }
            else if (resultCode == RESULT_CANCELED) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //user ok
                OnCaptureCamera();

            } else {
                //user ko
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void loadForms() {
        etName = findViewById(R.id.etName);
        numAge = findViewById(R.id.numAge);
        numPrice = findViewById(R.id.numPrice);
        etOrigin = findViewById(R.id.etOrigin);
        etFamili = findViewById(R.id.etFamili);
        etNote = findViewById(R.id.etNote);
        checkAlive = findViewById(R.id.checkAlive);
        imgBonsai = findViewById(R.id.imgBonsai);

        if(Singletone.getInstance().getEdit()) {
            this.bonsai = bonsaiArrayList.get(Singletone.getInstance().getPosition());

            findViewById(R.id.btnEdit).setVisibility(View.VISIBLE);
            findViewById(R.id.btnTrash).setVisibility(View.VISIBLE);

            etName.setText(bonsai.getName());
            etName.setEnabled(false);

            numAge.setText(bonsai.getAge());
            numAge.setEnabled(false);

            numPrice.setText(bonsai.getPrice());
            numPrice.setEnabled(false);

            etOrigin.setText(bonsai.getOrigin());
            etOrigin.setEnabled(false);

            etFamili.setText(bonsai.getFamili());
            etFamili.setEnabled(false);

            etNote.setText(bonsai.getNote());
            etNote.setEnabled(false);

            findViewById(R.id.btnImage).setEnabled(false);

            imgBonsai.setImageBitmap(bonsai.getImage());

            if(bonsai.isAlive())
                checkAlive.setChecked(true);
            else
                checkAlive.setChecked(false);

            checkAlive.setEnabled(false);
        }
    }

    public void back(View view) { finish(); }

    public void save(View view) {

        Intent intent = new Intent(EditItem.this, ListScreen.class);

        pullData();

        if(!Singletone.getInstance().getEdit())
            Singletone.getInstance().addBonsai(bonsai);

        startActivity(intent);
        finish();
    }

    public void btnEdit(View view){

        etName.setEnabled(true);
        numAge.setEnabled(true);
        numPrice.setEnabled(true);
        etOrigin.setEnabled(true);
        etFamili.setEnabled(true);
        etNote.setEnabled(true);
        checkAlive.setEnabled(true);
        findViewById(R.id.btnImage).setEnabled(true);
    }

    public void btnTrash(View view){
        Intent intent = new Intent(EditItem.this, ListScreen.class);

        startActivity(intent);

        finish();

        bonsaiArrayList.remove(Singletone.getInstance().getPosition());
    }

    private void pullData() {

        bonsai.setName(etName.getText().toString());
        bonsai.setAge(numAge.getText().toString());
        bonsai.setPrice(numPrice.getText().toString());
        bonsai.setOrigin(etOrigin.getText().toString());
        bonsai.setFamili(etFamili.getText().toString());
        bonsai.setNote(etNote.getText().toString());

        if(Singletone.getInstance().isPhotoEdit())
            bonsai.setImage(photo);

        if(checkAlive.isChecked())
            bonsai.setAlive(true);
        else
            bonsai.setAlive(false);
    }
}