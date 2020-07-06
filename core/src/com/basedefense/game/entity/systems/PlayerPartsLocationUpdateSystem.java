package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.basedefense.game.entity.components.TransformComponent;
import com.basedefense.game.entity.components.PlayerPartComponent;
import com.basedefense.game.entity.components.Mapper;
import com.basedefense.game.entity.components.TextureComponent;

import static com.basedefense.game.utility.Utility.transpolateAttachPointOffset;

public class PlayerPartsLocationUpdateSystem extends IteratingSystem {

    ComponentMapper<PlayerPartComponent> cm;

    @SuppressWarnings("unchecked")
    public PlayerPartsLocationUpdateSystem(){
        super(Family.all(PlayerPartComponent.class).get());
        cm = ComponentMapper.getFor(PlayerPartComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = Mapper.transCom.get(entity);
        PlayerPartComponent ppc = cm.get(entity);
        //ConnectorPoint cp = entity.getComponent(ConnectorPoint.class);
        TextureComponent tc = entity.getComponent(TextureComponent.class);
        TransformComponent parenttrans = Mapper.transCom.get(ppc.owner);
        TextureComponent ptc = ppc.owner.getComponent(TextureComponent.class);
        float centerX = tc.region.getRegionWidth() / 2.0f;
        float centerY = tc.region.getRegionHeight() / 2.0f;
        float pivotPointX = ptc.region.getRegionWidth()/ 2.0f;
        float pivotPointY = ptc.region.getRegionHeight()/ 2.0f;
        Vector2 interpolatedOffset = transpolateAttachPointOffset(ppc.position_in_player.x ,ppc.position_in_player.y,centerX,centerY,
                pivotPointX, pivotPointY,parenttrans.rotation);
        trans.position.x = (parenttrans.position.x  + interpolatedOffset.x);
        trans.position.y = (parenttrans.position.y + interpolatedOffset.y);
        trans.rotation = parenttrans.rotation;
    }
}
