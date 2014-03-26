package com.idk.game.graphics;

import com.idk.game.entity.projectile.Projectile;
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
    }
    
    public void clear()
    {
        for( int i = 0; i < pixels.length; i++ )
        {
            pixels[i] = 0;
        }
    }
    
    public void renderSheet( int xp, int yp, SpriteSheet sheet, boolean fixed )
    {
        if( fixed )
        {
            xp -= xOffset;
            yp -= yOffset;
        }
       
        for( int y = 0; y < sheet.HEIGHT; y++ )
        {
            int ya = y + yp;
            
            for( int x = 0; x < sheet.WIDTH; x++ )
            {
                int xa = x + xp;
                
                if( xa < 0 || xa >= width || ya < 0 || ya >= height ) continue;
                
                pixels[ xa + ya * width ] = sheet.pixels[ x + y * sheet.WIDTH ];
            }
        }
    }
    
    public void renderSprite( int xp, int yp, Sprite sprite, boolean fixed )
    {
        if( fixed )
        {
            xp -= xOffset;
            yp -= yOffset;
        }
       
        for( int y = 0; y < sprite.getHeight(); y++ )
        {
            int ya = y + yp;
            
            for( int x = 0; x < sprite.getWidth(); x++ )
            {
                int xa = x + xp;
                
                if( xa < 0 || xa >= width || ya < 0 || ya >= height ) continue;
                
                pixels[ xa + ya * width ] = sprite.pixels[ x + y * sprite.getWidth() ];
            }
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
    
    public void renderProjectile( int xp, int yp, Projectile projectile )
    {
        // Adjust for offset (when player moves)
        xp -= xOffset;
        yp -= yOffset;
        
        for( int y = 0; y < projectile.getSpriteSize(); y++ )
        {
            // Absalute y position (y position of sprite + offset)
            int ya = y + yp;
            
            for( int x = 0; x < projectile.getSpriteSize(); x++ )
            {
                // Absalute x position (x position of sprite + offset)
                int xa = x + xp;
                
                // Don't render what can't be seen in the screen's dimensions
                // (slight padding to allow smooth procedural tile rendering at boundaries)
                if( xa < -projectile.getSpriteSize() || xa >= width || ya < 0 || ya >= height ) break;
                
                // Keep the array index from going out of bounds.
                if( xa < 0 ) xa = 0;
                
                int color = projectile.getSprite().pixels[ x + y * projectile.getSpriteSize() ];
                
                // Check for pink (0xffff00ff). This color indicates transparency.
                if( color != 0xffff00ff )
                {
                    // Store the sprite's pixels into this screen's pixels
                    pixels[ xa + ya * width ] = color;
                }
            }
        }
    }
    
    public void renderMob( int xp, int yp, Sprite sprite, int flip )
    {
        // Adjust for offset (when player moves)
        xp -= xOffset;
        yp -= yOffset;
        
        for( int y = 0; y < 32; y++ )
        {
            // Absalute y position (y position of sprite + offset)
            int ya = y + yp;
            
            // Sprite Y flip?
            int ys = y;
            if ( flip == 2 || flip == 3 ) ys = 31 - y;
            
            for( int x = 0; x < 32; x++ )
            {
                // Absalute x position (x position of sprite + offset)
                int xa = x + xp;
                
                // Sprite X flip?
                int xs = x;
                if ( flip == 1 || flip == 3 ) xs = 31 - x;
                
                // Don't render what can't be seen in the screen's dimensions
                // (slight padding to allow smooth procedural tile rendering at boundaries)
                if( xa < -32 || xa >= width || ya < 0 || ya >= height ) break;
                
                // Keep the array index from going out of bounds.
                if( xa < 0 ) xa = 0;
                
                // Check for pink (0xffff00ff). This color indicates transparency.
                System.out.println( sprite );
                System.out.println( sprite.pixels );
                int color = sprite.pixels[ xs + ys * 32 ];               
                if( color != 0xffff00ff )
                {
                    // Store the sprite's pixels into this screen's pixels
                    pixels[ xa + ya * width ] = color;
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