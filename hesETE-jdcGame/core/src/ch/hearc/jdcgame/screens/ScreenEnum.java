package ch.hearc.jdcgame.screens;

import ch.hearc.jdcgame.screens.menus.MainMenuScreen;
import ch.hearc.jdcgame.screens.menus.LevelMenuScreen;
import ch.hearc.jdcgame.JdcGame;
import com.badlogic.gdx.Screen;

/**
 * Enumération utilisée par ScreenManager
 */
public enum ScreenEnum {
    MAIN_MENU {
        @Override
        public Screen getScreen(JdcGame game) {
            return new MainMenuScreen(game);
        }
    },
    PLAY_SCREEN {
        @Override
        public Screen getScreen(JdcGame game) {
            return new PlayScreen(game);
        }
    },
    LEVEL_MENU {
        @Override
        public Screen getScreen(JdcGame game) {
            return new LevelMenuScreen(game);
        }
    };
    
    /**
     * Permet de renvoier le screen souhaité
     * @param game
     * @return Nouveau Screen
     */
    public abstract Screen getScreen(JdcGame game);

    /**
     * Permet de renvoier l'interface de jeu en indiquant le niveau
     * @param game
     * @param levelFileName : Chemin du fichier du niveau
     * @return Nouveau PlayScreen
     */
    public Screen getPlayScreen(JdcGame game, String levelFileName) {
        return new PlayScreen(game, levelFileName);
    }
}
