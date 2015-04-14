package de.tahigames.demondefense.main;

import com.badlogic.gdx.ApplicationAdapter;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.game.DemonDefenseGame;

public class DemonDefense extends ApplicationAdapter {

    private Core core;

	@Override
	public void create () {
		core = new Core(new DemonDefenseGame());
	}

	@Override
	public void render () {
		core.run();
	}
}
