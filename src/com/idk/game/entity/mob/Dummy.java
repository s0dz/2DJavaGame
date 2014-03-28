package com.idk.game.entity.mob;

import com.idk.game.graphics.AnimatedSprite;
import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;
import com.idk.game.graphics.SpriteSheet;

public class Dummy extends Mob
{
    private AnimatedSprite down = new AnimatedSprite( SpriteSheet.dummy_down, 32, 32, 3 );
    private AnimatedSprite up = new AnimatedSprite( SpriteSheet.dummy_up, 32, 32, 3 );
    private AnimatedSprite left = new AnimatedSprite( SpriteSheet.dummy_left, 32, 32, 3 );
    private AnimatedSprite right = new AnimatedSprite( SpriteSheet.dummy_right, 32, 32, 3 );
        
    private AnimatedSprite animSprite = down;
    
    private int time = 0;
    int xChange = 1;
    int yChange = 0;
    
    public Dummy( int x, int y )
    {
        this.x = x << 4;
        this.y = y << 4;
        
        sprite = Sprite.dummy;
    }
    
    @Override
    public void update()
    {
        time++;
        
        if( time % 60 == 0 )
        {
            xChange = -xChange;
        }
        
        if ( walking ) animSprite.update();
        else animSprite.setFrame( 0 );
        
        // Check input and set change
        if( yChange < 0 )
        {
            animSprite = up;
            dir = Direction.UP;
        }
        else if( yChange > 0 )
        {
            animSprite = down;
            dir = Direction.DOWN;
        }
        if( xChange < 0 )
        {
            animSprite = left;
            dir = Direction.LEFT;
        }        
        else if( xChange > 0 )
        {
            animSprite = right;
            dir = Direction.RIGHT;
        }
        
        // Move! Move! Move!
        if( xChange != 0 || yChange != 0 )
        {
            move( xChange, yChange );
            walking = true;
        }
        else
        {
            walking = false;
        }
    }
    
    public void render( Screen screen )
    {
        sprite = animSprite.getSprite();
        screen.renderMob( x, y, sprite, 0 );
    }
}