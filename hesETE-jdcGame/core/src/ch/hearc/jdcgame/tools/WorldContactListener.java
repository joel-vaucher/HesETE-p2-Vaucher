/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.tools;

import ch.hearc.jdcgame.sprites.Enemy;
import ch.hearc.jdcgame.sprites.InteractiveTileObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 *
 * @author charlesombangndo
 */
public class WorldContactListener implements ContactListener{

    @Override
    public void beginContact(Contact contact) {
        //Gdx.app.log("Début contact","");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        
        if(fixA.getUserData() == "deadpart" || fixB.getUserData() == "deadpart") {
            Fixture player = fixA.getUserData() == "deadpart" ? fixA : fixB;
            Fixture object = player == fixA ? fixB : fixA;
            //Gdx.app.log("deadpart", object.getUserData().getClass().getSimpleName());
        
            if(object.getUserData() != null && Enemy.class.isAssignableFrom(object.getUserData().getClass())) {
                ((Enemy) object.getUserData()).onPlayerHit();
            }
            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onPlayerHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        //Gdx.app.log("Fin contact","");
    }

    @Override
    public void preSolve(Contact cntct, Manifold mnfld) {
    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {
    }
    
}
