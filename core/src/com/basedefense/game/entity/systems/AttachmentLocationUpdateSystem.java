package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entity.components.*;

import static com.gdx.game.Utility.transpolateAttachPointOffset;

public class AttachmentLocationUpdateSystem extends IteratingSystem {

    ComponentMapper<AttachmentPointComponent> apc;

    @SuppressWarnings("unchecked")
    public AttachmentLocationUpdateSystem(){
        super(Family.all(AttachmentPointComponent.class).get());
        apc = ComponentMapper.getFor(AttachmentPointComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = Mapper.transCom.get(entity);
        AttachmentPointComponent ap = apc.get(entity);
        ConnectorPoint cp = entity.getComponent(ConnectorPoint.class);
        TextureComponent tc = entity.getComponent(TextureComponent.class);
        TransformComponent parenttrans = Mapper.transCom.get(ap.parent);
        TextureComponent ptc = ap.parent.getComponent(TextureComponent.class);
        float centerX = tc.region.getRegionWidth() / 2.0f;
        float centerY = tc.region.getRegionHeight() / 2.0f;
        float pivotPointX = ptc.region.getRegionWidth()/ 2.0f;
        float pivotPointY = ptc.region.getRegionHeight()/ 2.0f;
        Vector2 interpolatedOffset = transpolateAttachPointOffset(ap.offset.x ,ap.offset.y,centerX,centerY,
                pivotPointX, pivotPointY,parenttrans.rotation);
        trans.position.x = (parenttrans.position.x  + interpolatedOffset.x);
        trans.position.y = (parenttrans.position.y + interpolatedOffset.y);
        //trans.rotation = parenttrans.rotation;
    }
}
