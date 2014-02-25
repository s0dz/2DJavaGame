package com.idk.game.entity.projectile;

import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;
import com.idk.game.level.tile.Tile;

public class TestProjectile extends Projectile
{
    public TestProjectile( int x, int y, double dir )
    {
        super( x, y, dir );
        range = 200;
        speed = 4;
        damage = 20;
        rateOfFire = 15;
        
        sprite = Sprite.projectile_test;
        
        xNext = speed * Math.cos( angle );
        yNext = speed * Math.sin( angle );
    }
    
    @Override
    public void update()
    {
        move();
    }
    
    protected void move()
    {
        x += xNext;
        y += yNext;
    }
    
    @Override
    public void render( Screen screen )
    {
        screen.renderProjectile( x, y, this );
    }
}