package ch.hearc.jdcgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.hearc.jdcgame.JdcGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.height = JdcGame.V_HEIGHT;
                config.width = JdcGame.V_WIDTH;
                config.title = "Travel Time Guy";
                new LwjglApplication(new JdcGame(), config);
	}
}
