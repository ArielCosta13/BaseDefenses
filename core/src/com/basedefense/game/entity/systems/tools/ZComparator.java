package com.basedefense.game.entity.systems.tools;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.basedefense.game.entity.components.TransformComponent;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {
    private ComponentMapper<TransformComponent> transformM;

    public ZComparator(){
        transformM = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        return (int) Math.signum(transformM.get(entityB).position.z -
                transformM.get(entityA).position.z);
    }
}