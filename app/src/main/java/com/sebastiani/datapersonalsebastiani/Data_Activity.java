package com.sebastiani.datapersonalsebastiani;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.google.android.material.button.MaterialButton;

public class Data_Activity extends AppCompatActivity {

    ImageView imageProfile;
    ProgressDialog progressDialog;
    private  static final int CAMERA_REQUEST = 100;
    private static  final int IMAGE_PICK_CAMERA_REQUEST = 400;

    //camata almacenamiento TEMP
    String cameraPermission[];
    Uri imageUri;
    String profileOrCoverImage; // mensaje
    MaterialButton editImage;
    Button btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceSatte){
        super.onCreate(savedInstanceSatte);
        setContentView(R.layout.personal_data_fragment);
        editImage=findViewById(R.id.edit_image);
        imageProfile=findViewById(R.id.profile_image);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        cameraPermission = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Update pic");
                profileOrCoverImage = "image";
                swhoImageDialog();
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]granResult){
        super.onRequestPermissionsResult(requestCode, permissions, granResult);
        switch (requestCode){
            case CAMERA_REQUEST:{
                if(granResult.length>0){
                    boolean cameraAccepted = granResult[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = granResult[1] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && writeStorageAccepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(this, "Please enable camera permission", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "Something went wrong! try agan...", Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }


    private void swhoImageDialog(){
        String options[] ={"Camera", "Galaery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(which == 0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromCamera();
                    }
                }
            }
        });
        builder.create().show();
    }

    private boolean checkCameraPermission(){
        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return  result && result1;
    }

    private void requestCameraPermission(){
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    private void pickFromCamera(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp_pic");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Tem descripcion");
        imageUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent cameraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_REQUEST);
    }




}
