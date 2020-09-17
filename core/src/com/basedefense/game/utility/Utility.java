package com.basedefense.game.utility;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import static com.basedefense.game.loaders.Assets.*;

public class Utility {

    public static void queueAddImages(AssetManager manager){
        System.out.println("Loading atlas");
        manager.load(BASE_PARTS_ATLAS, TextureAtlas.class);
        manager.load(WEAPON_ATLAS, TextureAtlas.class);
      //  manager.load("TT",TextureAtlas.class);
      //  manager.load(BULLETS_ATLAS, TextureAtlas.class);
      //  manager.load(SHIP_BODY_BIG_ATLAS, TextureAtlas.class);
      //  manager.load(CANNON_TURRET_MEDIUM_ATLAS, TextureAtlas.class);
      //  manager.load(MISSILE_TURRET_SMALL_ATLAS, TextureAtlas.class);
      //  manager.load(SHIP_PARTS_MEDIUM, TextureAtlas.class);
      //  manager.load(EMPLACEMENT_MEDIUM, TextureAtlas.class);
      //  manager.load(CANNON_MEDIUM, TextureAtlas.class);
      //  manager.load(CANNON_MEDIUM_ANIMATED, TextureAtlas.class);
      //  manager.load(CANNON_MEDIUM_PARTS, TextureAtlas.class);
        System.out.println("Finish Loading atlas");
    }

    /**
     *Trim the value of rotation when in over 360
     *
     */
    public static float trimRotationValue(float rotation) {
        if (rotation > 360){
            rotation = rotation -360;
        }
        if (rotation < -360){
            rotation = rotation + 360;
        }
        return rotation;
    }

    public static Vector2 transpolateAttachPointOffset(float AttachmentPointX, float AttachmentPointY,
                                                       float centerX, float centerY,
                                                       float pivotPointX, float pivotPointY,
                                                       float rotation){
        float centeredX = AttachmentPointX - pivotPointX;
        float centeredY = AttachmentPointY - pivotPointY;
        float x =  (float) (centeredX * Math.cos(Math.toRadians((double)rotation))) -
                (float) (centeredY * Math.sin(Math.toRadians((double)rotation)));
        float y =  (float) (centeredX * Math.sin(Math.toRadians((double)rotation))) +
                (float) (centeredY * Math.cos(Math.toRadians((double)rotation)));
         x = x + pivotPointX - centerX;
         y = y + pivotPointY - centerY;
         return new Vector2(x,y);
    }

    public static float calculateVectorialX(float a,float angle){
        return (float) (a * Math.sin(-Math.toRadians(angle)));
    }

    public static float calculateVectorialY(float a,float angle){
        return (float) (a * Math.cos(-Math.toRadians(angle)));
    }

    public static Vector3 calculateVector(float a, float angle){
        Vector3 vector = new Vector3();
        vector.x = calculateVectorialX(a,angle);
        vector.y = calculateVectorialY(a,angle);
        vector.z = 0;
        return vector;

    }

}
