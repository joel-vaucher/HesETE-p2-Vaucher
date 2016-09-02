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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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
        
        ImageButton frBtn = new ImageButton(skin.getDrawable("fr"));
        ImageButton ukBtn = new ImageButton(skin.getDrawable("uk"));
        ImageButton deBtn = new ImageButton(skin.getDrawable("de"));
        ImageButton itBtn = new ImageButton(skin.getDrawable("it"));
        ImageButton ptBtn = new ImageButton(skin.getDrawable("pt"));
        
        frBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("fr","");
            }
        });
        ukBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("en","");
            }
        });
        deBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("de","");
            }
        });
        itBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("it","");
            }
        });
        ptBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("pt","");
            }
        });
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
        
        stage.addActor(flagTable);
    }
}
