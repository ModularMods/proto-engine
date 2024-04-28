package net.modularmods.protoengine;

import net.modularmods.protoengine.engine.Engine;
import net.modularmods.protoengine.game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Engine gameEng = new Engine("proto-engine", 1280, 720, game);
        gameEng.start();
    }
}