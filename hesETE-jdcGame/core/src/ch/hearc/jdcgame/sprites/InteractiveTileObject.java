/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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
 * @author charlesombangndo
 */
public abstract class InteractiveTileObject {
    
    protected Fixture fixture;
    
    protected TiledMap map;
    protected World world;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    
    public InteractiveTileObject(PlayScreen screen, Rectangle bounds){
        
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;
        
        //
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
    
    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * JdcGame.PPM / 16),
                (int)(body.getPosition().y * JdcGame.PPM / 16));
    }
}
