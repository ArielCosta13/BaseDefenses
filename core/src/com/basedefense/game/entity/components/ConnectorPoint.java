package com.basedefense.game.entity.components;

//This connector holds the conection coordinates
//of the element to be attached
// AttachmentPointComponent hold the coordinates of the
//hardpoint located in the player
//
//Connector Point hold the coordinates of the point
//that goes in that hardpoint. Think of the.
// connector point like a screw and the
//hardpoint the hole that the screw goes
//This is the element rotation point

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class ConnectorPoint implements Component {
    public Vector3 offset = new Vector3();
}
