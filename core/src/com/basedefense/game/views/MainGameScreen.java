package com.basedefense.game.views;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.basedefense.game.controller.KeyboardController;
import com.basedefense.game.BaseDefense;
import com.basedefense.game.entity.factories.EntityFactory;
import com.basedefense.game.entity.factories.EntityConfigurationFactory;
import com.basedefense.game.entity.components.TransformComponent;
import com.basedefense.game.viewports.Viewport;
import com.basedefense.game.entity.systems.RenderingSystem;
import com.basedefense.game.entity.systems.PlayerControlSystem;




public class MainGameScreen implements Screen {
    private static final String TAG = MainGameScreen.class.getSimpleName();
    protected OrthographicCamera camera = null;
    private BaseDefense parent;

    private KeyboardController controller;
    private SpriteBatch sb;
    private PooledEngine engine;

    private EntityFactory entityFactory;
    private EntityConfigurationFactory entityConfiguration;

    private Viewport viewport;

    private Entity player;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;



    public MainGameScreen(BaseDefense spaceComponent1) {

        parent = spaceComponent1;
        controller = new KeyboardController();

        viewport = new Viewport();
        viewport.setupViewport(100,100);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, viewport.viewportWidth, viewport.viewportHeight);
        sb = new SpriteBatch();
        engine = new PooledEngine();
        entityFactory = new EntityFactory(engine, parent.manager);
        entityConfiguration = new EntityConfigurationFactory(parent.manager);
        addSystems(sb);
        addEntities();

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/testBeachMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1 /4f);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateTestCamera(delta);
        renderer.setView(camera);
        renderer.render();
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.setupViewport(100,100);
        camera.setToOrtho(false, viewport.viewportWidth, viewport.viewportHeight);
        engine.getSystem(RenderingSystem.class).getCamera().setToOrtho(false, viewport.viewportWidth, viewport.viewportHeight);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    private void addSystems(SpriteBatch sb){
        RenderingSystem renderingSystem = new RenderingSystem(sb);
        renderingSystem.setCamera(camera);
        renderingSystem.setWidthRatio( viewport.viewportWidth / viewport.physicalWidth);
        renderingSystem.setHeightRatio(viewport.viewportHeight / viewport.physicalHeight);
      //  engine.addSystem(new PlayerControlSystem(controller,entityFactory));
      //  engine.addSystem(new PlayerPartsLocationUpdateSystem());
      //  engine.addSystem(new HardPointLocationUpdateSystem());
      //  engine.addSystem(new EmplacementLocationUpdateSystem());
      //  engine.addSystem(new EmplacementControlSystem(controller,entityFactory));
      //  engine.addSystem(new WeaponLocationUpdateSystem());

      //  engine.addSystem(new AttachmentLocationUpdateSystem());
        // engine.addSystem(new ConectorPointSystem());
      //  engine.addSystem(new WeaponControlSystem(controller,entityFactory));
      //  engine.addSystem(new BulletSystem());
      //  engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
      //  engine.addSystem(new EntityRemovalSystem(entityFactory));
    }

    private void addEntities(){
        player = entityFactory.addEntity(EntityFactory.EntityType.PLAYER);
        entityConfiguration.setPlayer(player);

        Entity playerPart1 = entityFactory.addEntity(EntityFactory.EntityType.PLAYER_PART);
        entityConfiguration.setPlayerPart(playerPart1);
        entityConfiguration.attachPlayerPart(player,playerPart1,0,0,-1);

        Entity hardPoint1 = entityFactory.addEntity(EntityFactory.EntityType.HARDPOINT);
        entityConfiguration.setHardPoint(hardPoint1, "medium");
        entityConfiguration.attachHardPointToPart(playerPart1,hardPoint1,32,32,-2);

        Entity emplacement1 = entityFactory.addEntity(EntityFactory.EntityType.EMPLACEMENT);
        entityConfiguration.setEmplacement(emplacement1,"medium");
        entityConfiguration.attachEmplacementToHardPoint(hardPoint1,emplacement1,0,0,-4);
        engine.getSystem(PlayerControlSystem.class).playerRotationSignal.add(emplacement1.getComponent(TransformComponent.class));

       // Entity weapon1 = entityFactory.addEntity(EntityFactory.EntityType.WEAPON);
       // entityConfiguration.setWeapon(weapon1);
       // entityConfiguration.attachWeaponToEmplacement(emplacement1,weapon1,18,33,-3);

      //   Entity weaponPart1 = entityFactory.addEntity(EntityFactory.EntityType.WEAPON_PART);
      //   entityConfiguration.setWeaponPart(weaponPart1);
      //   entityConfiguration.addWeaponPartToEmplacement(emplacement1,weaponPart1,16,15,-3);
      //  entityConfiguration.addWeaponPartToWeapon(weapon1,weaponPart1,0,0,-3);



      //  Entity playerPart2 = entityFactory.addEntity(EntityFactory.EntityType.PLAYER_PART);
      //  entityConfiguration.setPlayerPart(playerPart2);
      //  entityConfiguration.attachPlayerPart(player,playerPart2,64,0,-1);

      //  Entity hardPoint21 = entityFactory.addEntity(EntityFactory.EntityType.HARDPOINT);
      //  entityConfiguration.setHardPoint(hardPoint21, "small");
      //  entityConfiguration.attachHardPointToPart(playerPart2,hardPoint21,16,16,-2);

      //  Entity hardPoint22 = entityFactory.addEntity(EntityFactory.EntityType.HARDPOINT);
      //  entityConfiguration.setHardPoint(hardPoint22, "small");
      //  entityConfiguration.attachHardPointToPart(playerPart2,hardPoint22,32,48,-2);

      //  Entity hardPoint23 = entityFactory.addEntity(EntityFactory.EntityType.HARDPOINT);
      //  entityConfiguration.setHardPoint(hardPoint23, "small");
      //  entityConfiguration.attachHardPointToPart(playerPart2,hardPoint23,48,16,-2);

       // Entity playerWeapon = entityFactory.addEntity(EntityFactory.EntityType.WEAPON);
       // entityConfiguration.setWeapon(playerWeapon);
       // entityConfiguration.setPlayerAttachments(player, playerWeapon,-5,25,-1);
       // entityConfiguration.setPlayerAttachments(player, playerWeapon,0,0,-1);
        // entityConfiguration.setConnectorPoint(playerWeapon,13,15,2);
       // Entity playerWeapon2 = entityFactory.addEntity(EntityFactory.EntityType.WEAPON);
       // entityConfiguration.setWeapon(playerWeapon2);
       // entityConfiguration.setPlayerAttachments(player, playerWeapon2,34,0,-1);
        //    entityConfiguration.setConnectorPoint(playerWeapon,13,15,2);
       // Entity playerWeapon3 = entityFactory.addEntity(EntityFactory.EntityType.WEAPON);
       // entityConfiguration.setWeapon(playerWeapon3);
       // entityConfiguration.setPlayerAttachments(player, playerWeapon3,21,65,1);
        //    entityConfiguration.setConnectorPoint(playerWeapon,0,0,0);
      //  Entity bigTurret1 = entityFactory.addEntity(EntityFactory.EntityType.TURRET);
      //  entityConfiguration.setTurret(bigTurret1);
      //  entityConfiguration.setPlayerAttachments(player,bigTurret1,64,116,-1);

      //  Entity smallTurret1 = entityFactory.addEntity(EntityFactory.EntityType.TURRET);
      //  entityConfiguration.setMissileTurret(smallTurret1);
      //  entityConfiguration.setPlayerAttachments(player,smallTurret1,64,166,-1);

    }

    private void updateCameraWithLerp (float delta){
        float lerp = 0.1f;
        camera.position.x += (player.getComponent(TransformComponent.class).position.x - camera.position.x) * lerp * delta;
        camera.position.y += (player.getComponent(TransformComponent.class).position.y - camera.position.y) * lerp * delta;
        camera.update();
    }

    private void updateCamera(float delta){
        camera.position.x += (player.getComponent(TransformComponent.class).position.x - camera.position.x)  * delta;
        camera.position.y += (player.getComponent(TransformComponent.class).position.y - camera.position.y)  * delta;
        camera.update();
    }

    private void updateTestCamera(float delta){
        camera.position.x += (player.getComponent(TransformComponent.class).position.x  - camera.position.x);
        camera.position.y += (player.getComponent(TransformComponent.class).position.y  - camera.position.y);
        camera.update();
    }


}
