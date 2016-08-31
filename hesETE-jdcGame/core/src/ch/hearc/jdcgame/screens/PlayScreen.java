/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.screens;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.scenes.Hud;
import ch.hearc.jdcgame.scenes.PauseScene;
import ch.hearc.jdcgame.sprites.Player;
import ch.hearc.jdcgame.sprites.Teleportation;
import ch.hearc.jdcgame.tools.B2dWorldCreator;
import ch.hearc.jdcgame.tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Daniel
 */
public class PlayScreen implements Screen{

    private JdcGame game;
    private TextureAtlas sprite;
    
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;
    
    //Chargement de la map
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private int widthmap;
    private static String levelFileName;
    
   //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    
    //player
    private Player player;
    private Teleportation teleportation;
    private boolean TeleportReady;
    private float timeToReloadTeleport;
    private float reloadTeleport;
    
    private boolean GravityReady;
    private float timeToReloadGravity;
    private float reloadGravity;
    
    private float gravity;
    
    private boolean debugMoving = false;
    
    //Music
    public Music music;
    
    private boolean pause;
    private PauseScene pauseScene;
    
    public PlayScreen(JdcGame game) {
        this(game, levelFileName);
    }
    
    public PlayScreen(JdcGame game, String levelFileName){
        this.levelFileName = levelFileName;
        sprite = new TextureAtlas("sprite.pack");
        
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(JdcGame.V_WIDTH / JdcGame.PPM, JdcGame.V_HEIGHT / JdcGame.PPM, gamecam);
        hud = new Hud(game.batch);
        
        //Loader
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(levelFileName);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / JdcGame.PPM);
        widthmap = map.getProperties().get("width", Integer.class) *
                   map.getProperties().get("tilewidth", Integer.class);
        
        gamecam.position.set(gameport.getWorldWidth()/ 2, gameport.getWorldHeight()/ 2, 0);
    
        pause = false;
        
        gravity = -9;
        
        world = new World(new Vector2(0, gravity), true);
        b2dr = new Box2DDebugRenderer();
        
        new B2dWorldCreator(this);
        
        player = new Player(this);
        teleportation = new Teleportation(this);
        TeleportReady = true;
        timeToReloadTeleport = 1;
        reloadTeleport = 0;
        
        GravityReady = true;
        timeToReloadGravity = 2;
        reloadGravity = 0;
        
        //Son jeu
        music = JdcGame.manager.get("audio/music/gamesic.mp3", Music.class);
        music.setLooping(true);
        music.play();
        
        world.setContactListener(new WorldContactListener());
    }
    
    public TextureAtlas getSprites(){
        return sprite;
    }
    
    @Override
    public void show() {
    }

    //Evenements input 
    public void handleInput(float delta){
        
        if(Gdx.input.isTouched() && TeleportReady && !pause) {
            
            float gamePosX = Gdx.input.getX() - gameport.getLeftGutterWidth();
            float gamePosY = Gdx.input.getY() - gameport.getTopGutterHeight();
            float logicPosX =  (gamecam.position.x - gameport.getWorldWidth()/ 2) + (gameport.getWorldWidth()/gameport.getScreenWidth()) * gamePosX ;
            float logicPosY =  gameport.getWorldHeight() - (gameport.getWorldHeight()/gameport.getScreenHeight()) * gamePosY;
            player.b2body.setTransform(logicPosX,logicPosY, 0);
            teleportation.changePosition(logicPosX, logicPosY);
            TeleportReady = false;
            JdcGame.manager.get("audio/sounds/teletransportation.mp3", Sound.class).play();
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.SPACE) && GravityReady && !pause) {
            gravity *= -1;
            world.setGravity(new Vector2(0, gravity));
            GravityReady = false;
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.P)) {
            pause = !pause;
            if(pause) {
                music.pause();
                pauseScene =  new PauseScene(game.batch);
            } else {
                music.play();
                pauseScene.dispose();
            }
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
        
        if(!pause) {
            world.step(1/60f, 6, 2);
            if(!debugMoving) {
                Gdx.app.log("position x", gamecam.position.x + " " + (float)widthmap / JdcGame.PPM);
                if(gamecam.position.x + gameport.getWorldWidth()/ 2 < (float)widthmap / JdcGame.PPM)
                gamecam.position.x += delta;
                if(player.b2body.getLinearVelocity().x <= 1f)
                    player.b2body.applyLinearImpulse(new Vector2(0.5f, 0),player.b2body.getWorldCenter(), true);
            }
            if(!TeleportReady){
                teleportation.update(delta);
                reloadTeleport += delta;
                if(reloadTeleport >= timeToReloadTeleport){
                    TeleportReady = true;
                    reloadTeleport = 0;
                }
            }
            if(!GravityReady){
                reloadGravity += delta;
                if(reloadGravity >= timeToReloadGravity){
                    GravityReady = true;
                    reloadGravity = 0;
                }
            }        
            player.update(delta);

            //Mise a jour de la position de la camera suivant les nouvelles coordonnées
            gamecam.update();
            renderer.setView(gamecam);
        }
    }
    
    @Override
    public void render(float delta) {
        update(delta);
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        
        //TiledMapTileLayer mainLayer = (TiledMapTileLayer) map.getLayers().get(0);
        //int tileSize = (int) mainLayer.getTileWidth();
        //int mapWidth = mainLayer.getWidth() * tileSize;  
        //int mapHeight = mainLayer.getHeight() * tileSize;
        //System.out.println(mapWidth);
        //renderer Box2DDebugLines
        b2dr.render(world, gamecam.combined);
        
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        teleportation.render(game.batch, teleportation.x, teleportation.y);
        game.batch.end();
        
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        if(pause) {
            game.batch.setProjectionMatrix(pauseScene.stage.getCamera().combined);
            pauseScene.stage.draw();
        }

    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        if(pause) pauseScene.stage.getViewport().update(width, height, true);
    }
    
    public TiledMap getMap() {
        return map;
    }
    
    public World getWorld() {
        return world;
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
        pauseScene.dispose();
    }  
}
