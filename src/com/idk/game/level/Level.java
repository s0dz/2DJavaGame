package com.idk.game.level;

import com.idk.game.graphics.Screen;

public class Level
{
    protected int width;
    protected int height;
    protected int[] tiles;
    
    // This constructor randomly generates a level.
    public Level( int width, int height )
    {
        this.width = width;
        this.height = height;
        tiles = new int[ width * height ];
        generateLevel();
    }

    // This constructor loads a level from file.
    public Level( String path )
    {
        loadLevel( path );
    }
    
    protected void generateLevel()
    {
        
    }
    
    private void loadLevel( String path )
    {
        
    }
    
    public void update()
    {
        
    }
    
    private void time()
    {
        
    }
    
    public void render( int xScroll, int yScroll, Screen screen )
    {
        
    }
}