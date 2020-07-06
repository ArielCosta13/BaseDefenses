package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import com.basedefense.game.controller.KeyboardController;

import com.basedefense.game.entity.components.MovementStatsComponent;
import com.basedefense.game.entity.components.TransformComponent;
import com.basedefense.game.entity.components.WeaponComponent;
import com.basedefense.game.entity.factories.EntityFactory;



public class WeaponControlSystem extends IteratingSystem  {

    ComponentMapper<WeaponComponent> pcm;
    ComponentMapper<TransformComponent> tcm;
    ComponentMapper<MovementStatsComponent> mc;
    ComponentMapper<WeaponComponent> wc;

    KeyboardController controller;
    EntityFactory efac;

    @SuppressWarnings("unchecked")
    public WeaponControlSystem(KeyboardController keyCon, EntityFactory efactory) {
        super(Family.all(WeaponComponent.class).get());
        controller = keyCon;
        pcm = ComponentMapper.getFor(WeaponComponent.class);
        tcm = ComponentMapper.getFor(TransformComponent.class);
        mc  = ComponentMapper.getFor(MovementStatsComponent.class);
        wc = ComponentMapper.getFor(WeaponComponent.class);
        efac = efactory;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (controller.isMouse1Down){
            Entity shot = efac.addEntity(EntityFactory.EntityType.BULLET);
            shot.getComponent(TransformComponent.class).position.x =  tcm.get(entity).position.x
                    + wc.get(entity).offset.x;
            shot.getComponent(TransformComponent.class).position.y =  tcm.get(entity).position.y
                    + wc.get(entity).offset.y;
            shot.getComponent(TransformComponent.class).position.z =  tcm.get(entity).position.z
                    + wc.get(entity).offset.z;
            shot.getComponent(TransformComponent.class).rotation =  entity.getComponent(TransformComponent.class).rotation;
        }



    }

}
