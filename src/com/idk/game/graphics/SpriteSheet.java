package com.idk.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet
{
    private String path;
    public final int SIZE;
    public final int WIDTH;
    public final int HEIGHT;
    public int[] pixels;
    
    public static SpriteSheet tiles = new SpriteSheet( "/textures/sheets/spritesheet.png", 256 ); 
    public static SpriteSheet spawn_level = new SpriteSheet( "/textures/sheets/spawn_level.png", 48 );
    public static SpriteSheet projectile_test = new SpriteSheet( "/textures/sheets/projectiles/test.png", 48 );
        
    public static SpriteSheet player = new SpriteSheet( "/textures/sheets/player_sheet.png", 128, 96 );
    
    public SpriteSheet( SpriteSheet sheet, int x, int y, int width, int height, int spriteSize )
    {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
    }
    
    public SpriteSheet( String path, int size )
    {
        this.path = path;
        SIZE = size;
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[ SIZE * SIZE ];
        load();
    }
    
    public SpriteSheet( String path, int width, int height )
    {
        this.path = path;
        SIZE = -1;
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[ WIDTH * HEIGHT ];
        load();
    }
    
    private void load()
    {
        try
        {
            BufferedImage image = ImageIO.read( SpriteSheet.class.getResource( path ) );
        
            int w = image.getWidth();
            int h = image.getHeight();
            
            image.getRGB( 0, 0, w, h, pixels, 0, w );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
}