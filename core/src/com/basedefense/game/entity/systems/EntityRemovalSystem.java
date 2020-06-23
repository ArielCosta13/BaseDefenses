package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gdx.game.entity.components.BulletComponent;
import com.gdx.game.entity.factories.EntityFactory;

public class EntityRemovalSystem extends IteratingSystem {
    EntityFactory efactory;

    @SuppressWarnings("unchecked")
    public EntityRemovalSystem(EntityFactory ef){
        super(Family.all(BulletComponent.class).get());
        efactory = ef;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //If dead remove from the game
        if(entity.getComponent(BulletComponent.class).isDead){
            System.out.println("Entity Removed");
            efactory.removeEntity(entity);
        }
    }
}

