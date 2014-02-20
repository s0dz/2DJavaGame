package com.idk.game.graphics;

public class Sprite
{
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;
    
    public static Sprite grass = new Sprite( 16, 0, 0, SpriteSheet.tiles );
    public static Sprite voidSprite = new Sprite( 16, 0 ); // Black sprite
    
    
    // Player is 32x32 which is 4 Tile sizes
    public static Sprite player0 = new Sprite( 16, 0, 10, SpriteSheet.tiles );
    public static Sprite player1 = new Sprite( 16, 1, 10, SpriteSheet.tiles );
    public static Sprite player2 = new Sprite( 16, 0, 11, SpriteSheet.tiles );
    public static Sprite player3 = new Sprite( 16, 1, 11, SpriteSheet.tiles );
    
    public Sprite( int size, int x, int y, SpriteSheet sheet )
    {
        SIZE = size;
        pixels = new int[ SIZE * SIZE ];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    /**
     * This object will fill in a sprite object will be a solid color which
     * is specified as a parameter.
     * 
     * To pass in a color: 0xXXXXXX
     * 
     * Easy way to find color codes: http://www.colorpicker.com/
     * 
     * @param size
     * @param color 
     */
    public Sprite( int size, int color )
    {
        SIZE = size;
        pixels = new int[ SIZE * SIZE ];
        setColor( color );
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
    
    private void setColor( int color )
    {
        for( int i = 0; i < pixels.length; i++ )
        {
            pixels[i] = color;
        }
    }
}