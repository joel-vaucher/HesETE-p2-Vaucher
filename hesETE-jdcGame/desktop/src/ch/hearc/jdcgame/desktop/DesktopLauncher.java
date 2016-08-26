package ch.hearc.jdcgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.hearc.jdcgame.JdcGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.height = JdcGame.V_HEIGHT*2;
                config.width = JdcGame.V_WIDTH*2;
                config.title = "Travel Time Guy";
                new LwjglApplication(new JdcGame(), config);
	}
}
