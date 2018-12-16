package com.winision.sampleapp;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Calling extends AppCompatActivity {

    private Camera mCamera;
    private CameraSurface mPreview;
    private Context mContext;
    private LinearLayout cameraPreview;
    private FloatingActionButton callEndFab;
    private TextView userNameCallingTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        callEndFab = findViewById(R.id.callEndFab);
        userNameCallingTxt = findViewById(R.id.userNameCallingTxt);


        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("UserName");

        userNameCallingTxt.setText(userName);

        callEndFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mContext = this;

        mCamera = mCamera.open();
        mCamera.setDisplayOrientation(90);
        cameraPreview = findViewById(R.id.cameraCalling);
        mPreview = new CameraSurface(mContext, mCamera);
        cameraPreview.addView(mPreview);

        mCamera.startPreview();

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mCamera == null) {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            mPreview.refreshCamera(mCamera);
        }

    }


    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

}
