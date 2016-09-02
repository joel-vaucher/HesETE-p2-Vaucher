package ch.hearc.jdcgame.screens;

import ch.hearc.jdcgame.JdcGame;
import com.badlogic.gdx.Screen;

/**
 *
 * @author Daniel
 */
public class ScreenManager {
    
    // Singleton : instance unique
    private static ScreenManager instance;
    private JdcGame game;
    
    private ScreenManager() {
        super();
    }
    
    // Singleton : retourner l'instance
    public static ScreenManager getInstance() {
        if(instance == null)
            instance = new ScreenManager();
        return instance;
    }
    
    public void initialize(JdcGame game) {
        this.game = game;
    }
    
    public void showScreen(ScreenEnum screenEnum) {
        Screen currentScreen = game.getScreen();
        Screen newScreen = screenEnum.getScreen(game);
        game.setScreen(newScreen);
        
        if(currentScreen != null) {
            currentScreen.dispose();
        }
    }
    
    public void showPlayScreen(ScreenEnum screenEnum, String levelFileName) {
        Screen currentScreen = game.getScreen();
        Screen newScreen = screenEnum.PLAY_SCREEN.getPlayScreen(game, levelFileName);
        game.setScreen(newScreen);
        
        if(currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
