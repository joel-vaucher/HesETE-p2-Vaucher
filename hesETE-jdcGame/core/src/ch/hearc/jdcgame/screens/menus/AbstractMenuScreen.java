/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.screens.menus;

import ch.hearc.jdcgame.JdcGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Daniel
 */
public abstract class AbstractMenuScreen implements Screen{
    
    protected Viewport viewport;
    protected Stage stage;
    protected JdcGame game;
    protected Table buttonsTable;
         
    
    public AbstractMenuScreen(JdcGame game) {
        this.game = game;
        viewport = new FitViewport(JdcGame.V_WIDTH, JdcGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((JdcGame) game).batch);
        Gdx.input.setInputProcessor(stage);
        
        buttonsTable = new Table();
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        
        Image logo = new Image(new Texture(Gdx.files.internal("logo.png")));
        
        buttonsTable.defaults().expandX().center();
        buttonsTable.defaults().expandY().center();
        buttonsTable.columnDefaults(0).right();
        mainTable.defaults().pad(50f);
        mainTable.defaults().expandX().center();
        mainTable.defaults().expandY().center();
        mainTable.columnDefaults(0).align(Align.left);
        mainTable.columnDefaults(1).align(Align.right);
        mainTable.add(logo);
        mainTable.add(buttonsTable);
        
        stage.addActor(mainTable);    
    }
    
    protected TextButton.TextButtonStyle makeButtonStyle () {
        TextButton.TextButtonStyle textButtonStyle;
        BitmapFont font;
        Skin skin;
        TextureAtlas buttonAtlas; 
        
        font = JdcGame.FONT;
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.overFontColor = Color.valueOf("49b47e");
        textButtonStyle.downFontColor = Color.valueOf("307553");
        textButtonStyle.up = skin.getDrawable("up_button");
        textButtonStyle.over = skin.getDrawable("over_button");
        textButtonStyle.down = skin.getDrawable("down_button");
        textButtonStyle.checked = skin.getDrawable("checked_button");
        
        return textButtonStyle;
    }
    
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(new Texture(Gdx.files.internal("bg_menu.png")), 0, 0, JdcGame.V_WIDTH, JdcGame.V_HEIGHT);
        stage.getBatch().end();
        stage.act(delta);
        stage.draw(); }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
    
}
