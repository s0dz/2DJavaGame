package com.idk.game.entity.projectile;

import com.idk.game.entity.Spawner;
import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;

public class TestProjectile extends Projectile
{
    // higher == slower
    public static final int FIRE_RATE = 10;
    
    public TestProjectile( int x, int y, double dir )
    {
        super( x, y, dir );
        range = 200; // random.nextInt( 100 );
        speed = 4;
        damage = 20;
        
        sprite = Sprite.projectile_test;
        
        xNext = speed * Math.cos( angle );
        yNext = speed * Math.sin( angle );
    }
    
    @Override
    public void update()
    {
        if( level.tileCollision( x, y, xNext, yNext, 7 ) )
        {
            level.add( new Spawner( 16 * 16, 62 * 16, Spawner.Type.PARTICLE, 500 , level ) );
            remove();
        }
        move();
    }
       
    protected void move()
    {                   
        x += xNext;
        y += yNext;
        
        if( distance() > range ) remove();
    }
    
    private double distance()
    {
        double dist = 0;
        dist = Math.sqrt( Math.abs( (xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y) ) );
        return dist;
    }
    
    @Override
    public void render( Screen screen )
    {
        // The -12 & -2 are to adjust where Projectile originates from Player
        screen.renderProjectile( (int) x - 12, (int) y - 2, this );
    }
}