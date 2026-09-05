package me.rplgame.miravia;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.rplgame.miravia.MiraviaGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Miravia");
		config.setWindowedMode(MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setResizable(false);
//		config.setWindowSizeLimits(MiraviaGame.WIDTH, MiraviaGame.HEIGHT, -1, -1);
		
		new Lwjgl3Application(new MiraviaGame(), config);
	}
}
