package com.idk.game.level;

import com.idk.game.entity.Entity;
import com.idk.game.graphics.Screen;
import com.idk.game.level.tile.Tile;
import java.util.List;
import java.util.ArrayList;

public class Level
{
    protected int width;
    protected int height;
    
    protected int[] tilesInt;
    
    protected int[] tiles;
    
    //TODO: tile_size
    
    private List<Entity> entities = new ArrayList<>();
    
    public static Level spawn = new SpawnLevel( "/levels/spawn.png" );
    
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
        for( int i = 0; i < entities.size(); i++ )
        {
            entities.get(i).update();
        }
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
                getTile( x, y ).render( x, y, screen );
            }
        }
        
        for( int i = 0; i < entities.size(); i++ )
        {
            entities.get(i).render( screen );
        }
    }
    
    public void add( Entity e )
    {
        entities.add( e );
    }
    
    // Grass = 0xff00FF00
    // Flower = 0xffFFFF00
    // Rock = 0xff7F7F00
    //
    // ( alpha channel = ff == 100% opaque )
    public Tile getTile( int x, int y )
    {
        // Map out of bounds
        if( x < 0 || y < 0 || x >= width || y >= height ) return Tile.voidTile;
        
        if( tiles[ x + y * width ] == Tile.col_spawn_floor ) return Tile.spawn_floor;
        if( tiles[ x + y * width ] == Tile.col_spawn_grass ) return Tile.spawn_grass;
        if( tiles[ x + y * width ] == Tile.col_spawn_hedge ) return Tile.spawn_hedge;
        if( tiles[ x + y * width ] == Tile.col_spawn_wall1 ) return Tile.spawn_wall1;
        if( tiles[ x + y * width ] == Tile.col_spawn_wall2 ) return Tile.spawn_wall2;
        if( tiles[ x + y * width ] == Tile.col_spawn_water ) return Tile.spawn_water;
        
        return Tile.voidTile;
    }
}