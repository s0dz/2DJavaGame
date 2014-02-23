package com.idk.game.entity.mob;

import com.idk.game.entity.Entity;
import com.idk.game.graphics.Sprite;

public abstract class Mob extends Entity
{
    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;
    
    public void move( int xChange, int yChange )
    {
        // This technique allows sliding
        if( xChange != 0 && yChange != 0 )
        {
            move( xChange, 0 );
            move( 0, yChange );
            
            return;
        }
        
        //         0
        //      3     1
        //         2
        if( xChange > 0 ) dir = 1;
        if( xChange < 0 ) dir = 3;
        if( yChange > 0 ) dir = 2;
        if( yChange < 0 ) dir = 0;
        
        // Move if not in a collision
        if( !collision( xChange, yChange ) )
        {
            x += xChange;
            y += yChange;
        }
    }
    
    @Override
    public void update()
    {
        
    }
    
    public void render()
    {
        
    }
    
    private boolean collision( int xChange, int yChange )
    {
        boolean solid = false;
        
        if( level.getTile( ( x + xChange ) / 16, ( y + yChange ) /16 ).solid() ) solid = true;
        
        return solid;
    }
}