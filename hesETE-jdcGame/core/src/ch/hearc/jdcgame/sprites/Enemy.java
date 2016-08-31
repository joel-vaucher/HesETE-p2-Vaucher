/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
 * @author joel
 */
public abstract class Enemy{
    
    protected Fixture fixture;
    
    protected World world;
    protected TiledMap map;
    protected PlayScreen screen;
    protected Rectangle bounds;
    public Body body;
    
    public Enemy(PlayScreen screen, Rectangle bounds){
        
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;
        
        defineEnemy();
    }
    
    protected abstract void defineEnemy();
    
    public abstract void onPlayerHit();
    
    
    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}
