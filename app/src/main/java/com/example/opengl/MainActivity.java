package com.example.opengl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.example.opengl.activity.CarRotationActivity;
import com.example.opengl.activity.CarRotationAtTimeActivity;
import com.example.opengl.activity.CarRotationIntervalActivity;
import com.example.opengl.renderer.ObjectRotationIntervalRenderer;
import com.example.opengl.renderer.ObjectRotationRenderer;

public class MainActivity extends AppCompatActivity {
    Button RotateCar, RotateCarAtTime, RotateCarInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RotateCar = findViewById(R.id.RotateCar);
        RotateCarAtTime = findViewById(R.id.RotateCarAtTime);
        RotateCarInterval = findViewById(R.id.RotateCarInterval);

        RotateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarRotationActivity.class);
                startActivity(intent);
            }
        });

        RotateCarAtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarRotationAtTimeActivity.class);
                startActivity(intent);
            }
        });

        RotateCarInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarRotationIntervalActivity.class);
                startActivity(intent);
            }
        });
    }
}

//public class MainActivity extends AppCompatActivity {
//    private GLSurfaceView mGLSurfaceView;
//    private SeekBar timeSeekBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mGLSurfaceView = new GLSurfaceView(this);
//        timeSeekBar = findViewById(R.id.timeSeekBar);
//
//        ((FrameLayout) findViewById(R.id.glSurfaceViewContainer)).addView(mGLSurfaceView);
//
//        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
//        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
//
//        if (supportsEs2)
//        {
//            mGLSurfaceView.setEGLContextClientVersion(2);
//            mGLSurfaceView.setRenderer(new ObjectRotationRenderer(this,"laurel.obj", new float[]{0.0f, 1.0f, 0.0f}, 90));
//        }
//        else
//        {
//            return;
//        }
//
//    }
//    protected void onResume()
//    {
//        super.onResume();
//        mGLSurfaceView.onResume();
//    }
//
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        mGLSurfaceView.onPause();
//    }
//}

//public class MainActivity extends AppCompatActivity {
//
//    private GLSurfaceView glSurfaceView;
//    private ObjectRotationRenderer renderer;
//    private SeekBar timeSeekBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        FrameLayout glSurfaceViewContainer = findViewById(R.id.glSurfaceViewContainer);
//        timeSeekBar = findViewById(R.id.timeSeekBar);
//
//        // Setup GLSurfaceView
//        glSurfaceView = new GLSurfaceView(this);
//        glSurfaceView.setEGLContextClientVersion(2);
//        renderer = new ObjectRotationRenderer(this,"laurel.obj", new float[]{0.0f, 1.0f, 0.0f}, 180, 10,5);
//        glSurfaceView.setRenderer(renderer);
//        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
//
//        glSurfaceViewContainer.addView(glSurfaceView);
//
//        // Setup SeekBar
//        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                float angle = progress * 360 / 100.0f;
//                renderer.updateAngle(angle);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                // Do something when start touching
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                // Do something when stop touching
//            }
//        });
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        glSurfaceView.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        glSurfaceView.onResume();
//    }
//}
