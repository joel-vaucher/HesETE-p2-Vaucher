package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author joel
 */
public class Teleportation {
    
    public float x;
    public float y;
    
    private TextureRegion tpSprite;
    private Animation tpAnimation;
    private float stateTimerTp;
    
    private final int SPRITE_WIDTH = 40;
    private final int SPRITE_HEIGHT = 50;
    
    public Teleportation(PlayScreen screen) {
        
        TextureAtlas.AtlasRegion tp = screen.getSprites().findRegion("Teleportation_sprite");
        
        stateTimerTp = 0;
        x = 0;
        y = 0;
        
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0;  i < 8; i++){
            frames.add(new TextureRegion(tp.getTexture(), tp.getRegionX() + i*SPRITE_WIDTH, tp.getRegionY(), SPRITE_WIDTH, SPRITE_HEIGHT));
        }
        tpAnimation = new Animation(0.1f, frames);
        frames.clear();
        
        tpSprite = new TextureRegion(tp.getTexture(), tp.getRegionX(), tp.getRegionY(), SPRITE_WIDTH, SPRITE_HEIGHT);
    }
    
    public void changePosition(float x, float y){
        //System.out.println(x + " " + y);
        this.x = x;
        this.y = y;
        stateTimerTp = 0;
    }
    
    public void update(float delta){
        tpSprite = getFrameTeleporting(delta);
    }
    
    public void render(SpriteBatch batch, float x, float y){
        batch.draw(tpSprite, x - SPRITE_WIDTH / 2 / JdcGame.PPM, y - SPRITE_HEIGHT / 2 / JdcGame.PPM, SPRITE_WIDTH / JdcGame.PPM, SPRITE_HEIGHT / JdcGame.PPM);
    }

    private TextureRegion getFrameTeleporting(float delta) {
        TextureRegion pic = tpAnimation.getKeyFrame(stateTimerTp);
        stateTimerTp += delta;
        return pic;
    }
}
