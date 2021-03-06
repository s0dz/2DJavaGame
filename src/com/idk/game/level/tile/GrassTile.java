package com.idk.game.level.tile;

import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;

public class GrassTile extends Tile
{
    public GrassTile( Sprite sprite )
    {
        super( sprite );
    }
    
    @Override
    public void render( int x, int y, Screen screen )
    {
        // Convert back to pixel precision
        screen.renderTile( x << 4, y << 4 , this );
    }
}