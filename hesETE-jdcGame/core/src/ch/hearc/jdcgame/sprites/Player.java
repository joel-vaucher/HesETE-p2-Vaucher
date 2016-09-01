/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author joel
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
    
    private int life = 5;
    private float speed = 0.5f;
    
    public Player(PlayScreen screen) {
        super(screen.getSprites().findRegion("Run_sprite"));
        
        AtlasRegion run =screen.getSprites().findRegion("Run_sprite");
        
        this.world = screen.getWorld();
        this.screen = screen;
        
        stateTimerRun = 0;
        
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0;  i < 5; i++){
            frames.add(new TextureRegion(getTexture(), run.getRegionX() + i*SPRITE_WIDTH, run.getRegionY(), SPRITE_WIDTH, SPRITE_HEIGHT));
        }
        runAnimation = new Animation(0.1f, frames);
        frames.clear();
        
        runSprite = new TextureRegion(getTexture(), run.getRegionX(), run.getRegionY(), SPRITE_WIDTH, SPRITE_HEIGHT);
        
        definePlayer();
        setBounds(0, 0, (float)SPRITE_WIDTH / JdcGame.PPM, (float)SPRITE_HEIGHT / JdcGame.PPM);
        setRegion(runSprite);
    }    
    
    public void update(float delta){
        setPosition(b2body.getPosition().x - getWidth()/2,  b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrameRunning(delta));
    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / JdcGame.PPM, 100 / JdcGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setSleepingAllowed(false);
        
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20 / JdcGame.PPM, 20 / JdcGame.PPM);
        
        fdef.filter.categoryBits = JdcGame.PLAYER_BIT;
        fdef.filter.maskBits = JdcGame.GROUND_BIT | JdcGame.WATER_BIT | JdcGame.DOOR_BIT;
        
        fdef.shape = shape;
        b2body.createFixture(fdef);
        
        PolygonShape deadpart = new PolygonShape();
        deadpart.set(new Vector2[]{new Vector2( 20 / JdcGame.PPM, 20 / JdcGame.PPM),
                                    new Vector2( 20 / JdcGame.PPM, -20 / JdcGame.PPM),
                                    new Vector2( -20 / JdcGame.PPM, -20 / JdcGame.PPM),
                                    new Vector2( -20 / JdcGame.PPM, 20 / JdcGame.PPM)});
        fdef.shape = deadpart;
        fdef.isSensor = true;
        
        b2body.createFixture(fdef).setUserData("deadpart");
    }

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
    
    public void loseLife(boolean all){
        life = all ? 0 : life-1;
        if(life == 0){
            screen.endGame(false);
        }
        Gdx.app.log("life", life + "");
    }

    public float getSpeed() {
        return speed;
    }
    
    public void setSpeed(float s) {
        speed = s;
    }
}
