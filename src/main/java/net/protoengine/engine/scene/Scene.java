package net.protoengine.engine.scene;

import net.protoengine.engine.graph.Node;
import net.protoengine.engine.utils.Camera;

public class Scene extends Node {

    public Camera camera;

    public Scene() {
        super("main");
    }

    public void cleanup() {
        // Nothing to be done here yet
    }
}