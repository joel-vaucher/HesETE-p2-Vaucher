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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 *
 * @author Daniel
 */
public class MainMenuScreen extends AbstractMenuScreen {
    
    public MainMenuScreen(JdcGame game) {
        super(game);

        makeFlagsBtn();
        
        TextButton playBtn, exitBtn, settingBtn;
        TextButton.TextButtonStyle textButtonStyle = makeButtonStyle();      
                
        playBtn = new TextButton("Jouer", textButtonStyle);
        exitBtn = new TextButton("Quitter", textButtonStyle);
        
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
    }
    
    private void makeFlagsBtn() {          
        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/flags.pack"));
        skin.addRegions(buttonAtlas);
        
        Table flagTable = new Table();
        flagTable.pad(10);
        flagTable.defaults().padRight(2f);
        flagTable.setFillParent(true);
        flagTable.right().top();
        flagTable.add(new ImageButton(skin.getDrawable("uk"))).size(16, 16);
        flagTable.add(new ImageButton(skin.getDrawable("fr"))).size(16, 16);
        flagTable.add(new ImageButton(skin.getDrawable("de"))).size(16, 16);
        flagTable.add(new ImageButton(skin.getDrawable("it"))).size(16, 16);
        flagTable.add(new ImageButton(skin.getDrawable("pt"))).size(16, 16);
        
        stage.addActor(flagTable);
    }
}
