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
 *  Menu avec la liste des niveaux
 *  Cette classe hérite de AbstractMenuScreen
 */
public class LevelMenuScreen extends AbstractMenuScreen {
    
    /**
     * Construction du menu
     * @param game 
     */
    public LevelMenuScreen(JdcGame game) {
        super(game);
        
        TextButton levelBtn, returnBtn;
        TextButton.TextButtonStyle textButtonStyle = makeButtonStyle();     
        
        // Recherche et parcour des fichiers contenu dans le dossier levels
        FileHandle[] files = Gdx.files.internal("levels/").list();
        for(final FileHandle file: files) {
            // Si le fichier est un tmx --> fichier niveau
            if(file.extension().equals("tmx")) {
                // Création d'un bouton pour le niveau trouvé
                levelBtn = new TextButton(Localization.LEVEL_BTN + " " + file.nameWithoutExtension(), textButtonStyle);
                // Action lors du clic de souris --> Affiche le niveau
                levelBtn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        JdcGame.manager.get("audio/sounds/click.mp3", Sound.class).play();
                        music.stop();
                        ScreenManager.getInstance().showPlayScreen(ScreenEnum.PLAY_SCREEN, file.path());
                    }
                });              
                // Action lors du survol de souris --> Emettre un bruit
                levelBtn.addListener(new ClickListener() {
                @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        JdcGame.manager.get("audio/sounds/buttonson.mp3", Sound.class).play();
                    }
                });
                // Ajout du bouton au tableau servant de liste
                buttonsTable.add(levelBtn);
                buttonsTable.row();
                scrollTable.add(levelBtn);
                scrollTable.row();
            }
        }
        
        // Bouton de retour au menu principal
        returnBtn = new TextButton(Localization.BACK_BTN, textButtonStyle);
        // Action lors du clic de souris --> Affiche le menu principal
        returnBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JdcGame.manager.get("audio/sounds/click.mp3", Sound.class).play();
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
        // Action lors du survol de souris --> Emettre un bruit
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
