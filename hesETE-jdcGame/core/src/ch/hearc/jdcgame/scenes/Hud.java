/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.scenes;

import ch.hearc.jdcgame.JdcGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Daniel
 */
public class Hud implements Disposable{
    public Stage stage;
    public Viewport viewport;
    
    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    
    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label playerLabel;
    
    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        
        viewport = new FitViewport(JdcGame.V_WIDTH, JdcGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        scoreLabel =  new Label(String.format("%06d", score), new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        timeLabel =  new Label("TIME", new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        levelLabel = new Label("1", new Label.LabelStyle(JdcGame.FONT, Color.WHITE));;
        worldLabel = new Label("Level", new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        playerLabel = new Label("Life", new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        
        table.add(playerLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();
        
        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
