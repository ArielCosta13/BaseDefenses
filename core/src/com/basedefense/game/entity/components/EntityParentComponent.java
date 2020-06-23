package com.basedefense.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 *This components says if the entity has a parent or related
 * entity. Example: a Turret has a  parentcomponent that
 * tell from which ship belongs
 */
public class EntityParentComponent implements Component {
       public Entity parentEntity;
}


