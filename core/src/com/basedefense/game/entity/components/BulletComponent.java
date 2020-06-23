package com.basedefense.game.entity.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BulletComponent implements Component, Poolable {
    public static enum Owner { ENEMY,PLAYER,SCENERY,NONE }

    public Entity particleEffect;
    public float xVel = 0;
    public float yVel = 0;
    public float vel = 5;
    public boolean isDead = false;
    public Owner owner = Owner.NONE;
    public float activeTime = 0;

    @Override
    public void reset() {
        owner = Owner.NONE;
        xVel = 0;
        yVel = 0;
        isDead = false;
        particleEffect = null;
        activeTime = 0;
    }
}
