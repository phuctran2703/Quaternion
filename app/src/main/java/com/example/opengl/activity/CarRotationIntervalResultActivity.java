package com.example.opengl.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.example.opengl.renderer.ObjectRotationIntervalRenderer;
import com.example.opengl.renderer.ObjectRotationRenderer;

public class CarRotationIntervalResultActivity extends AppCompatActivity {
    private Bundle data;
    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLSurfaceView = new GLSurfaceView(this);

        data = getIntent().getExtras();
        float axisX = data.getFloat("axisX");
        float axisY = data.getFloat("axisY");
        float axisZ = data.getFloat("axisZ");
        float rotationAngle = data.getFloat("rotationAngle");
        float rotationDuration = data.getFloat("rotationDuration");

        Log.d("Rotation Axis", "X: " + axisX + " Y: " + axisY + " Z: " + axisZ);
        Log.d("Rotation Angle", rotationAngle+"");
        Log.d("Rotation Duration", ""+rotationDuration);

        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if (supportsEs2) {
            mGLSurfaceView.setEGLContextClientVersion(2);

            float[] rotationAxis = {axisX, axisY, axisZ};

            mGLSurfaceView.setRenderer(new ObjectRotationIntervalRenderer(this, "laurel.obj", rotationAxis, rotationAngle, rotationDuration));
        } else {
            return;
        }

        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
