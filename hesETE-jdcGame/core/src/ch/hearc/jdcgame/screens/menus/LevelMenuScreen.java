/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.screens.menus;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.ScreenEnum;
import ch.hearc.jdcgame.screens.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Daniel
 */
public class LevelMenuScreen extends AbstractMenuScreen {
    
    public LevelMenuScreen(JdcGame game) {
        super(game);
                          
        TextButton levelBtn, returnBtn;
        TextButton.TextButtonStyle textButtonStyle = makeButtonStyle();
        
        for(int i=1; i<4; i++) {      
            levelBtn = new TextButton("Niveau " + i, textButtonStyle);
            levelBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ScreenManager.getInstance().showScreen(ScreenEnum.PLAY_SCREEN);
                }
            });
            buttonsTable.add(levelBtn);
            buttonsTable.row();
        }
        
        returnBtn = new TextButton("Retour", textButtonStyle);
        returnBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
        buttonsTable.add(returnBtn);
    }
    
}
