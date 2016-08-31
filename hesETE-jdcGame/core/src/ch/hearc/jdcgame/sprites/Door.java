/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author charlesombangndo
 */
public class Door extends Enemy{

    public Door(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(JdcGame.DOOR_BIT);
    }

    @Override
    protected void defineEnemy() {
       
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

    @Override
    public void onPlayerHit() {
        Gdx.app.log("door", "collision");
        setCategoryFilter(JdcGame.DESTROYED_BIT);
    
    }
    
}
