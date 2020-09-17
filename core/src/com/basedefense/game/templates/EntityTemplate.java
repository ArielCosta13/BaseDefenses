package com.basedefense.game.templates;

import com.basedefense.game.entity.factories.EntityFactory;

import java.util.ArrayList;

public class EntityTemplate {

    private String type;
    ArrayList components = new ArrayList<EntityFactory.Components>();

    public void setType(String type){ this.type = type;}

    public void addComponent (EntityFactory.Components component){ components.add(component);}

    public boolean HasComponent(EntityFactory.Components component) {
        return components.contains(component);
    }

    public ArrayList<EntityFactory.Components> getComponents(){
        return components;
    }
}
