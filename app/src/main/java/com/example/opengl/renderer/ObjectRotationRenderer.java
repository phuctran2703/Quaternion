package com.example.opengl.renderer;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import com.example.opengl.Object.AABB;
import com.example.opengl.Object.Car;
import com.example.opengl.Object.Colors;
import com.example.opengl.Object.Quaternion;
import com.example.opengl.Object.Ray;
import com.example.opengl.helper.RotationHelper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ObjectRotationRenderer extends MeshRender {
    private Car car;
    private Ray ray;
    private float angle;
    Quaternion q1;
    Quaternion q2;
    float t=-1;

    public ObjectRotationRenderer(Context context, String fileName, float[] rayDirection, float angle) {
        this.car = new Car(context, fileName);
        this.ray = new Ray(new float[]{-1, -1, -1}, rayDirection);
        this.angle = angle;

        mActivityContext = context;

        setupVertexBuffers();
        setupRayBuffer();
    }

    public ObjectRotationRenderer(Context context, String fileName, Quaternion q1, Quaternion q2) {
        this.car = new Car(context, fileName);
        this.angle = 0;
        this.ray = new Ray(new float[]{-1, -1, -1}, new float[]{0, 0, 0});
        this.t = 0;
        this.q1 = q1;
        this.q2 = q2;

        mActivityContext = context;

        setupVertexBuffers();
        setupRayBuffer();
    }

    public ObjectRotationRenderer(Context context, String fileName, float[] rayDirection, float angle, float interval, float time) {
        angle = angle / interval * time;
        this.car = new Car(context, fileName);
        this.ray = new Ray(new float[]{-1, -1, -1}, rayDirection);
        this.angle = angle;

        mActivityContext = context;

        setupVertexBuffers();
        setupRayBuffer();
    }

    private void setupVertexBuffers() {
        float[] vertices = this.car.generate();
        numPoints = vertices.length / (mPositionDataSize + mTextureDataSize);
        mVertexBuffer = ByteBuffer.allocateDirect(vertices.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);
    }

    private void setupRayBuffer() {
        float[] rayVertices = {
                ray.rayPosition[0], ray.rayPosition[1], ray.rayPosition[2],
                ray.rayPosition[0] + ray.rayDirection[0] * 1000, ray.rayPosition[1] + ray.rayDirection[1] * 1000, ray.rayPosition[2] + ray.rayDirection[2] * 1000
        };

        mRayBuffer = ByteBuffer.allocateDirect(rayVertices.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        mRayBuffer.put(rayVertices);
        mRayBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        super.onSurfaceCreated(glUnused, config);

        AABB box = this.car.getBoundingBox();

        final float eyeX = (box.pMin[0] + box.pMax[0]) / 2 + 3;
        final float eyeY = (box.pMin[1] + box.pMax[1]) / 2 + 3;
        final float eyeZ = (box.pMax[2] + box.pMin[2]) / 2 - 10;

        final float lookX = (box.pMin[0] + box.pMax[0]) / 2;
        final float lookY = (box.pMin[1] + box.pMax[1]) / 2;
        final float lookZ = (box.pMin[2] + box.pMax[2]) / 2;

        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;
        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, mVBOHandles[1]);
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, mRayBuffer.capacity() * mBytesPerFloat, mRayBuffer, GLES30.GL_DYNAMIC_DRAW);

        if (mPointBuffer!=null) {
            GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, mVBOHandles[2]);
            GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, mPointBuffer.capacity() * mBytesPerFloat, mPointBuffer, GLES30.GL_DYNAMIC_DRAW);
        }
    }

//    @Override
//    public void onDrawFrame(GL10 glUnused) {
//        super.onDrawFrame(glUnused);
//
//        Matrix.setIdentityM(mModelMatrix, 0);
//        super.drawRay(10.0f, Colors.WHITE, Colors.RED);
//
//        float[] rotationMatrix = RotationHelper.rotationMatrix(this.ray.rayDirection, this.angle);
//        Matrix.multiplyMM(mModelMatrix, 0, mModelMatrix, 0, rotationMatrix, 0);
//
//        GLES30.glUniformMatrix4fv(mModelMatrixHandle, 1, false, mModelMatrix, 0);
//
//        super.drawMesh(Colors.WHITE);
//    }

    public void updateT(float t) {
        this.t = t;
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {
        super.onDrawFrame(glUnused);

        Matrix.setIdentityM(mModelMatrix, 0);
        float[] rotationMatrix;
        if(t==-1){
            super.drawRay(10.0f, Colors.WHITE, Colors.RED);
            rotationMatrix = RotationHelper.rotationMatrix(this.ray.rayDirection, this.angle);
        }
        else{
            Quaternion q = RotationHelper.interpolate(this.q1, this.q2, t);
            rotationMatrix = RotationHelper.rotationMatrix(q);
        }
        Matrix.multiplyMM(mModelMatrix, 0, mModelMatrix, 0, rotationMatrix, 0);

        GLES30.glUniformMatrix4fv(mModelMatrixHandle, 1, false, mModelMatrix, 0);

        super.drawMesh(Colors.WHITE);
    }
}
