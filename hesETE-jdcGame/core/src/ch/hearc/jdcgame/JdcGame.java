package ch.hearc.jdcgame;

import ch.hearc.jdcgame.screens.ScreenEnum;
import ch.hearc.jdcgame.screens.ScreenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe principale du programme
 */
public class JdcGame extends Game {
    
        public static final int V_WIDTH = 640;
        public static final int V_HEIGHT = 360;
        public static final float PPM = 100; //Pixel Per Meter
        public static BitmapFont FONT;
        
        public static final short NOTHING_BIT = 0;
        public static final short GROUND_BIT = 1;
        public static final short PLAYER_BIT = 2;
        public static final short WATER_BIT = 4;
        public static final short DOOR_BIT = 8;
        public static final short STAR_BIT = 8;
        public static final short FLAGEND_BIT = 16;
        public static final short DESTROYED_BIT = 32;
        

        public SpriteBatch batch;
        
        public static AssetManager manager;
        //public List<Integer> goverList = new ArrayList<Integer>();
	
        /**
         * Chargement des ressources et initialisation des éléments du jeu
         */
	@Override
	public void create () {
            batch = new SpriteBatch();
            manager = new AssetManager();
            
            manager.load("audio/music/gamesic.mp3", Music.class);
            manager.load("audio/music/menuson.mp3", Music.class);
            manager.load("audio/sounds/teletransportation.mp3", Sound.class);
            manager.load("audio/sounds/doorson.mp3", Sound.class);
            manager.load("audio/sounds/gameOver.mp3", Sound.class);
            manager.load("audio/sounds/youloose.mp3", Sound.class);
            manager.load("audio/sounds/winson.mp3", Sound.class);
            manager.load("audio/sounds/buttonson.mp3", Sound.class);
            manager.load("audio/sounds/click.mp3", Sound.class);
            
            manager.finishLoading();
            
            FONT = new BitmapFont(Gdx.files.internal("fonts/ComicSansMS.fnt"));
            ScreenManager.getInstance().initialize(this);
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);               
	}

	@Override
	public void render () {
            super.render();
            manager.update();
	}

	@Override
	public void dispose () {
            batch.dispose();
	}
}
