package com.idk.game.level.tile;

import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;

public class Tile
{
    public int x;
    public int y;
    public Sprite sprite;
    
    public static Tile voidTile = new VoidTile( Sprite.voidSprite );
    
    public static Tile grass = new GrassTile( Sprite.grass );
    public static Tile flower = new FlowerTile( Sprite.flower );
    public static Tile rock = new RockTile( Sprite.rock );
    
    public Tile( Sprite sprite )
    {
        this.sprite = sprite;
    }
    
    public void render( int x, int y, Screen screen )
    {
        
    }
    
    // Only needs overridden for collidable tiles
    public boolean solid()
    {
        return false;
    }
}