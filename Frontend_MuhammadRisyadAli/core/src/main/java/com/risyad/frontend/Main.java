package com.risyad.frontend; // CHANGE THIS to your actual package!

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.HashMap;
import java.util.Map;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Player player;
    private ScoreSystem scoreSystem;
    private ScoreDisplay scoreDisplay;
    private Map<Integer, Command> inputCommands;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        // Create player at screen center
        player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

        // Set up score system
        scoreSystem = new ScoreSystem();
        scoreDisplay = new ScoreDisplay();
        scoreSystem.registerObserver(scoreDisplay);

        // Map keys to commands
        inputCommands = new HashMap<>();
        inputCommands.put(Input.Keys.W, new MoveCommand(player, 0, 5));   // Up
        inputCommands.put(Input.Keys.A, new MoveCommand(player, -5, 0));  // Left
        inputCommands.put(Input.Keys.S, new MoveCommand(player, 0, -5));  // Down
        inputCommands.put(Input.Keys.D, new MoveCommand(player, 5, 0));   // Right

        System.out.println("Press W,A,S,D to Move. Press SPACE to add score.");
    }

    @Override
    public void render() {
        // Handle movement keys
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) inputCommands.get(Input.Keys.W).execute();
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) inputCommands.get(Input.Keys.A).execute();
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) inputCommands.get(Input.Keys.S).execute();
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) inputCommands.get(Input.Keys.D).execute();

        // Handle score key
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) scoreSystem.addScore(10);

        // Draw everything
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 0, 1); // Yellow player

        Vector2 pos = player.getPosition();
        shapeRenderer.circle(pos.x, pos.y, 20);

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
