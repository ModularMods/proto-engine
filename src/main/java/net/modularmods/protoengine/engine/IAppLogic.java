package net.modularmods.protoengine.engine;

import net.modularmods.protoengine.engine.window.Window;
import net.modularmods.protoengine.engine.graph.Render;
import net.modularmods.protoengine.engine.scene.Scene;

public interface IAppLogic {

    void cleanup();

    void init(Window window, Scene scene, Render render);

    void input(Window window, Scene scene, long diffTimeMillis);

    void update(Window window, Scene scene, long diffTimeMillis);
}