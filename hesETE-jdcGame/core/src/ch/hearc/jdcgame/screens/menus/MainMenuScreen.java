/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.screens.menus;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.ScreenEnum;
import ch.hearc.jdcgame.screens.ScreenManager;
import ch.hearc.jdcgame.tools.Localization;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.Locale;


/**
 *
 * @author Daniel
 */
public class MainMenuScreen extends AbstractMenuScreen {
    
    public MainMenuScreen(JdcGame game) {
        super(game);

        mainElememts();
    }
    
    private void mainElememts() {    
        
        final TextButton playBtn, exitBtn;
        TextButton.TextButtonStyle textButtonStyle = makeButtonStyle(); 
        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/flags.pack"));
        skin.addRegions(buttonAtlas);
                
        ImageButton frBtn = new ImageButton(skin.getDrawable("fr"));
        ImageButton ukBtn = new ImageButton(skin.getDrawable("uk"));
        ImageButton deBtn = new ImageButton(skin.getDrawable("de"));
        ImageButton itBtn = new ImageButton(skin.getDrawable("it"));
        ImageButton ptBtn = new ImageButton(skin.getDrawable("pt"));
        
        playBtn = new TextButton(Localization.START_BTN, textButtonStyle);
        exitBtn = new TextButton(Localization.QUIT_BTN, textButtonStyle);
        
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenEnum.LEVEL_MENU);
            }
        });
        
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });
   
        scrollTable.add(playBtn);
        scrollTable.row();
        scrollTable.add(exitBtn);
 
        Table flagTable = new Table();
        flagTable.pad(10);
        flagTable.defaults().padRight(2f);
        flagTable.setFillParent(true);
        flagTable.right().top();
        flagTable.add().size(16, 16);
        flagTable.add(frBtn).size(16, 16);
        flagTable.add(ukBtn).size(16, 16);
        flagTable.add(deBtn).size(16, 16);
        flagTable.add(itBtn).size(16, 16);
        flagTable.add(ptBtn).size(16, 16);
        
        //Gestion de la localisation et internationalisation
        frBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {        
                
                translateLanguage("fr","FR",playBtn,exitBtn);
            }
        });
        ukBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                translateLanguage("en","GB",playBtn,exitBtn);
            }
        });
        deBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               translateLanguage("de","DE",playBtn,exitBtn);
            }
        });
        itBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                translateLanguage("it","IT",playBtn,exitBtn);
            }
        });
        ptBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                translateLanguage("pt","PT",playBtn,exitBtn);
            }
        });
        
        stage.addActor(flagTable);
    }
    
    private void translateLanguage(String language, String lg, TextButton playBtn, TextButton exitBtn){
        FileHandle baseFileHandle = Gdx.files.internal("../src/ch/hearc/jdcgame/localization/Bundle");
        Locale locale = new Locale(language, lg);
        
        I18NBundle lang = I18NBundle.createBundle(baseFileHandle, locale);
        
        String play = lang.format("button_1");
        String quit = lang.format("button_2");
        String replay = lang.format("button_3");
        String back = lang.format("button_4");
        String levelBtn = lang.format("button_5");
                
        String life = lang.format("label_1");
        String level = lang.format("label_2");
        String time = lang.format("label_3");
                
        String message_1 = lang.format("message_1");
        String message_2 = lang.format("message_2");
                
        //Mise à jour des boutons
        Localization.setStartBtn(play);
        Localization.setQuitBtn(quit);
        Localization.setReplayBtn(replay);
        Localization.setBackBtn(back);
        Localization.setLevelBtn(levelBtn);
        Localization.setLifeLab(life);
        Localization.setLevelLab(level);
        Localization.setTimeLab(time);
        Localization.setMessage(message_1);
        Localization.setMessage2(message_2);
        
        playBtn.setText(play);
        exitBtn.setText(quit);
    }
}
