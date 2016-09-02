package ch.hearc.jdcgame.screens.menus;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.ScreenEnum;
import ch.hearc.jdcgame.screens.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 *
 * @author Daniel
 */
public class MainMenuScreen extends AbstractMenuScreen {
    
    public MainMenuScreen(JdcGame game) {
        super(game);
        
        TextButton playBtn, exitBtn, settingBtn;
        TextButton.TextButtonStyle textButtonStyle = makeButtonStyle();      
                
        playBtn = new TextButton("Jouer", textButtonStyle);
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

}
