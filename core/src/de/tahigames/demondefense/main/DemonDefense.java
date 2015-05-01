package de.tahigames.demondefense.main;

import com.badlogic.gdx.ApplicationAdapter;

import de.tahigames.demondefense.engine.core.Core;
import de.tahigames.demondefense.engine.core.Game;
import de.tahigames.demondefense.game.screens.IngameScreen;

public class DemonDefense extends ApplicationAdapter {

    private Core core;

	@Override
	public void create () {
		core = new Core(new Game(new IngameScreen()));
	}

	@Override
	public void render () {
		core.run();
	}

    @Override
    public void dispose() {
        super.dispose();
        core.dispose();
    }
}
