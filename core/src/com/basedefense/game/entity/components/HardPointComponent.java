package com.basedefense.game.entity.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/*
  Hardpoints owner is a player_part in this game
  Hardpoints are added to a part.
  This could change in other games

  initial_rotation is not yet implemented.
  If the hardpoint has an initial rotation it should be reflected here
 */
public class HardPointComponent implements Component {
    public Entity owner;
    public Vector3 position_in_part = new Vector3();
    public Vector2 scale = new Vector2(1.0f, 1.0f);
    public float initial_rotation = 0.0f;
    public float rotation = 0.0f;
    public boolean isHidden = false;

    public void attachEntityTo(Entity entityToAttach){
        owner = entityToAttach;
    }

    public void setEntityAttachmentPointOffSet(Vector3 offsetCoordinates){
        position_in_part = offsetCoordinates;
    }

}