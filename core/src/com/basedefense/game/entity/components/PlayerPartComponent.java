package com.basedefense.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PlayerPartComponent implements Component {
    public Entity owner;
    public Vector3 position_in_player = new Vector3();
    public Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation = 0.0f;
    public boolean isHidden = false;

    public void attachEntityTo(Entity entityToAttach){
        owner = entityToAttach;
    }

    public void setEntityAttachmentPointOffSet(Vector3 offsetCoordinates){
        position_in_player = offsetCoordinates;
    }

}