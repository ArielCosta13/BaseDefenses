package com.basedefense.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.basedefense.game.views.MainGameScreen;
import com.basedefense.game.utility.Utility;

public class BaseDefense extends Game {

	public MainGameScreen mainScreen;
	public final AssetManager manager = new AssetManager();

	@Override
	public void create () {
		Utility.queueAddImages(manager);
		manager.finishLoading();
		mainScreen = new MainGameScreen(this);

		setScreen(mainScreen);
	}

	@Override
	public void dispose () {
		mainScreen.dispose();
		manager.dispose();
	}
}

