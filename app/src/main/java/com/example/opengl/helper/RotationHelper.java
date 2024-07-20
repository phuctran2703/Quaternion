package com.example.opengl.helper;

import android.util.Log;

import com.example.opengl.Object.Quaternion;

public class RotationHelper {
    public static float[] rotationMatrix(float[] vector, float angle) {
        float norm = (float) Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]);
        float[] vectorNormal = new float[3];
        vectorNormal[0] = vector[0] / norm;
        vectorNormal[1] = vector[1] / norm;
        vectorNormal[2] = vector[2] / norm;

        angle = (float) Math.toRadians(angle);
        Quaternion qRotation = new Quaternion(
                (float) Math.cos(angle / 2),
                vectorNormal[0] * (float) Math.sin(angle / 2),
                vectorNormal[1] * (float) Math.sin(angle / 2),
                vectorNormal[2] * (float) Math.sin(angle / 2)
        );

        return qRotation.rotationMatrix();
    }

    public static float[] rotationMatrix(Quaternion q){
        q = q.normalize();
        return q.rotationMatrix();
    }
    public static Quaternion interpolate(Quaternion q1, Quaternion q2, float t) {
        // Normalize input quaternions
        q1 = q1.normalize();
        q2 = q2.normalize();

        // Compute the cosine of the angle between the two quaternions
        float dot = q1.dot(q2);

        if (dot < 0.0f) {
            q2 = new Quaternion(-q2.w, -q2.x, -q2.y, -q2.z);
            dot = -dot;
        }

        // Clamp dot product to avoid numerical issues
        dot = Math.min(Math.max(dot, -1.0f), 1.0f);

        // Calculate the angle between the quaternions
        float theta = (float) Math.acos(dot);

        // Calculate the relative weights
        float sinTheta = (float) Math.sin(theta);
        float weight1 = (float) Math.sin((1 - t) * theta) / sinTheta;
        float weight2 = (float) Math.sin(t* theta) / sinTheta;

        // Perform the slerp
        float w = weight1 * q1.w + weight2 * q2.w;
        float x = weight1 * q1.x + weight2 * q2.x;
        float y = weight1 * q1.y + weight2 * q2.y;
        float z = weight1 * q1.z + weight2 * q2.z;

        return new Quaternion(w, x, y, z).normalize();
    }
}
