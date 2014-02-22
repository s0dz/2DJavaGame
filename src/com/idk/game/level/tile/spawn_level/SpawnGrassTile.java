package com.idk.game.level.tile.spawn_level;

import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;
import com.idk.game.level.tile.Tile;

public class SpawnGrassTile extends Tile
{
    public SpawnGrassTile( Sprite sprite )
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