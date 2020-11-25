package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Transporte;

public class DesktopLauncher {
	private static final int width = 800;
	private static final int height = 640;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Transporte SI";
		config.useGL30 = true;
		config.width = width;
		config.height = height;
		config.resizable = false;
		new LwjglApplication(new Transporte(), config);
	}
}
