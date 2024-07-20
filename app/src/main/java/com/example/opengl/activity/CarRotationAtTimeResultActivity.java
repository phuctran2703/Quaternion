package com.example.opengl.activity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.example.opengl.Object.Quaternion;
import com.example.opengl.R;
import com.example.opengl.renderer.ObjectRotationRenderer;

public class CarRotationAtTimeResultActivity extends AppCompatActivity {
    private Bundle data;
    private GLSurfaceView mGLSurfaceView;
    private ObjectRotationRenderer renderer;
    private SeekBar timeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = getIntent().getExtras();
        float axisW1 = data.getFloat("axisW1");
        float axisX1 = data.getFloat("axisX1");
        float axisY1 = data.getFloat("axisY1");
        float axisZ1 = data.getFloat("axisZ1");
        float axisW2 = data.getFloat("axisW2");
        float axisX2 = data.getFloat("axisX2");
        float axisY2 = data.getFloat("axisY2");
        float axisZ2 = data.getFloat("axisZ2");

        setContentView(R.layout.rotate_car_at_time_result);

        FrameLayout mGLSurfaceViewContainer = findViewById(R.id.glSurfaceViewContainer);
        timeSeekBar = findViewById(R.id.timeSeekBar);

        Log.d("axis", Float.toString(axisW1));
        Log.d("axis", Float.toString(axisX1));
        Log.d("axis", Float.toString(axisY1));
        Log.d("axis", Float.toString(axisZ1));
        Log.d("axis", Float.toString(axisW2));
        Log.d("axis", Float.toString(axisX2));
        Log.d("axis", Float.toString(axisY2));
        Log.d("axis", Float.toString(axisZ2));

        // Setup mGLSurfaceView
        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setEGLContextClientVersion(2);
        renderer = new ObjectRotationRenderer(this,"laurel.obj", new Quaternion(axisW1, axisX1, axisY1, axisZ1), new Quaternion(axisW2, axisX2, axisY2, axisZ2));
        mGLSurfaceView.setRenderer(renderer);
        mGLSurfaceView.setRenderMode(mGLSurfaceView.RENDERMODE_CONTINUOUSLY);

        mGLSurfaceViewContainer.addView(mGLSurfaceView);

        // Setup SeekBar
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float t = progress / 100.0f;
                renderer.updateT(t);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something when start touching
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do something when stop touching
            }
        });
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
