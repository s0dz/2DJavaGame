package com.idk.game.entity.particle;

import com.idk.game.entity.Entity;
import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;

public class Particle extends Entity
{
    private Sprite sprite; // Might move this into entity class...
    
    private int life;
    private int time = 0;
    
    protected double xx, yy, zz;
    protected double xa, ya, za;
    
    public Particle( int x, int y, int life )
    {
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;        
        this.life = life + ( random.nextInt( 20 ) - 10 );
        
        sprite = Sprite.particle_normal;
        
        // Normally distrubited results. Dat bell curve doh.
        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        
        // Slightly random starting height
        this.zz = random.nextFloat() + 2.0;
    }
    
    @Override
    public void update()
    {
        time++;
        if( time >= 7400 ) time = 0; // Just keep it from running buck wild
        if( time > life ) remove();
        za -= 0.1;
        
        // floor/bounce simulation
        if( zz < 0 )
        {
            zz = 0;
            za *= -0.55;
            xa *= 0.4;
            ya *= 0.4;
        }
        
        move( xx + xa, (yy + ya) + (zz + za ) );
    }
    
    private void move(double x, double y)
    {
        if( collision( x, y ) )
        {
            this.xa *= -0.5;
            this.ya *= -0.5;
            this.za *= -0.5;
        }
        this.xx += xa;
        this.yy += ya;
        this.zz += za;
    }
    
    public boolean collision( double x, double y )
    {
        boolean solid = false;
        
        for( int c = 0; c < 4; c++ )
        {
            double xt = ( x - c % 2 * 16 ) / 16;
            double yt = ( y - c / 2 * 16 ) / 16;
            
            int ix = (int) Math.ceil( xt );
            int iy = (int) Math.ceil( yt );
            
            if( c % 2 == 0 ) ix = (int) Math.floor( xt ); // Left
            if( c / 2 == 0 ) ix = (int) Math.floor( yt ); // Bottom
            
            if( level.getTile( ix, iy ).solid() ) solid = true;
        }
        
        return solid;
    }
    
    @Override
    public void render( Screen screen )
    {
        screen.renderSprite( (int) xx - 1, (int) yy - (int) zz - 1, sprite, true );
    }
}