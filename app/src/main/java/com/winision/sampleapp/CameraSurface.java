package com.winision.sampleapp;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraSurface(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);

        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            if (mCamera == null) {
                mCamera.setPreviewDisplay(surfaceHolder);
                mCamera.startPreview();
            }
        } catch (IOException e) {

        }
    }

    public void refreshCamera(Camera camera) {
        if (mHolder.getSurface() == null) {
            return;
        }

        try {
            mCamera.stopPreview();
        } catch (Exception e) {

        }

        setCamera(camera);

        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {

        }

    }

    public void setCamera(Camera camera) {
        mCamera = camera;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        refreshCamera(mCamera);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
