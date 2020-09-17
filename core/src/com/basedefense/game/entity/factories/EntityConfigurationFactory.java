package com.basedefense.game.entity.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.basedefense.game.entity.components.TextureComponent;
import com.basedefense.game.entity.components.TransformComponent;
import com.basedefense.game.entity.components.MovementStatsComponent;
import com.basedefense.game.entity.components.WeaponComponent;

import com.basedefense.game.entity.components.PlayerPartComponent;
import com.basedefense.game.entity.components.HardPointComponent;
import com.basedefense.game.entity.components.EmplacementComponent;
import com.basedefense.game.entity.components.StateComponent;
import com.basedefense.game.entity.components.AttachmentPointComponent;
import com.basedefense.game.entity.components.ConnectorPoint;

import com.basedefense.game.entity.components.WeaponPartComponent;
import com.basedefense.game.templates.PlayerPartConfigurationTemplate;

import static com.basedefense.game.loaders.Assets.*;

public class EntityConfigurationFactory {
  private AssetManager manager;

  public EntityConfigurationFactory(AssetManager manager){
    this.manager = manager;
  }

  public void setPlayer(Entity player){
    //player.getComponent(TextureComponent.class).setTexture(manager.get(BASE_PARTS_ATLAS, TextureAtlas.class)
    //        .findRegion("basepart_1x1_bottom"));
  //  player.getComponent(TransformComponent.class).position.set(100 - player.getComponent(TextureComponent.class).center.x,
  //          100 - player.getComponent(TextureComponent.class).center.x,0);
    player.getComponent(TransformComponent.class).position.set(0, 0 ,0);
    player.getComponent(TransformComponent.class).rotation = 0;
    player.getComponent(TransformComponent.class).scale = new Vector2(1.0f, 1.0f);
    setPlayerMovementStats(player);
   // player.getComponent(TransformComponent.class).isHidden = true;
  }

    public void setPlayerPart(Entity playerPart, PlayerPartConfigurationTemplate ppconfig ){
        playerPart.getComponent(TextureComponent.class).setTexture(manager.get(BASE_PARTS_ATLAS, TextureAtlas.class)
                .findRegion(ppconfig.getTexture()));
      //  playerPart.getComponent(StateComponent.class).set(StateComponent.IDLE);
        setPlayerPartMovementStats(playerPart);
        // weapon.getComponent(TransformComponent.class).isHidden = true;
    }

    public void setHardPoint(Entity hardPoint, String size){
      if (size == "medium")
        hardPoint.getComponent(TextureComponent.class).setTexture(manager.get(BASE_PARTS_ATLAS, TextureAtlas.class)
                .findRegion("weapon_hardpoint_turret_medium_open"));
        if (size == "small")
            hardPoint.getComponent(TextureComponent.class).setTexture(manager.get(BASE_PARTS_ATLAS, TextureAtlas.class)
                    .findRegion("weapon_hardpoint_turret_small_open"));
      hardPoint.getComponent(StateComponent.class).set(StateComponent.IDLE);
        setHardPointMovementStats(hardPoint);
        // weapon.getComponent(TransformComponent.class).isHidden = true;
    }

    public void setEmplacement(Entity emplacement, String size){
        if (size == "medium")
            emplacement.getComponent(TextureComponent.class).setTexture(manager.get(WEAPON_ATLAS, TextureAtlas.class)
                    .findRegion("emplacement_turret_cannon_medium_mk1"));
        if (size == "small")
            emplacement.getComponent(TextureComponent.class).setTexture(manager.get(WEAPON_ATLAS, TextureAtlas.class)
                    .findRegion("emplacement_turret_cannon_medium_mk1"));
        emplacement.getComponent(StateComponent.class).set(StateComponent.IDLE);
        setEmplacementMovementStats(emplacement);
        // weapon.getComponent(TransformComponent.class).isHidden = true;
    }

    public void setWeaponPart(Entity weaponPart){
        weaponPart.getComponent(TextureComponent.class).setTexture(manager.get(WEAPON_ATLAS, TextureAtlas.class)
                .findRegion("cannon_ammo_chamber_01_mk1"));
       // weaponPart.getComponent(TextureComponent.class).region = manager.get(CANNON_MEDIUM, TextureAtlas.class)
       //            .findRegion("cannon_01_mk1");
        weaponPart.getComponent(StateComponent.class).set(StateComponent.IDLE);
        setWeaponPartMovementStats(weaponPart);
        // weapon.getComponent(TransformComponent.class).isHidden = true;
    }


  public void setPlayerMovementStats(Entity player){
      MovementStatsComponent msc =  player.getComponent(MovementStatsComponent.class);
      msc.rotationSpeed = 1.8f;
      msc.velX = 2.0f;
      msc.velY = 2.0f;
  }

  public void setWeapon(Entity weapon){
      //  Animation<TextureRegion> shooting;
      //  shooting = new Animation<TextureRegion>(0.133f, manager.get(CANNON_MEDIUM_ANIMATED,TextureAtlas.class)
       //         .getRegions(), Animation.PlayMode.LOOP);
     //   weapon.getComponent(TextureComponent.class).region = manager.get(CANNON_MEDIUM, TextureAtlas.class)
     //           .findRegion("cannon_01_mk1");
     //   weapon.getComponent(AnimationComponent.class).animations.put(StateComponent.SHOOTING,shooting);
        weapon.getComponent(StateComponent.class).set(StateComponent.IDLE);
        setWeaponMovementStats(weapon);
        // weapon.getComponent(TransformComponent.class).isHidden = true;
    }

    public void setTurret(Entity turret){
        turret.getComponent(TextureComponent.class).region = manager.get(WEAPON_ATLAS, TextureAtlas.class)
                .findRegion("turret_01_mk1");
        turret.getComponent(StateComponent.class).set(StateComponent.IDLE);
        setWeaponMovementStats(turret);
        // weapon.getComponent(TransformComponent.class).isHidden = true;
    }

    public void setMissileTurret(Entity turret){
        Animation<TextureRegion> shooting;
        turret.getComponent(TextureComponent.class).region = manager.get(WEAPON_ATLAS, TextureAtlas.class)
                .findRegion("turret_03_mk1");
        turret.getComponent(StateComponent.class).set(StateComponent.SHOOTING);
        setWeaponMovementStats(turret);
        // weapon.getComponent(TransformComponent.class).isHidden = true;
    }

  public void setWeaponMovementStats(Entity weapon){
    MovementStatsComponent msc =  weapon.getComponent(MovementStatsComponent.class);
    msc.rotationSpeed = 0.0f;
  }

    public void setPlayerPartMovementStats(Entity part){
      MovementStatsComponent msc =  part.getComponent(MovementStatsComponent.class);
      msc.rotationSpeed = 0.0f;
    }

    public void setHardPointMovementStats(Entity part){
        MovementStatsComponent msc =  part.getComponent(MovementStatsComponent.class);
        msc.rotationSpeed = 0.0f;
    }

    public void setEmplacementMovementStats(Entity weapon){
        MovementStatsComponent msc =  weapon.getComponent(MovementStatsComponent.class);
        msc.rotationSpeed = 1.8f;
    }

    public void setWeaponPartMovementStats(Entity weaponpart){
        MovementStatsComponent msc =  weaponpart.getComponent(MovementStatsComponent.class);
        msc.rotationSpeed = 0.0f;
    }

  /**
   Offsetz is for locate the weapon above or below the player. Z is calculated playerz + offsetz
   */
  public void setPlayerAttachments(Entity player, Entity entityToAttach,int  offsetx, int offsety, int offsetz) {
    AttachmentPointComponent apc = entityToAttach.getComponent(AttachmentPointComponent.class);
    TransformComponent eta = entityToAttach.getComponent(TransformComponent.class);
    TransformComponent pt = player.getComponent(TransformComponent.class);
    eta.position.x = pt.position.x - entityToAttach.getComponent(TextureComponent.class).center.x + offsetx;
    eta.position.y = pt.position.y - entityToAttach.getComponent(TextureComponent.class).center.y + offsety;
    eta.position.z = pt.position.z + offsetz;
    if (apc != null) {
      apc.setEntityAttachmentPointOffSet(new Vector3(offsetx, offsety, offsetz));
      apc.attachEntityTo(player);
    }
  }

    /**
     Offsetz is for locate the weapon above or below the player. Z is calculated playerz + offsetz
     Note that we add the center to the offset. This is done because normally the offset is calculated
     to the center of the texture, and for parts me want to user the left corner.
     With normal offset the part will be located with its center in the offset.
     With the alteration now the left corner of the part is draw in the offset
     */
    public void attachPlayerPart(Entity player, Entity part,int  offsetx, int offsety, int offsetz) {
        PlayerPartComponent ppc = part.getComponent(PlayerPartComponent.class);
        TransformComponent eta = part.getComponent(TransformComponent.class);
        TransformComponent pt = player.getComponent(TransformComponent.class);
        Vector2 part_center =  part.getComponent(TextureComponent.class).center;
     //   System.out.println("Center:" + part_center.x);
        eta.position.x = pt.position.x + offsetx;
        eta.position.y = pt.position.y + offsety;
        eta.position.z = pt.position.z + offsetz;
        if (ppc != null) {
            ppc.setEntityAttachmentPointOffSet(new Vector3(offsetx + part_center.x, offsety + part_center.y, offsetz));
            ppc.attachEntityTo(player);
        }
    }

    /**
     Offsetz is for locate the weapon above or below the player. Z is calculated playerz + offsetz

     */
    public void attachHardPointToPart(Entity part, Entity hardpoint,int  offsetx, int offsety, int offsetz) {
        HardPointComponent hpc = hardpoint.getComponent(HardPointComponent.class);
        TransformComponent eta = hardpoint.getComponent(TransformComponent.class);
        TransformComponent pt = part.getComponent(TransformComponent.class);
        Vector2 part_center =  part.getComponent(TextureComponent.class).center;
       // System.out.println("Center:" + part_center.x);
        eta.position.x = pt.position.x  + offsetx;
        eta.position.y = pt.position.y  + offsety;
        eta.position.z = pt.position.z + offsetz;
        if (hpc != null) {
            hpc.setEntityAttachmentPointOffSet(new Vector3(offsetx, offsety, offsetz));
            hpc.attachEntityTo(part);
        }
    }

    /**
     Offsetz is for locate the weapon above or below the player. Z is calculated playerz + offsetz

     */
    public void attachEmplacementToHardPoint(Entity hardpoint, Entity emplacement,int  offsetx, int offsety, int offsetz) {
        EmplacementComponent ec = emplacement.getComponent(EmplacementComponent.class);
        TransformComponent eta = emplacement.getComponent(TransformComponent.class);
        TransformComponent pt = hardpoint.getComponent(TransformComponent.class);
        Vector2 part_center =  hardpoint.getComponent(TextureComponent.class).center;
      //  System.out.println("Center:" + part_center.x);
        eta.position.x = pt.position.x  + offsetx;
        eta.position.y = pt.position.y  + offsety;
        eta.position.z = offsetz;
        if (ec != null) {
            ec.setEntityAttachmentPointOffSet(new Vector3(offsetx, offsety, offsetz));
            ec.attachEntityTo(hardpoint);
        }
    }

    /**
     Offsetz is for locate the weapon above or below the player. Z is calculated playerz + offsetz

     */
    public void addWeaponPartToEmplacement(Entity emplacement, Entity weaponpart,int offsetx, int offsety, int offsetz) {
        WeaponPartComponent wpc = weaponpart.getComponent(WeaponPartComponent.class);
        TransformComponent eta = weaponpart.getComponent(TransformComponent.class);
        TransformComponent pt = emplacement.getComponent(TransformComponent.class);
        Vector2 emplacement_center =  emplacement.getComponent(TextureComponent.class).center;
        //System.out.println("Center:" + part_center.x);
        eta.position.x = pt.position.x  + offsetx - emplacement_center.x;
        eta.position.y = pt.position.y  + offsety - emplacement_center.y;
        eta.position.z = offsetz;
        if (wpc != null) {
            wpc.setPartOffSet(new Vector3(offsetx, offsety, offsetz));
            wpc.addPartToWeapon(emplacement);
        }
    }

    /**
     Offsetz is for locate the weapon above or below the player. Z is calculated playerz + offsetz

     */
    public void attachWeaponToEmplacement(Entity emplacement, Entity weapon,int offsetx, int offsety, int offsetz) {
        WeaponComponent wc = weapon.getComponent(WeaponComponent.class);
        TransformComponent eta = weapon.getComponent(TransformComponent.class);
        TransformComponent pt = emplacement.getComponent(TransformComponent.class);
        Vector2 part_center =  emplacement.getComponent(TextureComponent.class).center;
        //System.out.println("Center:" + part_center.x);
        eta.position.x = pt.position.x  + offsetx;
        eta.position.y = pt.position.y  + offsety;
        eta.position.z = offsetz;
        if (wc != null) {
            wc.setWeaponPointOffSet(new Vector3(offsetx, offsety, offsetz));
            wc.attachWeaponTo(emplacement);
        }
    }




    public void setConnectorPoint(Entity entity, int  offsetx, int offsety, int offsetz){
      ConnectorPoint cp = entity.getComponent(ConnectorPoint.class);
      if (cp != null){
        cp.offset = new Vector3(offsetx,offsety,offsetz);
      }
    }

  }

