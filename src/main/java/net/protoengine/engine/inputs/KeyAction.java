package net.protoengine.engine.inputs;

@FunctionalInterface
public interface KeyAction {
    void onPress();

    default void onRelease() {
        // Provide a default implementation in case only onPress is needed
    }
}