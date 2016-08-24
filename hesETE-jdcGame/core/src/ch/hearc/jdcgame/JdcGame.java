package ch.hearc.jdcgame;

import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JdcGame extends Game {
    
        public static final int V_WIDTH = 640;
        public static final int V_HEIGHT = 360;
        public static final float PPM = 100; //Pixel Per Meter

	public SpriteBatch batch;
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            setScreen(new PlayScreen(this));                
	}

	@Override
	public void render () {
            super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
