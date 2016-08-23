/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.screens;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Daniel
 */
public class PlayScreen implements Screen{

    private JdcGame game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;
    
    public PlayScreen(JdcGame game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(JdcGame.V_WIDTH, JdcGame.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);
    }
    
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}  
}
