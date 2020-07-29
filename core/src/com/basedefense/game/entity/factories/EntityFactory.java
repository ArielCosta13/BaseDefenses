package com.basedefense.game.entity.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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


//import static com.basedefense.game.loaders.Assets.BULLETS_ATLAS;

public class EntityFactory {

    private PooledEngine engine;
    private AssetManager manager;

    public static enum EntityType{
        PLAYER,
        PLAYER_PART,
        HARDPOINT,
        EMPLACEMENT,
        WEAPON,
        WEAPON_PART,
        TURRET,
        BULLET
    }

    public EntityFactory(PooledEngine engine, AssetManager manager){
        this.engine = engine;
        this.manager = manager;
    }

    /*
       This method does not add the Entity to the engine
     */
    public Entity createEntity(EntityType type){
        switch (type){
            case PLAYER: return createPlayer();
            case PLAYER_PART: return createPlayerPart();
            case HARDPOINT: return createHardPoint();
            case EMPLACEMENT: return createEmplacement();
            case WEAPON: return createWeapon();
            case WEAPON_PART: return createWeaponPart();
            //case TURRET: return createTurret();
            case BULLET: return createBullet();
        }
        return null;

    }

    /*
      This method add the entity to the engine
     */
    public Entity addEntity(EntityType type){
        switch (type){
            case PLAYER: return addPlayer();
            case PLAYER_PART: return addPlayerPart();
            case HARDPOINT: return addHardPoint();
            case EMPLACEMENT: return addEmplacement();
            case WEAPON: return addWeapon();
          //  case TURRET: return addTurret();
            case BULLET: return addBullet();
            case WEAPON_PART: return addWeaponPart();
        }
        return null;

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

    private Entity addPlayer(){
        Entity player = createEntity(EntityType.PLAYER);
        engine.addEntity(player);
        return player;
    }

    private Entity addPlayerPart(){
        Entity player_part = createEntity(EntityType.PLAYER_PART);
        engine.addEntity(player_part);
        return player_part;
    }

    private Entity addHardPoint(){
        Entity hard_point = createEntity(EntityType.HARDPOINT);
        engine.addEntity(hard_point);
        return hard_point;
    }

    private Entity addEmplacement(){
        Entity emplacement = createEntity(EntityType.EMPLACEMENT);
        engine.addEntity(emplacement);
        return emplacement;
    }

    private Entity addWeapon(){
        Entity weapon = createEntity(EntityType.WEAPON);
        engine.addEntity(weapon);
        return weapon;
    }

    private Entity addWeaponPart(){
        Entity weaponpart = createEntity(EntityType.WEAPON_PART);
        engine.addEntity(weaponpart);
        return weaponpart;
    }

    private Entity addBullet(){
        Entity bullet = createEntity(EntityType.BULLET);
        engine.addEntity(bullet);
        return bullet;
    }


    private Entity addTurret(){
        Entity turret = createEntity(EntityType.WEAPON);
        engine.addEntity(turret);
        return turret;
    }

    public void removeEntity(Entity entity){
        engine.removeEntity(entity);
    }

}
