package ch.hearc.jdcgame.scenes;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.ScreenEnum;
import ch.hearc.jdcgame.screens.ScreenManager;
import ch.hearc.jdcgame.tools.Localization;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 */
public class OtherScene implements Disposable{
    
    public Stage stage;
    public Viewport viewport;
    protected Table table;
    Label returnLbl;
    
    /**
     * 
     * @param sb
     * @param screenName 
     */
    public OtherScene(SpriteBatch sb, String screenName) {
        viewport = new FitViewport(JdcGame.V_WIDTH, JdcGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);        
        Gdx.input.setInputProcessor(stage);
        
        table = new Table();
        table.setFillParent(true);
        table.setBackground(new Image(new Texture(Gdx.files.internal("others/bg_menu.png"))).getDrawable());
        
        
        Label title = new Label(screenName, new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/ComicSansMS.fnt"));
        font.getData().setScale(0.5f);
        
        if(screenName.equals(Localization.MESSAGE_2)){
            returnLbl = new Label(Localization.MESSAGE_1, new Label.LabelStyle(font, Color.WHITE));
        }
        TextButton replayBtn, exitBtn;
        TextButton.TextButtonStyle textButtonStyle = makeButtonStyle();      
                
        replayBtn = new TextButton(Localization.REPLAY_BTN, textButtonStyle);
        exitBtn = new TextButton(Localization.QUIT_BTN, textButtonStyle);
        
        replayBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JdcGame.manager.get("audio/sounds/click.mp3", Sound.class).play();
                ScreenManager.getInstance().showScreen(ScreenEnum.PLAY_SCREEN);
            }
        });     
        replayBtn.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                JdcGame.manager.get("audio/sounds/buttonson.mp3", Sound.class).play();
            }
        });
        
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JdcGame.manager.get("audio/sounds/click.mp3", Sound.class).play();
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
        exitBtn.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                JdcGame.manager.get("audio/sounds/buttonson.mp3", Sound.class).play();
            }
        });
        
        table.add(title).padBottom(20f);
        table.row();
        table.add(replayBtn);
        table.row();
        table.add(exitBtn);
        table.row();
        table.add(returnLbl).padTop(15f);
        stage.addActor(table);
    }
    
    /**
     * 
     * @return 
     */
    protected TextButton.TextButtonStyle makeButtonStyle () {
        TextButton.TextButtonStyle textButtonStyle;
        BitmapFont font;
        Skin skin;
        TextureAtlas buttonAtlas; 
        
        font = JdcGame.FONT;
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
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
    public void dispose() {
        stage.dispose();
    }
    
}
