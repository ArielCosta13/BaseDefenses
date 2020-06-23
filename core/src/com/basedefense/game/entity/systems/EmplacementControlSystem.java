package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gdx.game.controller.KeyboardController;
import com.gdx.game.entity.components.*;
import com.gdx.game.entity.factories.EntityFactory;


public class EmplacementControlSystem extends IteratingSystem  {

    ComponentMapper<EmplacementComponent> pcm;

    ComponentMapper<TransformComponent> tcm;
    ComponentMapper<MovementStatsComponent> mc;
    ComponentMapper<EmplacementComponent> ec;

    KeyboardController controller;
    EntityFactory efac;

    @SuppressWarnings("unchecked")
    public EmplacementControlSystem(KeyboardController keyCon, EntityFactory efactory) {
        super(Family.all(EmplacementComponent.class).get());
        controller = keyCon;
        tcm = ComponentMapper.getFor(TransformComponent.class);
        mc  = ComponentMapper.getFor(MovementStatsComponent.class);
        ec = ComponentMapper.getFor(EmplacementComponent.class);
        efac = efactory;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(controller.a){
            tcm.get(entity).rotation += mc.get(entity).rotationSpeed;
        }
        if(controller.d){
            tcm.get(entity).rotation -= mc.get(entity).rotationSpeed;
        }

    }

}
