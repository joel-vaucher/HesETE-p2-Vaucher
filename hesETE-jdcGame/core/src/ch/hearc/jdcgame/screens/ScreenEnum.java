/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.screens;

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
            return new PlayScreen((JdcGame) game);
        }
    };
    
    public abstract Screen getScreen(JdcGame game);
}
