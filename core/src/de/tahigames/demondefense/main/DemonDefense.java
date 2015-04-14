package de.tahigames.demondefense.main;

import com.badlogic.gdx.ApplicationAdapter;

import de.tahigames.demondefense.engine.Core;

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
