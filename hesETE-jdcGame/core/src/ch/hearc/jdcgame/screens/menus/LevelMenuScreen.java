package ch.hearc.jdcgame.screens.menus;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.ScreenEnum;
import ch.hearc.jdcgame.screens.ScreenManager;
import ch.hearc.jdcgame.tools.Localization;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
        for(final FileHandle file: files) {
            
            if(file.extension().equals("tmx")) {
                levelBtn = new TextButton(Localization.LEVEL_BTN + " " + file.nameWithoutExtension(), textButtonStyle);
                levelBtn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        JdcGame.manager.get("audio/sounds/click.mp3", Sound.class).play();
                        music.stop();
                        ScreenManager.getInstance().showPlayScreen(ScreenEnum.PLAY_SCREEN, file.path());
                        
                    }
                });
                
                levelBtn.addListener(new ClickListener() {
                @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        JdcGame.manager.get("audio/sounds/buttonson.mp3", Sound.class).play();
                    }
                });
                buttonsTable.add(levelBtn);
                buttonsTable.row();
                scrollTable.add(levelBtn);
                scrollTable.row();
            }
        }
        
        returnBtn = new TextButton(Localization.BACK_BTN, textButtonStyle);
        returnBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JdcGame.manager.get("audio/sounds/click.mp3", Sound.class).play();
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
        
        returnBtn.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                JdcGame.manager.get("audio/sounds/buttonson.mp3", Sound.class).play();
            }
        });
        
        buttonsTable.row();
        buttonsTable.add(returnBtn);
    }
    
}
