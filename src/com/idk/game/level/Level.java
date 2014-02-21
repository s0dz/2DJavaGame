package com.idk.game.level;

import com.idk.game.graphics.Screen;
import com.idk.game.level.tile.Tile;

public class Level
{
    protected int width;
    protected int height;
    
    protected Tile[] tiles;
    protected int[] tilesInt;
    
    // This constructor randomly generates a level.
    public Level( int width, int height )
    {
        this.width = width;
        this.height = height;
        tilesInt = new int[ width * height ];
        generateLevel();
    }

    // This constructor loads a level from file.
    public Level( String path )
    {
        loadLevel( path );
        generateLevel();
    }
    
    protected void loadLevel( String path )
    {
        
    }
    
    protected void generateLevel()
    {
        
    }
            
    public void update()
    {
        
    }
    
    private void time()
    {
        
    }
    
    /**
     * Renders level in the passed in Screen instance based on the coordinates
     * of the Player.
     * 
     * @param xScroll - X coord of Player
     * @param yScroll - y coord of Player
     * @param screen - Instance of Screen
     */
    public void render( int xScroll, int yScroll, Screen screen )
    {
        // Set offsets based on position of Player
        screen.setOffset( xScroll, yScroll );
        
        // The left most and right most vertical lines converted from pixel
        // precision to tile precision.
        int x0 = xScroll >> 4;
        int x1 = ( xScroll + screen.width + 16 ) >> 4; // Pad right side of screen
        
        // The top most and bottom most horizontal lines converted from pixel
        // precision to tile precision.
        int y0 = yScroll >> 4;
        int y1 = ( yScroll + screen.height + 16 ) >> 4; // Pad bottom of screen
        
        // Using tile precision, render each tile
        for( int y = y0; y < y1; y++ )
        {
            for( int x = x0; x < x1; x++ )
            {
                // getTile( x, y ).render( x, y, screen );
                
                // Map out of bounds
                if( x + y * 16 < 0 || x + y * 16 >= 256 )
                {
                    Tile.voidTile.render( x, y, screen );
                    continue;
                }
                
                tiles[ x + y * 16 ].render( x, y, screen );
            }
        }
    }
    
    public Tile getTile( int x, int y )
    {
        // Map out of bounds
        if( x < 0 || y < 0 || x >= width || y >= height ) return Tile.voidTile;
        
        if( tilesInt[ x + y * width ] == 0 ) return Tile.grass;
        if( tilesInt[ x + y * width ] == 1 ) return Tile.flower;
        if( tilesInt[ x + y * width ] == 2 ) return Tile.rock;
        
        return Tile.voidTile;
    }
}