package ch.hearc.jdcgame.scenes;

import ch.hearc.jdcgame.JdcGame;
import ch.hearc.jdcgame.screens.PlayScreen;
import ch.hearc.jdcgame.tools.Localization;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 */
public class Hud implements Disposable{
    public Stage stage;
    public Viewport viewport;
    
    public static PlayScreen screen;
    
    private Integer worldTimer;
    private float timeCount;
    //Passe à vrai si le temps = 0
    private boolean timeUp;
    private static Integer health;
    
    private Label countdownLabel;
    private static Label healthLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label lifeLabel;
    private static String number;
    
    /**
     * 
     * @param sb 
     */
    public Hud(SpriteBatch sb) {
        
        worldTimer = 90;
        timeCount = 0;
        health = 5;
        
        viewport = new FitViewport(JdcGame.V_WIDTH, JdcGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        
        countdownLabel = new Label(String.format("%d", worldTimer), new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        healthLabel =  new Label(String.format("%d", health), new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        timeLabel =  new Label(Localization.Time_LAB, new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        levelLabel = new Label(number, new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        worldLabel = new Label(Localization.LEVEL_LAB, new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        lifeLabel = new Label(Localization.LIFE_LAB, new Label.LabelStyle(JdcGame.FONT, Color.WHITE));
        
        //LAbels supérieurs
        table.add(lifeLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        
        //Labels inférieurs
        table.row();
        table.add(healthLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();
        
        stage.addActor(table);
    }

    /**
     * 
     * @param dt 
     */
    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
            }
            countdownLabel.setText(String.format("%02d", worldTimer));
            timeCount = 0;
        }
    }
    
    /**
     * 
     * @return 
     */
    public static Integer getHealth(){
        return  health;
        
    }
    
    /**
     * 
     * @param value
     * @return 
     */
    public static Integer updateHealth(int value){
        
        health -= value;
        if(health >=0)
            healthLabel.setText(String.format("%d", health));
        return health;
    }
    
    /**
     * Changement du numéro du level
     * @param value 
     */
    public static void setLevelNumber(String value){ 
        number = value;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isTimeUp() {
        return timeUp; 
    }
    
    @Override
    public void dispose() {
        stage.dispose();
    }
}
