package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 */
public class Ground extends InteractiveTileObject{
    /**
     * 
     * @param screen
     * @param bounds 
     */
    public Ground(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(JdcGame.GROUND_BIT);
    }

    @Override
    public void onPlayerHit() {
        //rien
    }
}
