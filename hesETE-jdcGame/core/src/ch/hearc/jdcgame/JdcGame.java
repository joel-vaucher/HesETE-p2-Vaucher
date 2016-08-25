package ch.hearc.jdcgame;

import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JdcGame extends Game {
    
        public static final int V_WIDTH = 640;
        public static final int V_HEIGHT = 360;
        public static final float PPM = 100; //Pixel Per Meter

	public SpriteBatch batch;
        
        
        
        public static AssetManager manager;
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            manager = new AssetManager();
            
            manager.load("audio/music/gamesic.mp3", Music.class);
            manager.load("audio/sounds/teletransportation.mp3", Sound.class);
            manager.load("audio/sounds/waterson.mp3", Sound.class);
            manager.finishLoading();
            
            setScreen(new PlayScreen(this));                
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
