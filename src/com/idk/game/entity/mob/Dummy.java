package com.idk.game.entity.mob;

import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;

public class Dummy extends Mob
{
    public Dummy( int x, int y )
    {
        this.x = x << 4;
        this.y = y << 4;
        
        sprite = Sprite.player_down;
    }
    
    public void update()
    {
        
    }
    
    public void render( Screen screen )
    {
        screen.renderMob( x, y, sprite, 0 );
    }
}