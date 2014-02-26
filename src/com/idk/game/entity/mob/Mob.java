package com.idk.game.entity.mob;

import com.idk.game.entity.Entity;
import com.idk.game.entity.projectile.Projectile;
import com.idk.game.entity.projectile.TestProjectile;
import com.idk.game.graphics.Sprite;

public abstract class Mob extends Entity
{
    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;
    // TODO: protected boolean walking = false;
    
    public void move( int xChange, int yChange )
    {
        System.out.println( "Size: " + level.getProjectiles().size() );
        
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
        
        // Check all 4 corners of the tile
        for( int c = 0; c < 4; c++ )
        {
            // This maths if for finding the collision sweet spot
            int xt = ( (x + xChange ) + c % 2 * 14 - 8 ) / 16;
            int yt = ( (y + yChange ) + c / 2 * 12 + 3 ) / 16;  
            
            if( level.getTile( xt, yt ).solid() ) solid = true;
        }
        
        return solid;
    }
    
    protected void shoot( int x, int y, double dir )
    {
        // dir = Math.toDegrees( dir ); // or dir *= 180 / Math.PI // <= lol... doesn't work too well 
        Projectile p = new TestProjectile( x, y, dir ); // Probably should be double for precision
        level.addProjectile( p );
    }
}