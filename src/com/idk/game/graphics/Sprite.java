package com.idk.game.graphics;

public class Sprite
{
    private final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;
    
    public static Sprite grass = new Sprite( 16, 0, 0, null );
    
    public Sprite( int size, int x, int y, SpriteSheet sheet )
    {
        SIZE = size;
        pixels = new int[ SIZE * SIZE ];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    private void load()
    {
        for( int y = 0; y < SIZE; y++ )
        {
            for( int x = 0; x < SIZE; x++ )
            {
                pixels[ x + y * SIZE ] = sheet.pixels[ (this.x + x ) + ( this.y + y ) * sheet.SIZE ];
            }
        }
    }
}