package com.anabiozzze.sample.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.anabiozzze.sample.TextSample;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TextSample";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new TextSample(), config);
	}
}
