package ch.hearc.jdcgame.scenes;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import ch.hearc.jdcgame.tools.Localization;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *  Affichage des jauges du temps de rechargement
 */
public class GaugeHud implements Disposable {
    public Stage stage;
    public Viewport viewport;
    
    public static PlayScreen screen;
    
    private Label gravityLbl;
    private Label teleportLbl;
    
    private Image gaugeGravity;
    private Image gaugeTeleport;
    private Image gaugeHidenGravity;
    private Image gaugeHidenTeleport;
    
    private float startX, endX;
    private float percentGravity;
    private float percentTeleport;

    /**
     * Création et positionnement des jauges
     * @param sb 
     */
    public GaugeHud(SpriteBatch sb) {
        viewport = new FitViewport(JdcGame.V_WIDTH, JdcGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        // Récupération des textures
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("others/gauges.pack"));
        
        // Initialisation des jauges (simples images)
        // Initialisation de deux images qui permettront de simuler le chargement
        gaugeHidenTeleport = new Image(atlas.findRegion("gauge_hiden"));
        gaugeHidenGravity = new Image(atlas.findRegion("gauge_hiden"));
        gaugeTeleport = new Image(atlas.findRegion("gauge_green"));
        gaugeGravity = new Image(atlas.findRegion("gauge_blue"));
        
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/ComicSansMS.fnt"));
        font.getData().setScale(0.5f);
        
        gravityLbl = new Label(Localization.GRAVITY_LAB, new Label.LabelStyle(font, Color.WHITE));
        teleportLbl = new Label(Localization.TELEPORT_LAB, new Label.LabelStyle(font, Color.WHITE));
       
        stage.addActor(gaugeGravity);
        stage.addActor(gaugeTeleport);
        stage.addActor(gaugeHidenGravity);
        stage.addActor(gaugeHidenTeleport);
        stage.addActor(gravityLbl);
        stage.addActor(teleportLbl);
        
        gaugeGravity.setX(15);
        gaugeGravity.setY(5);
        gravityLbl.setX(15);
        gravityLbl.setY(15);
        gaugeTeleport.setX(15);
        gaugeTeleport.setY(30);
        teleportLbl.setX(15);
        teleportLbl.setY(40);
        
        startX = gaugeHidenGravity.getX();
        endX = 102;
        
        fullGaugeGravity();
        fullGaugeTeleportation();
    }
    
    /**
     * Fait disparaitre le cache de la jauge de la gravité
     */
    public void fullGaugeGravity() {
        gaugeHidenGravity.setSize(0, 6);        
        gaugeHidenGravity.setX(gaugeGravity.getX() + 5);
        gaugeHidenGravity.setY(gaugeGravity.getY() + 2);
        percentGravity = 0;
    }
    
    /**
     * Fait disparaitre le cache de la jauge de la téléportation
     */
    public void fullGaugeTeleportation () {
        gaugeHidenTeleport.setSize(0, 6);
        gaugeHidenTeleport.setX(gaugeTeleport.getX() + 5);
        gaugeHidenTeleport.setY(gaugeTeleport.getY() + 2);
        percentTeleport = 0;
    }
    
    /**
     * Simule le chargement de la jauge de la gravité
     * en variant la taille d'un cache
     * @param percent : Pourcentage du temps passé
     */
    public void updateGravityGauge(float percent) {
        // Appel de la méthode update en indiquant la jauge à mettre à jour
        updateGauge(gaugeGravity, gaugeHidenGravity, percent);
    }
    
    /**
     * Simule le chargement de la jauge de la téléportation
     * en variant la taille d'un cache
     * @param percent : Pourcentage du temps passé
     */
    public void updateTelepartionGauge(float percent) {  
        // Appel de la méthode update en indiquant la jauge à mettre à jour
        updateGauge(gaugeTeleport, gaugeHidenTeleport, percent);
    }
    
    /**
     * Simule le chargement de la jauge de la gravité 
     * en variant la taille d'un cache
     * 
     * La taille du cache est calculé à l'aide 
     *  - du pourcentage de temps passé
     *  - de la taille de la jauge
     * 
     * @param gauge : Jauge
     * @param gaugeHiden : Cache de la jauge
     * @param percent : Pourcentage du temps passé
     */
    private void updateGauge(Image gauge, Image gaugeHiden, float percent) {
        gaugeHiden.setWidth(104 - 104 * percent);
        gaugeHiden.setX(gauge.getWidth() + 12 - gaugeHiden.getWidth());
        gaugeHiden.invalidate();
        stage.act();
        stage.draw();    
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
