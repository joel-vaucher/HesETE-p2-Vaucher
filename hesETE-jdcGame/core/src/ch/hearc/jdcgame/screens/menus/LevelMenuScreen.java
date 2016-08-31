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
import com.badlogic.gdx.files.FileHandle;
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
        
        FileHandle[] files = Gdx.files.internal("levels/").list();
        int i = 1;
        for(final FileHandle file: files) {
            if(file.extension().equals("tmx")) {
                levelBtn = new TextButton("Niveau" + i, textButtonStyle);
                levelBtn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        ScreenManager.getInstance().showPlayScreen(ScreenEnum.PLAY_SCREEN, file.path());
                    }
                });
                buttonsTable.add(levelBtn);
                buttonsTable.row();
                i++;
            }
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
