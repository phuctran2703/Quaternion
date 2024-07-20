package com.example.opengl.Object;

public class Quaternion {
    public float w;
    public float x;
    public float y;
    public float z;

    public Quaternion(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Quaternion() {
        this(1.0f, 0.0f, 0.0f, 0.0f);
    }
    public Quaternion(float[] vector) {
        this(0.0f, vector[0], vector[1], vector[2]);
    }
    public Quaternion add(Quaternion q) {
        return new Quaternion(this.w + q.w, this.x + q.x, this.y + q.y, this.z + q.z);
    }

    public Quaternion sub(Quaternion q) {
        return new Quaternion(this.w - q.w, this.x - q.x, this.y - q.y, this.z - q.z);
    }

    public Quaternion mul(Quaternion q) {
        float newW = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;
        float newX = this.w * q.x + this.x * q.w + this.y * q.z - this.z * q.y;
        float newY = this.w * q.y - this.x * q.z + this.y * q.w + this.z * q.x;
        float newZ = this.w * q.z + this.x * q.y - this.y * q.x + this.z * q.w;
        return new Quaternion(newW, newX, newY, newZ);
    }

    public Quaternion mul(float scalar) {
        return new Quaternion(this.w * scalar, this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Quaternion conjugate() {
        return new Quaternion(this.w, -this.x, -this.y, -this.z);
    }

    public float norm() {
        return (float) Math.sqrt(this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Quaternion normalize() {
        float norm = norm();
        return new Quaternion(this.w / norm, this.x / norm, this.y / norm, this.z / norm);
    }

    public Quaternion inverse() {
        return this.conjugate().mul(1 / (this.norm() * this.norm()));
    }

    public float[] rotationMatrix() {
        float[] matrix = new float[16];

        matrix[0] = 1.0f - 2.0f * (this.y * this.y + this.z * this.z);
        matrix[1] = 2.0f * (this.x * this.y - this.w * this.z);
        matrix[2] = 2.0f * (this.x * this.z + this.w * this.y);
        matrix[3] = 0.0f;

        matrix[4] = 2.0f * (this.x * this.y + this.w * this.z);
        matrix[5] = 1.0f - 2.0f * (this.x * this.x + this.z * this.z);
        matrix[6] = 2.0f * (this.y * this.z - this.w * this.x);
        matrix[7] = 0.0f;

        matrix[8] = 2.0f * (this.x * this.z - this.w * this.y);
        matrix[9] = 2.0f * (this.y * this.z + this.w * this.x);
        matrix[10] = 1.0f - 2.0f * (this.x * this.x + this.y * this.y);
        matrix[11] = 0.0f;

        matrix[12] = 0.0f;
        matrix[13] = 0.0f;
        matrix[14] = 0.0f;
        matrix[15] = 1.0f;

        return matrix;
    }

    public float dot(Quaternion q) {
        return this.w * q.w + this.x * q.x + this.y * q.y + this.z * q.z;
    }
}