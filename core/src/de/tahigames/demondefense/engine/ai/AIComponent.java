package de.tahigames.demondefense.engine.ai;

import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Core;

/**
 * Created by Mirco on 15.04.2015.
 */
public abstract class AIComponent extends Component {

    public abstract void think(float delta);

    @Override
    public void onAddToCore(Core core) {
        super.onAddToCore(core);
        core.getAiEngine().addComponent(this);
    }

    @Override
    public void onRemoveFromCore(Core core) {
        super.onRemoveFromCore(core);
        core.getAiEngine().removeComponent(this);
    }
}
