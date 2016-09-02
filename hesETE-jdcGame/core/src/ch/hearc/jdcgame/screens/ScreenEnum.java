package ch.hearc.jdcgame.screens;

import ch.hearc.jdcgame.screens.menus.MainMenuScreen;
import ch.hearc.jdcgame.screens.menus.LevelMenuScreen;
import ch.hearc.jdcgame.JdcGame;
import com.badlogic.gdx.Screen;

/**
 *
 * @author Daniel
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
    },
    
    SETTING_MENU {
        @Override
        public Screen getScreen(JdcGame game) {
            return new SettingMenuScreen(game);
        }
    };
    
    public abstract Screen getScreen(JdcGame game);

    public Screen getPlayScreen(JdcGame game, String levelFileName) {
        return new PlayScreen(game, levelFileName);
    }
}
