package com.idk.game.graphics;

public class Sprite
{
    public final int SIZE;
    private int width;
    private int height;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;
    
    public static Sprite voidSprite = new Sprite( 16, 0 ); // Black sprite
    
    public static Sprite grass = new Sprite( 16, 0, 5, SpriteSheet.tiles );
    public static Sprite flower = new Sprite( 16, 1, 0, SpriteSheet.tiles );
    public static Sprite rock = new Sprite( 16, 2, 0, SpriteSheet.tiles );
    
    // Spawn Level Sprites
    public static Sprite spawn_grass = new Sprite( 16, 0, 0, SpriteSheet.spawn_level );
    public static Sprite spawn_hedge = new Sprite( 16, 1, 0, SpriteSheet.spawn_level );
    public static Sprite spawn_water = new Sprite( 16, 2, 0, SpriteSheet.spawn_level );
    public static Sprite spawn_wall1 = new Sprite( 16, 0, 1, SpriteSheet.spawn_level );
    public static Sprite spawn_wall2 = new Sprite( 16, 0, 2, SpriteSheet.spawn_level );
    public static Sprite spawn_floor = new Sprite( 16, 1, 1, SpriteSheet.spawn_level );
    
    // Player Sprites
    // The 5 is actually a 10 when counting on spritesheet.
    // This is because the Player sprite sheet dimension are twice as large.
    public static Sprite player_up = new Sprite( 32, 0, 5, SpriteSheet.tiles );
    public static Sprite player_side = new Sprite( 32, 1, 5, SpriteSheet.tiles );
    public static Sprite player_down = new Sprite( 32, 2, 5, SpriteSheet.tiles );
       
    public static Sprite player_up_1 = new Sprite( 32, 0, 6, SpriteSheet.tiles );
    public static Sprite player_up_2 = new Sprite( 32, 0, 7, SpriteSheet.tiles );
    
    public static Sprite player_side_1 = new Sprite( 32, 1, 6, SpriteSheet.tiles );
    public static Sprite player_side_2 = new Sprite( 32, 1, 7, SpriteSheet.tiles );
    
    public static Sprite player_down_1 = new Sprite( 32, 2, 6, SpriteSheet.tiles );
    public static Sprite player_down_2 = new Sprite( 32, 2, 7, SpriteSheet.tiles );
        
    // Projectiles
    public static Sprite projectile_test = new Sprite( 16, 0, 0, SpriteSheet.projectile_test );
    
    
    // Particles
    public static Sprite particle_normal = new Sprite( 3, 3, 0xAAAAAA );
    
    /*
    * TODO: Clean these constructors up... Specifically the third one
    *       that does not set height and width which, as it turns out,
    *       makes rendering it impossible. (Due to the nested for loops
    *       using height and width)
    */
    
    protected Sprite( SpriteSheet sheet, int width, int height )
    {
        SIZE = width == height ? width : -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }
    
    public Sprite( int size, int x, int y, SpriteSheet sheet )
    {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[ SIZE * SIZE ];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    public Sprite( int width, int height, int color )
    {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[ width * height ];
        setColor( color );
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
        for( int i = 0; i < width * height; i++ )
        {
            pixels[i] = color;
        }
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
}