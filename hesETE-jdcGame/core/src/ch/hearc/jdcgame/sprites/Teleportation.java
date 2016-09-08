package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Animation de la téléportation
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
    
    /**
     * Changement de position de l'animation
     * @param x
     * @param y 
     */
    public void changePosition(float x, float y){
        //System.out.println(x + " " + y);
        this.x = x;
        this.y = y;
        stateTimerTp = 0;
    }
    
    /**
     * Mise à jour de l'animation
     * @param delta 
     */
    public void update(float delta){
        tpSprite = getFrameTeleporting(delta);
    }
    
    /**
     * Rendu de l'animation
     * @param batch
     * @param x
     * @param y 
     */
    public void render(SpriteBatch batch, float x, float y){
        batch.draw(tpSprite, x - SPRITE_WIDTH / 2 / JdcGame.PPM, y - SPRITE_HEIGHT / 2 / JdcGame.PPM, SPRITE_WIDTH / JdcGame.PPM, SPRITE_HEIGHT / JdcGame.PPM);
    }

    /**
     * Met à jour stateTimerTP qui est le temps dans la boucle d'animation de la téléportation
     * et retourne l'image correspondant à ce temps dans la boucle.
     * @param delta
     * @return 
     */
    private TextureRegion getFrameTeleporting(float delta) {
        TextureRegion pic = tpAnimation.getKeyFrame(stateTimerTp);
        stateTimerTp += delta;
        return pic;
    }
}
