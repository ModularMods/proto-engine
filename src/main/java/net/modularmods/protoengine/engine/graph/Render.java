package net.modularmods.protoengine.engine.graph;

import net.modularmods.protoengine.engine.scene.Scene;
import net.modularmods.protoengine.engine.window.Window;
import net.modularmods.protoengine.engine.utils.Camera;
import net.modularmods.protoengine.engine.utils.Shader;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;

public class Render {

    public static Shader mainShader;

    public Render() {
        GL.createCapabilities();
        // Load and compile shaders
        try {
            mainShader = new Shader("/assets/shaders/vertexShader.glsl", "/assets/shaders/fragmentShader.glsl");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanup() {
        // Nothing to be done here yet
    }

    public void render(Window window, Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        mainShader.bind(); // Bind shader program
        // Set projection and view matrices in shader
        setShaderMatrices(mainShader, scene.camera);
        Matrix4f viewMatrix = scene.camera.getViewMatrix();

        scene.render(mainShader, viewMatrix);

        mainShader.unbind(); // Unbind shader program
    }

    private void setShaderMatrices(Shader shader, Camera camera) {
        // Retrieve and set projection and view matrices from the camera
        Matrix4f projectionMatrix = camera.getProjectionMatrix();
        shader.setUniform("projectionMatrix", projectionMatrix);
        Matrix4f viewMatrix = camera.getViewMatrix();
        shader.setUniform("viewMatrix", viewMatrix);
    }
}