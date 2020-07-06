package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basedefense.game.entity.components.BulletComponent;
import com.basedefense.game.entity.components.Mapper;
import com.basedefense.game.entity.components.TransformComponent;

import static com.basedefense.game.utility.Utility.calculateVectorialX;
import static com.basedefense.game.utility.Utility.calculateVectorialY;

public class BulletSystem extends IteratingSystem{

    @SuppressWarnings("unchecked")
    public BulletSystem(){
        super(Family.all(BulletComponent.class).get());

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //get  bullet components
        BulletComponent bullet = Mapper.bulletCom.get(entity);
        TransformComponent trans = Mapper.transCom.get(entity);
        trans.position.y = trans.position.y + calculateVectorialY(bullet.vel,trans.rotation);
        trans.position.x = trans.position.x + calculateVectorialX(bullet.vel,trans.rotation);
        bullet.activeTime += deltaTime;

        if(bullet.activeTime > 1){
            bullet.isDead = true;
        }

        //check if bullet is dead
        if(bullet.isDead){
            System.out.println("Bullet died");
        }
    }
}
