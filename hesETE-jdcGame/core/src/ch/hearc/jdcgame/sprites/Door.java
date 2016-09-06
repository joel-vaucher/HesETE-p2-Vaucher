package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 */
public class Door extends Enemy{

    /**
     * 
     * @param screen
     * @param bounds 
     */
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
        setCategoryFilter(JdcGame.DESTROYED_BIT);
        screen.getPlayer().manIsDead(1);
        screen.getPlayer().setSpeed(0);
    }
    
}
