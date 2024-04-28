package net.protoengine.game;

import net.protoengine.engine.IAppLogic;
import net.protoengine.engine.graph.Render;
import net.protoengine.engine.scene.Scene;
import net.protoengine.engine.utils.Camera;
import net.protoengine.engine.window.Window;

public class Game implements IAppLogic {

    @Override
    public void cleanup() {

    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        // Initialize the camera
        scene.camera = new Camera((float) Math.toRadians(45.0f), 800f / 600f, 0.1f, 1000.0f);
        scene.camera.setPosition(0, 0, 50);

        long startTime = System.currentTimeMillis(); // Start time
        long endTime = System.currentTimeMillis(); // End time
        long duration = endTime - startTime; // Calculate duration
        System.out.println("Time taken to load model: " + duration + " ms");

    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {

    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {

    }
}
