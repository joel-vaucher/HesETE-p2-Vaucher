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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
    
    //Chargement de la map
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    public PlayScreen(JdcGame game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(JdcGame.V_WIDTH, JdcGame.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);
        
        //Loader
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        
        gamecam.position.set(gameport.getWorldWidth()/ 2, gameport.getWorldHeight()/ 2, 0);
    }
    
    @Override
    public void show() {
    }

    //Evenements input 
    public void handleInput(float delta){
        
        if(Gdx.input.isTouched())
            gamecam.position.x += 600 * delta;
        
    }
    
    public void update(float delta){
        handleInput(delta);
        gamecam.update();
        renderer.setView(gamecam);
    }
    
    @Override
    public void render(float delta) {
        update(delta);
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        renderer.render();
        
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
