package ch.hearc.jdcgame.sprites;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.math.Rectangle;

/**
 * Classe représentant les portes présentes dans le jeu
 */
public class Door extends Enemy{

    /**
     * Création de la porte
     * @param screen
     * @param bounds : emplacement de la porte dans la carte du jeu 
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
