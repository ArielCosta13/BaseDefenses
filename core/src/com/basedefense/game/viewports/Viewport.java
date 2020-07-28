package com.basedefense.game.viewports;

import com.badlogic.gdx.Gdx;

public class Viewport {

    private static final String TAG = Viewport.class.getSimpleName();

    public static float viewportWidth;
    public static float viewportHeight;
    public static float virtualWidth;
    public static float virtualHeight;
    public static float physicalWidth;
    public static float physicalHeight;
    public static float aspectRatio;

    public void setupViewport(int width, int height){
        //Make the viewport a percentage of the total display area
        virtualWidth = width;
        virtualHeight = height;

        //Current viewport dimensions
        viewportWidth = virtualWidth;
        viewportHeight = virtualHeight;

        //pixel dimensions of display
        physicalWidth = Gdx.graphics.getWidth();
        physicalHeight = Gdx.graphics.getHeight();

        //aspect ratio for current viewport
        aspectRatio = (virtualWidth / virtualHeight);

        //update viewport if there could be skewing
        if( physicalWidth / physicalHeight >= aspectRatio){
            //Letterbox left and right
            viewportWidth = viewportHeight * (physicalWidth/ physicalHeight);
            viewportHeight = virtualHeight;
        }else{
            //letterbox above and below
            viewportWidth = virtualWidth;
            viewportHeight = viewportWidth * (physicalHeight/ physicalWidth);
        }

        Gdx.app.debug(TAG, "WorldRenderer: virtual: (" + virtualWidth + "," + virtualHeight + ")" );
        Gdx.app.debug(TAG, "WorldRenderer: viewport: (" + viewportWidth + "," + viewportHeight + ")" );
        Gdx.app.debug(TAG, "WorldRenderer: physical: (" + physicalWidth + "," + physicalHeight + ")" );
        Gdx.app.debug(TAG, "WorldRenderer: ratio: (" + viewportWidth / physicalWidth + "," + viewportHeight / physicalHeight + ")" );
    }
}
