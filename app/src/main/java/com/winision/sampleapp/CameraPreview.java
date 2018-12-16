package com.winision.sampleapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class CameraPreview extends AppCompatActivity {

    public static Bitmap bitmap;
    private Camera mcamera;
    private CameraSurface mPreview;
    private Camera.PictureCallback mPicture;
    private FloatingActionButton capture, switchCamera;
    private Context context;
    private LinearLayout cameraPreview;
    private boolean cameraFront = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        context = this;

        mcamera = Camera.open();
        mcamera.setDisplayOrientation(90);
        cameraPreview = findViewById(R.id.cameraPreview);
        mPreview = new CameraSurface(context, mcamera);
        cameraPreview.addView(mPreview);

        capture = findViewById(R.id.btnCam);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcamera.takePicture(null, null, mPicture);
            }
        });

        switchCamera = findViewById(R.id.btnSwitch);
        switchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cameraNumber = Camera.getNumberOfCameras();
                if (cameraNumber > 1) {
                    releaseCamera();
                    chooseCamera();
                }
            }
        });

        mcamera.startPreview();

    }


    private int findBackFacingCamera() {
        int cameraId = 1;

        int numberOfCameras = Camera.getNumberOfCameras();

        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;
            }
        }
        return cameraId;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mcamera == null) {
            mcamera = Camera.open();
            mcamera.setDisplayOrientation(90);
            mPicture = getPictureCallback();
            mPreview.refreshCamera(mcamera);
        }
    }

    private void chooseCamera() {
        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                mcamera = Camera.open(cameraId);
                mcamera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mcamera);
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                mcamera = Camera.open(cameraId);
                mcamera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mcamera);
            }
        }
    }

    private int findFrontFacingCamera() {
        int cameraId = 1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = 1;
                cameraFront = true;
                break;
            }
        }
        return cameraId;
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (mcamera != null) {
            mcamera.stopPreview();
            mcamera.setPreviewCallback(null);
            mcamera.release();
            mcamera = null;
        }
    }

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

            }
        };
        return picture;
    }
}
