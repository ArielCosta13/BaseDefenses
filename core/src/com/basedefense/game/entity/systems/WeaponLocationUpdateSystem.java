package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import com.basedefense.game.entity.components.TextureComponent;
import com.basedefense.game.entity.components.TransformComponent;
import com.basedefense.game.entity.components.WeaponComponent;
import com.basedefense.game.entity.components.Mapper;

import static com.basedefense.game.utility.Utility.transpolateAttachPointOffset;

public class WeaponLocationUpdateSystem extends IteratingSystem {

    ComponentMapper<WeaponComponent> wcm;

    @SuppressWarnings("unchecked")
    public WeaponLocationUpdateSystem(){
        super(Family.all(WeaponComponent.class).get());
        wcm = ComponentMapper.getFor(WeaponComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = Mapper.transCom.get(entity);
        WeaponComponent wc = wcm.get(entity);
        TransformComponent parenttrans = Mapper.transCom.get(wc.emplacement_owner);

        TextureComponent tc = entity.getComponent(TextureComponent.class);
        TextureComponent ptc = wc.emplacement_owner.getComponent(TextureComponent.class);
        float centerX = tc.region.getRegionWidth() / 2.0f;
        float centerY = tc.region.getRegionHeight() / 2.0f;
        float pivotPointX = ptc.region.getRegionWidth()/ 2.0f;
        float pivotPointY = ptc.region.getRegionHeight()/ 2.0f;
        Vector2 interpolatedOffset = transpolateAttachPointOffset(wc.offset.x ,wc.offset.y,centerX,centerY,
                pivotPointX, pivotPointY,parenttrans.rotation);
        trans.position.x = (parenttrans.position.x  + interpolatedOffset.x);
        trans.position.y = (parenttrans.position.y + interpolatedOffset.y);
        trans.rotation = parenttrans.rotation;
    }
}
