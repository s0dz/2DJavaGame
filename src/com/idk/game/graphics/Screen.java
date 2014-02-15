package com.idk.game.graphics;

import java.util.Random;

public class Screen
{
    private int width;
    private int height;
    public int[] pixels;
    
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    
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
    
    public void render( int xOffSet, int yOffSet )
    {   
        for( int y = 0; y < height; y++ )
        {
            int yp = y + yOffSet;
            
            if( yp < 0 || yp >= height ) continue;
            
            for( int x = 0; x < width; x++ )
            {
                int xp = x + xOffSet;
                
                if( xp < 0 || xp >= width ) continue;
                
                pixels[ xp + yp * width ] = Sprite.grass.pixels[ ( x & 15 ) + ( y & 15) * Sprite.grass.SIZE ];
            }
        }
    }
}