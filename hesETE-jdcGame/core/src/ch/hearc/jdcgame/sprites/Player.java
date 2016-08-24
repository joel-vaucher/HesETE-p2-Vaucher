/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author joel
 */
public class Player extends Sprite {
    
    public World world;
    public Body b2body;
    
    public Player(World world) {
        this.world = world;
        definePlayer();
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
        
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
    
}
