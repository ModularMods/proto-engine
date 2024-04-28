package net.modularmods.protoengine.engine.scene;

import net.modularmods.protoengine.engine.graph.Node;
import net.modularmods.protoengine.engine.utils.Camera;

public class Scene extends Node {

    public Camera camera;

    public Scene() {
        super("main");
    }

    public void cleanup() {
        // Nothing to be done here yet
    }
}