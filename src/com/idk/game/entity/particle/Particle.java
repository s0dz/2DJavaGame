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
        this.xa = random.nextGaussian() + 1.8;
        
        if( this.xa < 0 ) xa = 0.1;
        
        // gravity
        this.ya = random.nextGaussian();
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
        
        this.xx += xa;
        this.yy += ya;
        this.zz += za;
        
        // FOR SCIENCE!
    }
    
    @Override
    public void render( Screen screen )
    {
        screen.renderSprite( (int) xx - 12, (int) yy - (int) zz, sprite, true );
    }
}