package com.risyad.frontend; 

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class DemoGame extends ApplicationAdapter {
    private ShapeRenderer shape;
    // kotak
    private float boxWidth = 100f;
    private float boxHeight = 100f;
    private float x, y; // posisi kotak (center = x,y is bottom-left in this code)
    private float speed = 300f; // pixels per second

    // warna siklus
    private Color[] colors = { Color.RED, Color.YELLOW, Color.BLUE };
    private int colorIndex = 0;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        // mulai di tengah layar (posisi bottom-left supaya gambar rect beres)
        x = (Gdx.graphics.getWidth() - boxWidth) / 2f;
        y = (Gdx.graphics.getHeight() - boxHeight) / 2f;

        // print warna awal
        System.out.println("Initial color: RED");
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        // input - keyboard
        handleKeyboard(delta);

        // input - mouse click untuk ganti warna (klik kiri)
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            cycleColor();
        }

        // clear layar (hitam)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // gambar kotak
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(colors[colorIndex]);
        shape.rect(x, y, boxWidth, boxHeight);
        shape.end();
    }

    private void handleKeyboard(float delta) {
        float move = speed * delta;

        boolean left  = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean up    = Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down  = Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (left)  x -= move;
        if (right) x += move;
        if (up)    y += move;
        if (down)  y -= move;

        // (BONUS) cegah keluar layar: clamp posisi sehingga kotak selalu terlihat
        x = MathUtils.clamp(x, 0f, Gdx.graphics.getWidth() - boxWidth);
        y = MathUtils.clamp(y, 0f, Gdx.graphics.getHeight() - boxHeight);
    }

    private void cycleColor() {
        colorIndex = (colorIndex + 1) % colors.length;
        String name = colorName(colors[colorIndex]);
        System.out.println("Color changed to: " + name);
    }

    private String colorName(Color c) {
        if (c.equals(Color.RED))    return "RED";
        if (c.equals(Color.YELLOW)) return "YELLOW";
        if (c.equals(Color.BLUE))   return "BLUE";
        // fallback: print rgba
        return String.format("Color(r=%.2f,g=%.2f,b=%.2f,a=%.2f)", c.r, c.g, c.b, c.a);
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
