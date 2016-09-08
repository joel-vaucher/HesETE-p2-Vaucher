package ch.hearc.jdcgame.screens;

import ch.hearc.jdcgame.JdcGame;
import com.badlogic.gdx.Screen;

/**
 *  ScreenManager permet de changer de screen.
 *  Une seule instance de cette classe peut exister.
 */
public class ScreenManager {
    
    // Singleton : instance unique
    private static ScreenManager instance;
    private JdcGame game;
    
    /**
     * Constructeur privé
     */
    private ScreenManager() {
        super();
    }
    
    /**
     * Singleton
     * @return instance
     */
    public static ScreenManager getInstance() {
        if(instance == null)
            instance = new ScreenManager();
        return instance;
    }
    
    /**
     * Initialize la propriété game
     * @param game 
     */
    public void initialize(JdcGame game) {
        this.game = game;
    }
    
    /**
     * Afficher le screen souhaité
     * @param screenEnum
     */
    public void showScreen(ScreenEnum screenEnum) {
        Screen currentScreen = game.getScreen();
        // Obtention et affectation du nouveau screen
        Screen newScreen = screenEnum.getScreen(game);
        game.setScreen(newScreen);     
        // Suppression de l'ancien screen si nécessaire
        if(currentScreen != null) {
            currentScreen.dispose();
        }
    }
    
    /**
     * Affiche l'interface de jeu avec le niveau souhaité
     * @param screenEnum : interface de jeu
     * @param levelFileName : fichier du niveau souhaité
     */
    public void showPlayScreen(ScreenEnum screenEnum, String levelFileName) {
        Screen currentScreen = game.getScreen();
        // Obtention et affectation du nouveau jeu
        Screen newScreen = screenEnum.PLAY_SCREEN.getPlayScreen(game, levelFileName);
        game.setScreen(newScreen);
        // Suppression de l'ancien screen si nécessaire  
        if(currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
