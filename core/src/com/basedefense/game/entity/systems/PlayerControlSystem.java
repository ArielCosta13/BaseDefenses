package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gdx.game.controller.KeyboardController;
import com.gdx.game.entity.components.*;
import com.gdx.game.entity.factories.EntityFactory;

import static com.gdx.game.Utility.*;


public class PlayerControlSystem extends IteratingSystem {

	ComponentMapper<PlayerComponent> pcm;
	ComponentMapper<TransformComponent> tcm;
	ComponentMapper<MovementStatsComponent> mc;
	KeyboardController controller;
	EntityFactory efac;
	public Signal<Float> playerRotationSignal;

	@SuppressWarnings("unchecked")
	public PlayerControlSystem(KeyboardController keyCon, EntityFactory efactory) {
		super(Family.all(PlayerComponent.class).get());
		controller = keyCon;
		pcm = ComponentMapper.getFor(PlayerComponent.class);
		tcm = ComponentMapper.getFor(TransformComponent.class);
		mc  = ComponentMapper.getFor(MovementStatsComponent.class);
		efac = efactory;
		playerRotationSignal = new Signal<Float>();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		if(controller.left){
			tcm.get(entity).rotation += mc.get(entity).rotationSpeed;
			playerRotationSignal.dispatch(mc.get(entity).rotationSpeed);
		}
		if(controller.right){
			tcm.get(entity).rotation -= mc.get(entity).rotationSpeed;
			playerRotationSignal.dispatch(-mc.get(entity).rotationSpeed);
		}
		if(!controller.left && !controller.right){
		}
		if(controller.up){
			tcm.get(entity).position.y += calculateVectorialY(mc.get(entity).velY,tcm.get(entity).rotation);
			tcm.get(entity).position.x += calculateVectorialX(mc.get(entity).velX,tcm.get(entity).rotation);
		}
        if(controller.down){
			tcm.get(entity).position.y = tcm.get(entity).position.y - 1;
        }
        trimRotationValue(tcm.get(entity).rotation);

	}

}
