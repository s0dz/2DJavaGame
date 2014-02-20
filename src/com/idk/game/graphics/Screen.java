package com.idk.game.graphics;

import com.idk.game.level.tile.Tile;
import java.util.Random;

public class Screen
{
    public int width;
    public int height;
    public int[] pixels;
    
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    
    public int xOffset;
    public int yOffset;
    
    public int[] tiles = new int[ MAP_SIZE * MAP_SIZE ];
    
    private Random random = new Random();
    
    public Screen( int width, int height )
    {
        this.width = width;
        this.height = height;
        pixels = new int[ width * height ];
        
        for( int i = 0; i < MAP_SIZE * MAP_SIZE; i++ )
        {
            tiles[i] = random.nextInt( 0xffffff );
        }
        
        // Temp - Just for finding the root of the map (Top Left)
        tiles[0] = 0;
    }
    
    public void clear()
    {
        for( int i = 0; i < pixels.length; i++ )
        {
            pixels[i] = 0;
        }
    }
    
    public void renderTile( int xp, int yp, Tile tile )
    {
        // Adjust for offset (when player moves)
        xp -= xOffset;
        yp -= yOffset;
        
        for( int y = 0; y < tile.sprite.SIZE; y++ )
        {
            // Absalute y position (y position of sprite + offset)
            int ya = y + yp;
            
            for( int x = 0; x < tile.sprite.SIZE; x++ )
            {
                // Absalute x position (x position of sprite + offset)
                int xa = x + xp;
                
                // Don't render what can't be seen in the screen's dimensions
                // (slight padding to allow smooth procedural tile rendering at boundaries)
                if( xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height ) break;
                
                // Keep the array index from going out of bounds.
                if( xa < 0 ) xa = 0;
                
                // Store the sprite's pixels into this screen's pixels
                pixels[ xa + ya * width ] = tile.sprite.pixels[ x + y * tile.sprite.SIZE ];
            }
        }
    }
    
    public void renderPlayer( int xp, int yp, Sprite sprite )
    {
        // Adjust for offset (when player moves)
        xp -= xOffset;
        yp -= yOffset;
        
        for( int y = 0; y < 16; y++ )
        {
            // Absalute y position (y position of sprite + offset)
            int ya = y + yp;
            
            for( int x = 0; x < 16; x++ )
            {
                // Absalute x position (x position of sprite + offset)
                int xa = x + xp;
                
                // Don't render what can't be seen in the screen's dimensions
                // (slight padding to allow smooth procedural tile rendering at boundaries)
                if( xa < -16 || xa >= width || ya < 0 || ya >= height ) break;
                
                // Keep the array index from going out of bounds.
                if( xa < 0 ) xa = 0;
                
                int col = sprite.pixels[ x + y * 16 ];
                
                if( col != 0xffff00ff )
                {
                    // Store the sprite's pixels into this screen's pixels
                    pixels[ xa + ya * width ] = col;
                }
            }
        }
    }
    
    public void setOffset( int xOffset, int yOffset )
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}