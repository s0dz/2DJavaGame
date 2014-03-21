package com.idk.game.graphics;

public class AnimatedSprite extends Sprite
{
    private int frame = 0;
    private Sprite sprite;
    private int rate = 5;
    private int length = 0;
    
    public AnimatedSprite( SpriteSheet sheet, int width, int height, int length )
    {
        super( sheet, width, height );        
        this.length = length;        
        
        if( length > sheet.getSprites().length ) System.err.println("Error: Length of animation is too long.");
    }
    
    public void update()
    {
        if( frame > length ) frame = 0;
        else frame++;
        sprite = sheet.getSprites()[frame];
    }
    
    public Sprite getSprite()
    {
        return sprite;
    }
    
    public void setFrameRate( int frames )
    {
        rate = frames;
    }
}