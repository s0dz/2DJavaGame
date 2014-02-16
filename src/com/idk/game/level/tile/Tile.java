package com.idk.game.level.tile;

import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;

public class Tile
{
    public int x;
    public int y;
    public Sprite sprite;
    
    public Tile( Sprite sprite )
    {
        this.sprite = sprite;
    }
    
    public void render( int x, int y, Screen screen )
    {
        
    }
    
    public boolean solid()
    {
        return false;
    }
}