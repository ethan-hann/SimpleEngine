package com.kelvin101.gametest;

import com.kelvin101.engine.audio.AudioManager;
import com.kelvin101.engine.base.Game;
import com.kelvin101.engine.base.GameObject;
import com.kelvin101.engine.collision.AABB;
import com.kelvin101.engine.collision.Bounds;
import com.kelvin101.engine.gamestates.State;
import com.kelvin101.engine.gamestates.StateManager;
import com.kelvin101.engine.util.Dimension;
import com.kelvin101.engine.util.Vector2f;
import com.kelvin101.gametest.entities.Player;
import com.kelvin101.gametest.entities.Square;
import com.kelvin101.gametest.states.GameState;

public class EngineLauncher
{
    public static void main(String[] args)
    {
        /*new WelcomeScreen().setup().display();*/
        Game.getInstance();
        Vector2f playerPos = new Vector2f(600, 200);
        Dimension playerSize = new Dimension(32, 32);
        Bounds playerBounds = new AABB(playerPos, playerSize);

        Vector2f squarePos = new Vector2f(300, 200);
        Dimension squareSize = new Dimension(32, 32);
        Bounds squareBounds = new AABB(squarePos, squareSize);

        State gameState = new GameState("game_state");

        GameObject player = new Player(playerPos, playerSize, playerBounds);
        GameObject obstacle = new Square(squarePos, squareSize, squareBounds);
        StateManager.getInstance()
                .getStates()
                .get(gameState.getStateName())
                .getStateObjects()
                .put("player", player);
        StateManager.getInstance()
                .getStates()
                .get(gameState.getStateName())
                .getStateObjects()
                .put("obstacle", obstacle);
        StateManager.getInstance().setCurrentState(gameState);
        AudioManager.getInstance().addAudioClip("./res/audio/test.wav", "testClip", 0);
        AudioManager.getInstance().setVolume("testClip", 0.1);
//        InputManager.getInstance().changeKeyMapping(38, "up_key");
    }
}
