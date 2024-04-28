package net.protoengine.engine;

import net.protoengine.engine.graph.Render;
import net.protoengine.engine.inputs.InputsManager;
import net.protoengine.engine.scene.Scene;
import net.protoengine.engine.window.Window;

public class Engine {

    private final IAppLogic appLogic;
    private final Window window;
    private final InputsManager eventManager;
    private final int targetFps;
    private final int targetUps;

    private final Scene scene;
    private final Render render;

    private boolean running;

    public Engine(String title, int width, int height, IAppLogic appLogic) {
        this.window = new Window(title, width, height, () -> {
            resize();
            return null;
        });
        this.window.init();

        this.eventManager = new InputsManager();
        this.eventManager.setupKeyCallbacks(this.window.getHandle());

        this.targetFps = 144;
        this.targetUps = 30;
        this.appLogic = appLogic;

        this.render = new Render();
        this.scene = new Scene();

        appLogic.init(window, scene, render);
        this.running = true;
    }

    private void run() {
        long initialTime = System.currentTimeMillis();
        float timeU = 1000.0f / targetUps;
        float timeR = targetFps > 0 ? 1000.0f / targetFps : 0;
        float deltaUpdate = 0;
        float deltaFps = 0;

        long updateTime = initialTime;
        while (running && !window.windowShouldClose()) {
            window.pollEvents();

            long now = System.currentTimeMillis();
            deltaUpdate += (now - initialTime) / timeU;
            deltaFps += (now - initialTime) / timeR;

            if (targetFps <= 0 || deltaFps >= 1) {
                appLogic.input(window, scene, now - initialTime);
            }

            if (deltaUpdate >= 1) {
                long diffTimeMillis = now - updateTime;
                appLogic.update(window, scene, diffTimeMillis);
                updateTime = now;
                deltaUpdate--;
            }

            if (targetFps <= 0 || deltaFps >= 1) {
                render.render(window, scene);
                deltaFps--;
                window.update();
            }
            initialTime = now;
        }

        cleanup();
    }

    public void start() {
        running = true;
        run();
    }

    public void stop() {
        running = false;
    }

    private void resize() {
        // Nothing to be done yet
    }

    private void cleanup() {
        window.cleanup();
        eventManager.cleanup();
    }

}
