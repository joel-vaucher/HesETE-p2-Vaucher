/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.screens;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.scenes.Hud;
import ch.hearc.jdcgame.sprites.Player;
import ch.hearc.jdcgame.tools.B2dWorldCreator;
import ch.hearc.jdcgame.tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
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
    
    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    
    //player
    private Player player;
    private boolean TeleportReady;
    private float timeToReloadTeleport;
    private float reloadTeleport;
    
    //Music
    public Music music;
    
    public PlayScreen(JdcGame game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(JdcGame.V_WIDTH / JdcGame.PPM, JdcGame.V_HEIGHT / JdcGame.PPM, gamecam);
        hud = new Hud(game.batch);
        
        //Loader
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / JdcGame.PPM);
        
        gamecam.position.set(gameport.getWorldWidth()/ 2, gameport.getWorldHeight()/ 2, 0);
    
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        
        new B2dWorldCreator(world, map);
        
        player = new Player(world);
        TeleportReady = true;
        timeToReloadTeleport = 1;
        reloadTeleport = 0;
        
        //Son jeu
        music = JdcGame.manager.get("audio/music/gamesic.mp3", Music.class);
        music.setLooping(true);
        music.play();
        
        world.setContactListener(new WorldContactListener());
    }
    
    @Override
    public void show() {
    }

    //Evenements input 
    public void handleInput(float delta){
        
        if(Gdx.input.isTouched() && TeleportReady) {
            
            float gamePosX = Gdx.input.getX() - gameport.getLeftGutterWidth();
            float gamePosY = Gdx.input.getY() - gameport.getTopGutterHeight();
            float logicPosX =  (gamecam.position.x - gameport.getWorldWidth()/ 2) + (gameport.getWorldWidth()/gameport.getScreenWidth()) * gamePosX ;
            float logicPosY =  gameport.getWorldHeight() - (gameport.getWorldHeight()/gameport.getScreenHeight()) * gamePosY;
            player.b2body.setTransform(logicPosX,logicPosY, 0);
            TeleportReady = false;
            JdcGame.manager.get("audio/sounds/teletransportation.mp3", Sound.class).play();
        }
        
        //debug
        /*
        System.out.println("Sxy : "+ Gdx.input.getX() + "-" + Gdx.input.getY() +
                ", SCwh : "+ gameport.getScreenWidth() + "-" + gameport.getScreenHeight() +
                ", Wwh : " + gameport.getWorldWidth() + "-" + gameport.getWorldHeight());
        System.out.println(player.b2body.getPosition().x);
        System.out.println(player.b2body.getPosition().y);
        */
        
    }
    
    public void update(float delta){
        handleInput(delta);
        
        world.step(1/60f, 6, 2);
        gamecam.position.x += delta;
        if(player.b2body.getLinearVelocity().x <= 1f)
            player.b2body.applyLinearImpulse(new Vector2(0.5f, 0),player.b2body.getWorldCenter(), true);
        if(!TeleportReady){
            reloadTeleport += delta;
            if(reloadTeleport >= timeToReloadTeleport){
                TeleportReady = true;
                reloadTeleport = 0;
            }
        }
        
        gamecam.update();
        renderer.setView(gamecam);
    }
    
    @Override
    public void render(float delta) {
        update(delta);
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        renderer.render();
        
        //renderer Box2DDebugLines
        b2dr.render(world, gamecam.combined);
        
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
    public void dispose() {
    
        //libération ressources
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }  
}
