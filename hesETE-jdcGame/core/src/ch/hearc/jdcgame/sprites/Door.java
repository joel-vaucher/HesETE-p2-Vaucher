/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.scenes.Hud;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.math.Rectangle;

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
        defaultDefineEnemy();
    }

    @Override
    public void onPlayerHit() {
        //Gdx.app.log("door", "collision");
        setCategoryFilter(JdcGame.DESTROYED_BIT);
<<<<<<< HEAD
        screen.getPlayer().manIsDead();
        //Hud.updateHealth(1);
=======
        screen.getPlayer().loseLife(false);
        screen.getPlayer().setSpeed(0);
>>>>>>> origin/master
    }
    
}
