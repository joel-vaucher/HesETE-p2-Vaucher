/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.tools;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.sprites.Door;
import ch.hearc.jdcgame.sprites.Water;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author charlesombangndo
 */
public class B2dWorldCreator {
    
    public B2dWorldCreator(World world, TiledMap map){
        
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        //create wall bodies/fixture
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            new Door(world, map, rect);
        }
        
        //create ground bodies/fixture
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / JdcGame.PPM, (rect.getY() + rect.getHeight() / 2) / JdcGame.PPM);
            
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / JdcGame.PPM, rect.getHeight() / 2 / JdcGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        
        //Water
        //create ground bodies/fixture
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Water(world, map, rect);
        }
    }
}
