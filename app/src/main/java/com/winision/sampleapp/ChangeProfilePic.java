package com.winision.sampleapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ChangeProfilePic extends AppCompatActivity implements GestureDetector.OnGestureListener {


    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int REQUEST_CODE = 1;
    private GestureDetectorCompat detector;
    private ImageView cameraToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_pic);

        detector = new GestureDetectorCompat(this, this);

        cameraToggle = findViewById(R.id.cameraIcon);

        cameraToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeProfilePic.this, CameraPreview.class));
            }
        });

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent motionEvent, float v, float v1) {

        float diffY = motionEvent.getY() - downEvent.getY();

        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(v1) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffY < 0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);

            }
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            Intent intent = new Intent(ChangeProfilePic.this, TabbedActivity.class);
            intent.putExtra("ImageURI", uri);
            startActivity(intent);

        }
    }
}
