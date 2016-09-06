/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 */
public class Key extends InteractiveTileObject{
    /**
     * 
     * @param screen
     * @param bounds 
     */
    public Key(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(JdcGame.KEY_BIT);
    }

    @Override
    public void onPlayerHit() {
        System.out.println("touche");
        
    }
    
}
