package com.idk.game.entity.particle;

import com.idk.game.entity.Entity;
import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;

public class Particle extends Entity
{
    private Sprite sprite; // Might move this into entity class...
    
    private int life;
    private int time = 0;
    
    protected double xx, yy;
    protected double xa, ya;
    
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
    }
    
    @Override
    public void update()
    {
        time++;
        if( time >= 7400 ) time = 0; // Just keep it from running buck wild
        if( time > life ) remove();
        this.xx += xa;
        this.yy += ya;
    }
    
    @Override
    public void render( Screen screen )
    {
        screen.renderSprite( (int) xx, (int) yy, sprite, true );
    }
}