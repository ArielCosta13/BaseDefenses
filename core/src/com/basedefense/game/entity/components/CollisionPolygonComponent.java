package com.basedefense.game.entity.components;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author AriySam
 */
public class CollisionPolygonComponent implements Component {

    public Polygon polygon;
    public Color color;

    public void  setCollisionPolygon(Rectangle bounds, Color pcolor){
        polygon = new Polygon(new float[]{0,0,bounds.width,0,bounds.width,bounds.height,0,bounds.height});
        polygon.setOrigin(bounds.width/2, bounds.height/2);
        color = pcolor;
    }

    public void setRotation(float rotation){
        polygon.setRotation(rotation);
    }

    public void setPosition(float x, float y){
        polygon.setPosition(x,y);
    }

    public float[] getTransformedVertices(){
        return polygon.getTransformedVertices();
    }
}

