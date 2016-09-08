package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.scenes.Hud;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Représente le joueur
 */
public class Player extends Sprite {
    
    public World world;
    public Body b2body;
    public PlayScreen screen;
    
    private TextureRegion runSprite;
    private Animation runAnimation;
    private float stateTimerRun;
    
    private final int SPRITE_WIDTH = 40;
    private final int SPRITE_HEIGHT = 50;
    
    private boolean playerISDead;
    
    private int life = 5;
    private float speed = 0.5f;
    
    /**
     * 
     * @param screen 
     */
    public Player(PlayScreen screen) {
        //récuperation de l'image du package et des informations sur la position des frames dans l'image
        super(screen.getSprites().findRegion("Run_sprite"));
        AtlasRegion run =screen.getSprites().findRegion("Run_sprite");
        
        this.world = screen.getWorld();
        this.screen = screen;
        
        stateTimerRun = 0;
        
        //création du tableau de texture à passer en boucle dans l'animation
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0;  i < 5; i++){
            frames.add(new TextureRegion(getTexture(), run.getRegionX() + i*SPRITE_WIDTH, run.getRegionY(), SPRITE_WIDTH, SPRITE_HEIGHT));
        }
        runAnimation = new Animation(0.1f, frames);
        frames.clear();
        
        //image par défaut
        runSprite = new TextureRegion(getTexture(), run.getRegionX(), run.getRegionY(), SPRITE_WIDTH, SPRITE_HEIGHT);
        
        //création du Body du player
        definePlayer();
        //definit l'image par défaut comme image à afficher ainsi que sa taille
        setBounds(0, 0, (float)SPRITE_WIDTH / JdcGame.PPM, (float)SPRITE_HEIGHT / JdcGame.PPM);
        setRegion(runSprite);
    }    
    
    /**
     * 
     * @param delta 
     */
    public void update(float delta){
        //adapte la position de l'image à la position du Body (modifier par la teleportation et autre)
        setPosition(b2body.getPosition().x - getWidth()/2,  b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrameRunning(delta));
    }

    /**
     * 
     */
    public void definePlayer() {
        //defini la position de la zone de collision ainsi que son type
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / JdcGame.PPM, 100 / JdcGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        //ajoute le Body dans le monde et l'empêche de pouvoir entrer en veille
        b2body = world.createBody(bdef);
        b2body.setSleepingAllowed(false);
        
        //définit la taille/forme de la zone de collision
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20 / JdcGame.PPM, 20 / JdcGame.PPM);
        
        //défini le bit (type d'objet/calque) de player ainsi que les bits avec lesquelles il peut entrer en collision
        fdef.filter.categoryBits = JdcGame.PLAYER_BIT;
        //Elements de collision
        fdef.filter.maskBits = JdcGame.GROUND_BIT | JdcGame.WATER_BIT | JdcGame.DOOR_BIT | JdcGame.FLAGEND_BIT | JdcGame.STAR_BIT;
        
        fdef.shape = shape;
        b2body.createFixture(fdef);
        
        //définition d'un senseur
        //zone n'apportant pas de collision mais lançant les evenements de collision
        //la zone est exactement celle du joueur, mais il pourrait en être autrement
        PolygonShape deadpart = new PolygonShape();
        //typical square
        deadpart.set(new Vector2[]{new Vector2( 20 / JdcGame.PPM, 20 / JdcGame.PPM),
                                    new Vector2( 20 / JdcGame.PPM, -20 / JdcGame.PPM),
                                    new Vector2( -20 / JdcGame.PPM, -20 / JdcGame.PPM),
                                    new Vector2( -20 / JdcGame.PPM, 20 / JdcGame.PPM)});
        fdef.shape = deadpart;
        fdef.isSensor = true;
        
        b2body.createFixture(fdef).setUserData("deadpart");
    }

    /**
     * 
     * met à jour stateTimerRun qui est le temps dans la boucle d'animation de la course
     * et retourne l'image correspondant à ce temps dans la boucle.
     * @param delta
     * @return 
     */
    private TextureRegion getFrameRunning(float delta) {
        TextureRegion pic = runAnimation.getKeyFrame(stateTimerRun%runAnimation.getAnimationDuration());
        stateTimerRun += delta;
        if(world.getGravity().y >= 0 && !pic.isFlipY()){
            pic.flip(false, true);
        }
        if(world.getGravity().y <= 0 && pic.isFlipY()){
            pic.flip(false, true);
        }
        return pic;
    }
    
    //public void loseLife(boolean all){
        //life = all ? 0 : life-1;
        //if(life == 0){
        //    screen.endGame(false);
        //}
        //Gdx.app.log("life", life + "");
    //}
    
    /**
     * 
     * change les vie du joueur à value
     * @param value 
     */
    public void manIsDead(int value){         
        if(Hud.updateHealth(value) <= 0){
            screen.endGame(false);
        }   
    }
    public void KeyCollision(){
        
    }

    /**
     * 
     * @return 
     */
    public float getSpeed() {
        return speed;
    }
    
    /**
     * 
     * @param s 
     */
    public void setSpeed(float s) {
        speed = s;
    }
}
