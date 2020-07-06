package com.basedefense.game.entity.systems;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basedefense.game.entity.components.EmplacementComponent;
import com.basedefense.game.entity.components.Mapper;
import com.basedefense.game.entity.components.TransformComponent;

public class EmplacementLocationUpdateSystem extends IteratingSystem {

    ComponentMapper<EmplacementComponent> cm;

    @SuppressWarnings("unchecked")
    public EmplacementLocationUpdateSystem(){
        super(Family.all(EmplacementComponent.class).get());
        cm = ComponentMapper.getFor(EmplacementComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = Mapper.transCom.get(entity);
        EmplacementComponent hpc = cm.get(entity);
        TransformComponent parenttrans = Mapper.transCom.get(hpc.owner);
        trans.position.x = parenttrans.position.x ;
        trans.position.y = parenttrans.position.y;
    }

}