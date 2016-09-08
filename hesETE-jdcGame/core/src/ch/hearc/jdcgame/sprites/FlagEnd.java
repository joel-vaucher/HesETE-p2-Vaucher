package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.math.Rectangle;

/**
 * Classe représentant le drapeau de fin de niveau
 */
public class FlagEnd extends InteractiveTileObject{
    /**
     * Création du drapeau
     * @param screen
     * @param bounds 
     */
    public FlagEnd(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(JdcGame.FLAGEND_BIT);
    }

    @Override
    public void onPlayerHit() {
        screen.endGame(true);
    }
}
