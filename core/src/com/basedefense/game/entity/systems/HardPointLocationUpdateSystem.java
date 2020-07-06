package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.basedefense.game.entity.components.TransformComponent;
import com.basedefense.game.entity.components.HardPointComponent;
import com.basedefense.game.entity.components.TextureComponent;
import com.basedefense.game.entity.components.Mapper;

import static com.basedefense.game.utility.Utility.transpolateAttachPointOffset;

public class HardPointLocationUpdateSystem extends IteratingSystem {

    ComponentMapper<HardPointComponent> cm;

    @SuppressWarnings("unchecked")
    public HardPointLocationUpdateSystem(){
        super(Family.all(HardPointComponent.class).get());
        cm = ComponentMapper.getFor(HardPointComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = Mapper.transCom.get(entity);
        HardPointComponent hpc = cm.get(entity);
        TransformComponent parenttrans = Mapper.transCom.get(hpc.owner);
      //  trans.position.x = (parenttrans.position.x  + hpc.position_in_part.x);
      //  trans.position.y = (parenttrans.position.y + hpc.position_in_part.y);
      //  trans.rotation = parenttrans.rotation;

        TextureComponent tc = entity.getComponent(TextureComponent.class);
        TextureComponent ptc = hpc.owner.getComponent(TextureComponent.class);
        float centerX = tc.region.getRegionWidth() / 2.0f;
        float centerY = tc.region.getRegionHeight() / 2.0f;
        float pivotPointX = ptc.region.getRegionWidth()/ 2.0f;
        float pivotPointY = ptc.region.getRegionHeight()/ 2.0f;
        Vector2 interpolatedOffset = transpolateAttachPointOffset(hpc.position_in_part.x ,hpc.position_in_part.y,centerX,centerY,
                pivotPointX, pivotPointY,parenttrans.rotation);
        trans.position.x = (parenttrans.position.x  + interpolatedOffset.x);
        trans.position.y = (parenttrans.position.y + interpolatedOffset.y);
        trans.rotation = parenttrans.rotation;
    }
}
