package com.basedefense.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.basedefense.game.entity.components.TransformComponent;
import com.basedefense.game.entity.components.TextureComponent;
import com.basedefense.game.entity.systems.tools.ZComparator;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    private SpriteBatch batch;
    private ShapeRenderer shaper;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera cam;

    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<TransformComponent> transformM;

    private float heightRatio;
    private float widthRatio;

    @SuppressWarnings("unchecked")
	public RenderingSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());
        textureM = ComponentMapper.getFor(TextureComponent.class);
        transformM = ComponentMapper.getFor(TransformComponent.class);
        renderQueue = new Array<Entity>();
        shaper = new ShapeRenderer();
        this.batch = batch;
        comparator = new ZComparator();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        heightRatio = 1;
        widthRatio = 1;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderQueue.sort(comparator);
       // cam.update();
        shaper.setProjectionMatrix(cam.combined);
        shaper.begin(ShapeRenderer.ShapeType.Line);
        shaper.line(-1000,0,1000,0);
        shaper.line(0,-1000,0,1000);
        for (int i = -1000; i<= 1000; i+=50){
            shaper.line(-10,i,10,i);
            shaper.line(i,-10,i,10);
        }
        for (Entity entity : renderQueue) {
            TextureComponent tex = textureM.get(entity);
            TransformComponent t = transformM.get(entity);
            Rectangle bounds = new Rectangle(0, 0, tex.region.getRegionWidth()* widthRatio,
                    tex.region.getRegionHeight()* heightRatio);
            Polygon polygon = new Polygon(new float[]{t.position.x,t.position.y,
                    t.position.x + bounds.width,t.position.y,
                    t.position.x + bounds.width,t.position.y + bounds.height,
                    t.position.x,t.position.y + bounds.height});
            polygon.setOrigin( t.position.x +tex.region.getRegionWidth()/2.0f,
                    t.position.y + tex.region.getRegionHeight()/2.0f);
            polygon.setRotation(t.rotation);
            Color color = Color.CYAN;
            shaper.setColor(color);
            shaper.polygon(polygon.getTransformedVertices());
         }
        shaper.end();
        batch.setProjectionMatrix(cam.combined);
       // batch.enableBlending();
        batch.begin();
          for (Entity entity : renderQueue) {
            TextureComponent tex = textureM.get(entity);
            TransformComponent t = transformM.get(entity);

            if (tex.region == null || t.isHidden) {
                continue;
            }
            float width = tex.region.getRegionWidth() * widthRatio ;
            float height = tex.region.getRegionHeight() * heightRatio;
            float originX;
            float originY;
         //   ConnectorPoint cp = entity.getComponent(ConnectorPoint.class);
         //   if (entity.getComponent(ConnectorPoint.class)!= null){
         //       originX = cp.offset.x;
         //       originY = cp.offset.y;
          //  } else {
                 originX = width/2.0f;
                 originY = height/2.0f;
            //}
            batch.draw(tex.region, t.position.x * widthRatio, t.position.y * heightRatio,  originX,
                    originY, width , height , t.scale.x , t.scale.y , t.rotation);
          }
        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return cam;
    }

    public void setCamera(OrthographicCamera camera){
        cam = camera;
    }

    public void setWidthRatio(float ratio){
        widthRatio = ratio;
    }

    public void setHeightRatio(float ratio){
        heightRatio = ratio;
    }


}