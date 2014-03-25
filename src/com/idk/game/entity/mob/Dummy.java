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
    
    
    public Dummy( int x, int y )
    {
        this.x = x << 4;
        this.y = y << 4;
        
        sprite = down;
    }
    
    public void update()
    {
        
    }
    
    public void render( Screen screen )
    {
        screen.renderMob( x, y, sprite, 0 );
    }
}