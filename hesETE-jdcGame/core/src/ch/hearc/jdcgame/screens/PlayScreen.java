package ch.hearc.jdcgame.screens;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.scenes.GaugeHud;
import ch.hearc.jdcgame.scenes.Hud;
import ch.hearc.jdcgame.scenes.OtherScene;
import ch.hearc.jdcgame.sprites.Player;
import ch.hearc.jdcgame.sprites.Teleportation;
import ch.hearc.jdcgame.tools.B2dWorldCreator;
import ch.hearc.jdcgame.tools.Localization;
import ch.hearc.jdcgame.tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Ecran de jeu
 */
public class PlayScreen implements Screen{

    private JdcGame game;
    private TextureAtlas sprite;
    
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;
    private GaugeHud gHud;
    
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
    
    private boolean endGame;
    private Texture endScreen;
    
    //bloque le mouvement horizontale de la camera et du joueur
    private boolean debugMoving = false;
      
    private float maxSpeed;
    private float timeToReloadSpeed;
    private float reloadSpeed;
    private float deltaSpeed;
    
    //Music
    public Music music;
    
    private boolean pause;
    private OtherScene scene;
    
    /**
     * Constructeur sans précision du niveau
     *  - Cahrgement du niveau stocké
     *  - Utilisé pour recommencer le jeu
     * @param game 
     */
    public PlayScreen(JdcGame game) {
        this(game, levelFileName);
    }
    
    /**
     * Constructeur avec niveau
     *  - Utilisée pour commencer une nouvelle partie
     * @param game
     * @param levelFileName 
     */
    public PlayScreen(JdcGame game, String levelFileName){
        //récupération du niveau
        this.levelFileName = levelFileName;
        FileHandle file = new FileHandle(levelFileName);         
        Hud.setLevelNumber(file.nameWithoutExtension());
        
        //récupération des images de sprites
        sprite = new TextureAtlas("sprites/sprite.pack");
        
        //création de la camera/fenetre
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(JdcGame.V_WIDTH / JdcGame.PPM, JdcGame.V_HEIGHT / JdcGame.PPM, gamecam);
        hud = new Hud(game.batch);
        gHud = new GaugeHud(game.batch);
        
        //Loader
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(levelFileName);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / JdcGame.PPM);
        widthmap = map.getProperties().get("width", Integer.class) *
                   map.getProperties().get("tilewidth", Integer.class);
        
        
        //initialisation
        gamecam.position.set(gameport.getWorldWidth()/ 2, gameport.getWorldHeight()/ 2, 0);
    
        pause = false;
        
        gravity = -9;
        
        world = new World(new Vector2(0, gravity), true);
        b2dr = new Box2DDebugRenderer();
        
        //création des Body du terrain (sol, mur, eau, piece, ...)
        new B2dWorldCreator(this);
        
        player = new Player(this);
        teleportation = new Teleportation(this);
        TeleportReady = true;
        timeToReloadTeleport = 1;
        reloadTeleport = 0;
        
        GravityReady = true;
        timeToReloadGravity = 2;
        reloadGravity = 0;
        
        maxSpeed = 0.5f;
        timeToReloadSpeed = 1;
        reloadSpeed = 0;
        deltaSpeed = 0.05f;
        
        endGame = false;
        endScreen = null;
        
        //Son du jeu
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

    /**
     * Evenements input
     * appeler depuis la fonction update(float delta)
     * effectue certain code en fonction des touches pressées
     * @param delta 
     */
    public void handleInput(float delta){
        
        if(Gdx.input.isTouched() && TeleportReady && !pause) {
            //TELEPORTATION
            //enlève la partie noir de l'écran (gutter) de la valeur
            float gamePosX = Gdx.input.getX() - gameport.getLeftGutterWidth();
            float gamePosY = Gdx.input.getY() - gameport.getTopGutterHeight();
            //change la proportion de l'écran (en pixel) à la logique du jeu (mètre) ainsi que l'ajout du décalage générer par la position de la caméra dans le monde
            float logicPosX =  (gamecam.position.x - gameport.getWorldWidth()/ 2) + (gameport.getWorldWidth()/gameport.getScreenWidth()) * gamePosX ;
            float logicPosY =  gameport.getWorldHeight() - (gameport.getWorldHeight()/gameport.getScreenHeight()) * gamePosY;
            //change la position du joueur
            player.b2body.setTransform(logicPosX,logicPosY, 0);
            //affiche la zone bleu à l'endroit de la téléportation
            teleportation.changePosition(logicPosX, logicPosY);
            TeleportReady = false;
            //produit le son "ppuuiiiouuu"
            JdcGame.manager.get("audio/sounds/teletransportation.mp3", Sound.class).play();
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.SPACE) && GravityReady && !pause) {
            //CHANGEMENT DE GRAVITE
            gravity *= -1;
            world.setGravity(new Vector2(0, gravity));
            GravityReady = false;
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            //PAUSE
            pause = !pause;
            if(pause) {
                //music.pause();
                scene =  new OtherScene(game.batch, Localization.MESSAGE_2);
            } else {
                //music.play();
                scene.dispose();
            }
        }
    }
    
    /**
     * Fonction qui fait "bouger" le jeu en effectuant les déplacements continue
     * cette fonction est appeller depuis render(float delta)
     * @param delta 
     */
    public void update(float delta){
        if(!endGame) {
            handleInput(delta);
            if(!pause) {                
                world.step(1/60f, 6, 2);
                if(!debugMoving) {
                    if(gamecam.position.x + gameport.getWorldWidth()/ 2 < (float)widthmap / JdcGame.PPM)
                    gamecam.position.x += delta;
                    if(player.b2body.getLinearVelocity().x <= 1f){
                        if(player.getSpeed() < maxSpeed) {                              
                            if(reloadSpeed >= timeToReloadSpeed){
                                player.setSpeed(player.getSpeed() + deltaSpeed);
                                reloadSpeed = 0;
                            }                          
                            reloadSpeed += delta;
                        }else
                            reloadSpeed = 0;
                        
                        player.b2body.applyLinearImpulse(new Vector2(player.getSpeed(), 0),player.b2body.getWorldCenter(), true);
                    }
                }
                if(!TeleportReady){
                    teleportation.update(delta);
                    float percent = Interpolation.linear.apply(reloadTeleport/timeToReloadTeleport, timeToReloadTeleport/timeToReloadTeleport, 0.1f);
                    reloadTeleport += delta;
                    gHud.updateTelepartionGauge(percent);
                    if(reloadTeleport >= timeToReloadTeleport){
                        TeleportReady = true;
                        reloadTeleport = 0;
                        gHud.fullGaugeTeleportation();
                    }
                }
                if(!GravityReady){
                    float percent = Interpolation.linear.apply(reloadGravity/timeToReloadGravity, timeToReloadGravity/timeToReloadGravity, 0.1f);
                    reloadGravity += delta;
                    gHud.updateGravityGauge(percent);
                    if(reloadGravity >= timeToReloadGravity){
                        GravityReady = true;
                        reloadGravity = 0;
                        gHud.fullGaugeGravity();
                    }
                }  
                Vector2 posPlay = player.b2body.getPosition();
                Vector3 posCamUp = new Vector3(gamecam.position).sub(gameport.getWorldWidth()/ 2, gameport.getWorldHeight()/ 2, 0);
                Vector3 posCamDown = new Vector3(gamecam.position).add(gameport.getWorldWidth()/ 2, gameport.getWorldHeight()/ 2, 0);
                
                if((posPlay.x < posCamUp.x || posCamDown.x < posPlay.x) || (posPlay.y < posCamUp.y || posCamDown.y < posPlay.y)) {
                    player.manIsDead(5);
                }
                player.update(delta);
                //hud.update(delta);
                //Mise a jour de la position de la camera suivant les nouvelles coordonnées
                gamecam.update();
                renderer.setView(gamecam);
            }
        }
    }
    /**
     * Cette fonction va afficher les images sur la camera en fonction de leur position dans le monde
     * @param delta 
     */
    @Override
    public void render(float delta) {
        update(delta);
        
        //nettoyage de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        //affichage du background et des pourtours des Body
        //b2dr.render(world, gamecam.combined);
        
        //affichage  du joueur et de la teleportation (si besoin)
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        teleportation.render(game.batch, teleportation.x, teleportation.y);
        //si la partie est fini, affiche l'écran de fin
        if(endGame) {
            game.batch.draw(endScreen, (gamecam.position.x - gameport.getWorldWidth()/ 2), 0, JdcGame.V_WIDTH / JdcGame.PPM, JdcGame.V_HEIGHT / JdcGame.PPM);
            
            if(Gdx.input.isTouched() && !pause) {
                pause = true;
                scene =  new OtherScene(game.batch, "Game Over");            
            }
        }
        game.batch.end();
        
        //affiche HUD
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.setProjectionMatrix(gHud.stage.getCamera().combined);
        gHud.stage.draw();
        
        if(pause) {
            music.stop();
            game.batch.setProjectionMatrix(scene.stage.getCamera().combined);
            scene.stage.draw();
        }

    }
    
    /**
     * Fin du jeu --> affiche le message de victoire ou de perte
     * @param victory 
     */
    public void endGame(boolean victory){
        endGame = true;
        
        int life = Hud.getHealth();
        if(victory && life ==5){
            endScreen = new Texture("others/perfect.png");
            music.stop();
            JdcGame.manager.get("audio/sounds/winson.mp3", Sound.class).play();
        } else if(victory){
            endScreen = new Texture("others/Victory.png");
            music.stop();
            JdcGame.manager.get("audio/sounds/winson.mp3", Sound.class).play();
        }else {
            endScreen = new Texture("others/GameOver.png");
            music.stop();
            JdcGame.manager.get("audio/sounds/youloose.mp3", Sound.class).play();
            //JdcGame.manager.get("audio/sounds/gameOver.mp3", Sound.class).play();
        }
    }
    

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        if(pause) scene.stage.getViewport().update(width, height, true);
    }

    public TiledMap getMap() {
        return map;
    }
    
    public World getWorld() {
        return world;
    }
    
    public Player getPlayer() {
        return player;
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
    
        // Libération ressources
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        scene.dispose();
    }  
}
