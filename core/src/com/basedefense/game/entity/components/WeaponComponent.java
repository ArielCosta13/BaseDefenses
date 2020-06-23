package com.basedefense.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class WeaponComponent implements Component {
    public Entity emplacement_owner;
    public Vector3 offset = new Vector3();
    public Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation = 0.0f;
    public boolean isHidden = false;

    public void attachWeaponTo(Entity entityToAttach){
        emplacement_owner = entityToAttach;
    }

    public void setWeaponPointOffSet(Vector3 offsetCoordinates){
        offset = offsetCoordinates;
    }

}
