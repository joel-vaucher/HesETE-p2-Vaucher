package ch.hearc.jdcgame.tools;

import ch.hearc.jdcgame.screens.PlayScreen;
import ch.hearc.jdcgame.sprites.Door;
import ch.hearc.jdcgame.sprites.Key;
import ch.hearc.jdcgame.sprites.FlagEnd;
import ch.hearc.jdcgame.sprites.Ground;
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
    
    public B2dWorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        //create wall bodies/fixture
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(screen, rect);
        }
        
        //create ground bodies/fixture
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Ground(screen, rect);
        }
        
        //Water
        //create ground bodies/fixture
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Water(screen, rect);
        }
        
        //flag end
        //create bodies/fixture
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new FlagEnd(screen, rect);
        }
        
        //Key
        //create bodies/fixture
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Key(screen, rect);
        }
    }
}
