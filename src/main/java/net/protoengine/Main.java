package net.protoengine;

import net.protoengine.engine.Engine;
import net.protoengine.game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Engine gameEng = new Engine("proto-engine", 1280, 720, game);
        gameEng.start();
    }
}