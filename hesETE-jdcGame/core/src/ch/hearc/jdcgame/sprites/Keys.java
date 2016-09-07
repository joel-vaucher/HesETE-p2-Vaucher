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
public class Keys extends InteractiveTileObject{

    public Keys(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(JdcGame.STAR_BIT);
    }

    @Override
    public void onPlayerHit() {
        setCategoryFilter(JdcGame.DESTROYED_BIT);
        getCell(0,0).setTile(null);
        getCell(-1,0).setTile(null);
        getCell(-1,-1).setTile(null);
        getCell(0,-1).setTile(null);
        Hud.addScore(100);
    }
    
    
}
