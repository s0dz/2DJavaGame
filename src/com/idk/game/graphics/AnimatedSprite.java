package com.idk.game.graphics;

public class AnimatedSprite extends Sprite
{
    private int frame = 0;
    private Sprite sprite;
    private int rate = 5;
    private int animationSize = 0;
    
    public AnimatedSprite( SpriteSheet sheet, int width, int height, int animation )
    {
        super( sheet, width, height );
        
    }
    
    public void update()
    {
        
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