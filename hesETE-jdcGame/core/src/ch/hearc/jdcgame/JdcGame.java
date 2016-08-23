package ch.hearc.jdcgame;

import ch.hearc.jdcgame.screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JdcGame extends Game {
    
        public static final int V_WIDTH = 640;
        public static final int V_HEIGHT = 376;

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
<<<<<<< Updated upstream
		setScreen(new PlayScreen(this));
=======
		img = new Texture("jdcGame.tmx");
>>>>>>> Stashed changes
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
