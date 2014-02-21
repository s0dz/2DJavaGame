package com.idk.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpawnLevel extends Level
{    
    public SpawnLevel( String path )
    {
        super( path );
    }
    
    @Override
    protected void loadLevel( String path )
    {
        try
        {
            BufferedImage image = ImageIO.read( SpawnLevel.class.getResource( path ) );
            
            // Probably don't need w and h variables...
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            
            tiles = new int[w * h];
            
            image.getRGB( 0, 0, w, h, tiles, 0, w );
        }
        catch( IOException e )
        {
            System.out.println( "Exception: Could not load level file!" );
            e.printStackTrace();
        }
    }
    
    @Override
    protected void generateLevel()
    {
        
    }
}