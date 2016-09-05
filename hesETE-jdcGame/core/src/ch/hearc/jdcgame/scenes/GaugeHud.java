/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.jdcgame.scenes;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Daniel
 */
public class GaugeHud implements Disposable {
    public Stage stage;
    public Viewport viewport;
    
    public static PlayScreen screen;
    
    private Image gaugeHidenGravity;
    private Image gaugeHidenTeleport;
    
    private Actor gaugeGravity;
    private Actor gaugeTeleport;
    
    private float startX, endX;
    private float percentGravity;
    private float percentTeleport;

    public GaugeHud(SpriteBatch sb) {
        viewport = new FitViewport(JdcGame.V_WIDTH, JdcGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("others/gauges.pack"));
        
        gaugeHidenTeleport = new Image(atlas.findRegion("gauge_hiden"));
        gaugeHidenGravity = new Image(atlas.findRegion("gauge_hiden"));
        gaugeTeleport = new Image(atlas.findRegion("gauge_green"));
        gaugeGravity = new Image(atlas.findRegion("gauge_blue"));
       
        stage.addActor(gaugeGravity);
        stage.addActor(gaugeTeleport);
        stage.addActor(gaugeHidenGravity);
        stage.addActor(gaugeHidenTeleport);
        
        gaugeGravity.setX(15);
        gaugeGravity.setY(5);
        gaugeTeleport.setX(15);
        gaugeTeleport.setY(20);
        
        startX = gaugeHidenGravity.getX();
        endX = 102;
        
        emptyGaugeGravity();
        emptyGaugeTeleportation();
    }
    
    public void emptyGaugeGravity() {
        gaugeHidenGravity.setSize(0, 6);        
        gaugeHidenGravity.setX(gaugeGravity.getX() + 5);
        gaugeHidenGravity.setY(gaugeGravity.getY() + 2);
        percentGravity = 0;
    }
    
    public void emptyGaugeTeleportation () {
        gaugeHidenTeleport.setSize(0, 6);
        gaugeHidenTeleport.setX(gaugeTeleport.getX() + 5);
        gaugeHidenTeleport.setY(gaugeTeleport.getY() + 2);
        percentTeleport = 0;
    }
    
    public void updateGravityGauge(float percent) {       
        gaugeHidenGravity.setWidth(104 - 104 * percent);
        gaugeHidenGravity.setX(gaugeGravity.getWidth() + 12 - gaugeHidenGravity.getWidth());
        gaugeHidenGravity.invalidate();
        stage.act();
        stage.draw();        
    }
    
    public void updateTelepartionGauge(float percent) {  
        gaugeHidenTeleport.setWidth(104 - 104 * percent);
        gaugeHidenTeleport.setX(gaugeTeleport.getWidth() + 12 - gaugeHidenTeleport.getWidth());
        gaugeHidenTeleport.invalidate();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
