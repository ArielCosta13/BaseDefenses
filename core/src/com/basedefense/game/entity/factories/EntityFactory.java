package com.basedefense.game.entity.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Json;
import com.basedefense.game.entity.components.TextureComponent;
import com.basedefense.game.entity.components.TransformComponent;
import com.basedefense.game.entity.components.MovementStatsComponent;
import com.basedefense.game.entity.components.WeaponComponent;
import com.basedefense.game.entity.components.PlayerComponent;
import com.basedefense.game.entity.components.PlayerPartComponent;
import com.basedefense.game.entity.components.HardPointComponent;
import com.basedefense.game.entity.components.EmplacementComponent;
import com.basedefense.game.entity.components.StateComponent;
import com.basedefense.game.entity.components.BulletComponent;
import com.basedefense.game.entity.components.WeaponPartComponent;
import com.basedefense.game.entity.components.CollisionPolygonComponent;
import com.basedefense.game.templates.EntityTemplate;



//import static com.basedefense.game.loaders.Assets.BULLETS_ATLAS;

public class EntityFactory {

    private PooledEngine engine;
    private AssetManager manager;

    public enum Components{
        PLAYER,
        TRANSFORM,
        MOVEMENTSTATS,
        PLAYERPART,
        TEXTURE,
        STATE

    }

    public EntityFactory(PooledEngine engine, AssetManager manager){
        this.engine = engine;
        this.manager = manager;
    }

    private Entity createPlayer(){
        Entity player = engine.createEntity();
        TransformComponent positionc = engine.createComponent(TransformComponent.class);
        MovementStatsComponent movementc = engine.createComponent(MovementStatsComponent.class);
        TextureComponent texturec = engine.createComponent(TextureComponent.class);
        CollisionPolygonComponent cpolygon = engine.createComponent(CollisionPolygonComponent.class);
        PlayerComponent playerc = engine.createComponent(PlayerComponent.class);
        player.add(positionc);
        player.add(movementc);
        player.add(texturec);
        player.add(playerc);
        player.add(cpolygon);
        return player;
    }

    private Entity createPlayerPart(){
        Entity entity = engine.createEntity();
        PlayerPartComponent part = engine.createComponent(PlayerPartComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        MovementStatsComponent movementc = engine.createComponent(MovementStatsComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        entity.add(part);
        entity.add(position);
        entity.add(movementc);
        entity.add(state);
        entity.add(texture);
        return entity;
    }

    private Entity createHardPoint(){
        Entity entity = engine.createEntity();
        HardPointComponent hardpoint = engine.createComponent(HardPointComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        MovementStatsComponent movementc = engine.createComponent(MovementStatsComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        entity.add(hardpoint);
        entity.add(position);
        entity.add(movementc);
        entity.add(state);
        entity.add(texture);
        return entity;
    }

    private Entity createEmplacement(){
        Entity entity = engine.createEntity();
        EmplacementComponent emplacement = engine.createComponent(EmplacementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        MovementStatsComponent movementc = engine.createComponent(MovementStatsComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        entity.add(emplacement);
        entity.add(position);
        entity.add(movementc);
        entity.add(state);
        entity.add(texture);
        return entity;
    }

    private Entity createWeapon(){
        Entity entity = engine.createEntity();
        TransformComponent position = engine.createComponent(TransformComponent.class);
        MovementStatsComponent movementc = engine.createComponent(MovementStatsComponent.class);
    //    TextureComponent texture = engine.createComponent(TextureComponent.class);
    //    AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
       // AttachmentPointComponent apc = engine.createComponent(AttachmentPointComponent.class);
        WeaponComponent wc = engine.createComponent(WeaponComponent.class);
        //ConnectorPoint cpc = engine.createComponent(ConnectorPoint.class);
        entity.add(position);
        entity.add(movementc);
        entity.add(state);
        //entity.add(apc);
        entity.add(wc);
       // entity.add(cpc);
       // entity.add(texture);
      //  entity.add(animation);
        return entity;
    }

    private Entity createWeaponPart(){
        Entity entity = engine.createEntity();
        WeaponPartComponent weaponPart = engine.createComponent(WeaponPartComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        MovementStatsComponent movementc = engine.createComponent(MovementStatsComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        entity.add(weaponPart);
        entity.add(texture);
        entity.add(position);
        entity.add(movementc);
        entity.add(state);
        return entity;
    }

    /**
    public Entity createTurret(){
        Entity entity = engine.createEntity();
        TransformComponent position = engine.createComponent(TransformComponent.class);
        MovementStatsComponent movementc = engine.createComponent(MovementStatsComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        AttachmentPointComponent apc = engine.createComponent(AttachmentPointComponent.class);
        WeaponComponent wc = engine.createComponent(WeaponComponent.class);
        ConnectorPoint cpc = engine.createComponent(ConnectorPoint.class);
        entity.add(position);
        entity.add(movementc);
        entity.add(state);
        entity.add(apc);
        entity.add(wc);
        entity.add(cpc);
        entity.add(texture);
        return entity;
    }
*/
    private Entity createBullet(){
        Entity entity = engine.createEntity();
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
      // texture.region = manager.get(BULLETS_ATLAS, TextureAtlas.class).findRegion("shell_small");
        BulletComponent bullet = engine.createComponent(BulletComponent.class);
      //  position.position.set(100,100,0);
        bullet.yVel = 5;
        entity.add(position);
        entity.add(texture);
        entity.add(state);
        entity.add(bullet);
        return entity;
    }

    public void removeEntity(Entity entity){
        engine.removeEntity(entity);
    }

    public Entity addEntity(EntityTemplate template){

        Entity entity = engine.createEntity();
        for (Components component : template.getComponents()){
          switch(component) {
              case PLAYER:
                    System.out.println("Added component Player");
                    PlayerComponent playerc = engine.createComponent(PlayerComponent.class);
                    entity.add(playerc);
                    break;
              case TRANSFORM:
                    System.out.println("Added component Transform");
                    TransformComponent positionc = engine.createComponent(TransformComponent.class);
                    entity.add(positionc);
                    break;
              case MOVEMENTSTATS:
                    System.out.println("Added component Movement");
                    MovementStatsComponent movementc = engine.createComponent(MovementStatsComponent.class);
                    entity.add(movementc);
                    break;
              case PLAYERPART:
                    System.out.println("Added component Player Part");
                    PlayerPartComponent part = engine.createComponent(PlayerPartComponent.class);
                    entity.add(part);
                    break;
              case TEXTURE:
                    System.out.println("Added component Texture");
                    TextureComponent texture = engine.createComponent(TextureComponent.class);
                    entity.add(texture);
                    break;
              case STATE:
                    System.out.println("Added component STATE");
                    StateComponent state = engine.createComponent(StateComponent.class);
                    entity.add(state);
                    break;
          }

        }

        CollisionPolygonComponent cpolygon = engine.createComponent(CollisionPolygonComponent.class);
        entity.add(cpolygon);

        engine.addEntity(entity);
        return entity;
    }
}
