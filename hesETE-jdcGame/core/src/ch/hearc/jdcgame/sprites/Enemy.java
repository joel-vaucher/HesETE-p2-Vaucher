package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public abstract class Enemy{
    
    protected Fixture fixture;
    
    protected World world;
    protected TiledMap map;
    protected PlayScreen screen;
    protected Rectangle bounds;
    public Body body;
    
    /**
     * 
     * @param screen
     * @param bounds 
     */
    public Enemy(PlayScreen screen, Rectangle bounds){
        
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;
        this.screen = screen;
        
        defineEnemy();
    }
    
    /**
     * 
     */
    protected abstract void defineEnemy();
    
    /**
     * 
     */
    public abstract void onPlayerHit();
    
    /**
     * 
     */
    protected void defaultDefineEnemy() {
       
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / JdcGame.PPM, (bounds.getY() + bounds.getHeight() / 2) / JdcGame.PPM);
            
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth() / 2 / JdcGame.PPM, bounds.getHeight() / 2 / JdcGame.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }
    
    /**
     * 
     * @param filterBit 
     */
    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}
