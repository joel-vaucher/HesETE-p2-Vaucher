package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author charlesombangndo
 */
public class Water extends Enemy{
    public Water(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(JdcGame.WATER_BIT);
    }

    @Override
    protected void defineEnemy() {
        defaultDefineEnemy();
    }

    @Override
    public void onPlayerHit() {
        setCategoryFilter(JdcGame.DESTROYED_BIT);
        screen.getPlayer().manIsDead(5);
    }
}
