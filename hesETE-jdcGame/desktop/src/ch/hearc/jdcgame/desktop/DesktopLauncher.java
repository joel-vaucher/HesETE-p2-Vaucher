package ch.hearc.jdcgame.desktop;

import ch.hearc.jdcgame.JdcGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.Files;

/**
 * Point d'entrée du programme
 * Initialisation et paramètrage d'une nouvelle application 
 */
public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = JdcGame.V_HEIGHT*2;
        config.width = JdcGame.V_WIDTH*2;
        config.title = "Travel Time Guy";
        config.addIcon("icons/icon_128.png", Files.FileType.Internal);
        config.addIcon("icons/icon_32.png", Files.FileType.Internal);
        config.addIcon("icons/icon_16.png", Files.FileType.Internal);
        new LwjglApplication(new JdcGame(), config);
    }
}
