package net.protoengine.engine.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

    private final Vector3f position;
    private final Vector3f rotation;

    private final Matrix4f projectionMatrix;

    public Camera(float fov, float aspect, float zNear, float zFar) {
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
        projectionMatrix = new Matrix4f().perspective(fov, aspect, zNear, zFar);
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f().identity()
                .rotateX((float) Math.toRadians(rotation.x))
                .rotateY((float) Math.toRadians(rotation.y))
                .rotateZ((float) Math.toRadians(rotation.z))
                .translate(-position.x, -position.y, -position.z);
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
    }

    public void setRotation(float x, float y, float z) {
        rotation.set(x, y, z);
    }

    // Add methods to move or rotate the camera...
}