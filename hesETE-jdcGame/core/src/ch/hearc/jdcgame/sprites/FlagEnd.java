package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author joel
 */
public class FlagEnd extends InteractiveTileObject{
    public FlagEnd(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(JdcGame.FLAGEND_BIT);
    }
}
