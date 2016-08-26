package ch.hearc.jdcgame;

import ch.hearc.jdcgame.screens.PlayScreen;
import ch.hearc.jdcgame.screens.ScreenEnum;
import ch.hearc.jdcgame.screens.ScreenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JdcGame extends Game {
    
        public static final int V_WIDTH = 640;
        public static final int V_HEIGHT = 360;
        public static final float PPM = 100; //Pixel Per Meter
        public static BitmapFont FONT;

		public SpriteBatch batch;
        
        public static AssetManager manager;
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            manager = new AssetManager();
            
            manager.load("audio/music/gamesic.mp3", Music.class);
            manager.load("audio/sounds/teletransportation.mp3", Sound.class);
            //manager.load("audio/sounds/waterson.mp3", Sound.class);
            manager.finishLoading();
            
            FONT = new BitmapFont(Gdx.files.internal("ComicSansMS.fnt"));
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
