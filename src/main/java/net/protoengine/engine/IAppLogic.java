package net.protoengine.engine;

import net.protoengine.engine.graph.Render;
import net.protoengine.engine.scene.Scene;
import net.protoengine.engine.window.Window;

public interface IAppLogic {

    void cleanup();

    void init(Window window, Scene scene, Render render);

    void input(Window window, Scene scene, long diffTimeMillis);

    void update(Window window, Scene scene, long diffTimeMillis);
}