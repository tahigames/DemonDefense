package de.tahigames.demondefense.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.tahigames.demondefense.Core;

public class DemonDefense extends ApplicationAdapter {

    private Core core;

	@Override
	public void create () {
		core = new Core();
	}

	@Override
	public void render () {
		core.run();
	}
}
