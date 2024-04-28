package net.modularmods.protoengine.engine.inputs;

import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class InputsManager {

    private final Map<Integer, KeyAction> keyActions = new HashMap<>();
    private final Map<Integer, Boolean> keyStates = new HashMap<>();
    private GLFWKeyCallback keyCallback;

    public void setupKeyCallbacks(long window) {
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                KeyAction keyAction = keyActions.get(key);

                if (action == GLFW_PRESS) {
                    if (keyAction != null && !keyStates.getOrDefault(key, false)) {
                        keyAction.onPress();
                        keyStates.put(key, true);
                    }
                } else if (action == GLFW_RELEASE) {
                    if (keyAction != null && keyStates.getOrDefault(key, false)) {
                        keyAction.onRelease();
                        keyStates.put(key, false);
                    }
                }
            }
        };

        glfwSetKeyCallback(window, keyCallback);
    }

    public void addKeybind(int key, KeyAction action) {
        keyActions.put(key, action);
        keyStates.put(key, false);
    }

    public void cleanup() {
        if (keyCallback != null) {
            keyCallback.free();
        }
    }
}