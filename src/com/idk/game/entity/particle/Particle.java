package com.idk.game.entity.particle;

import com.idk.game.entity.Entity;
import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;
import java.util.ArrayList;
import java.util.List;


public class Particle extends Entity
{
    private List<Particle> particles = new ArrayList<>();
    private Sprite sprite; // Might move this into entity class...
    
    private int life;
    
    protected double xx, yy;
    protected double xa, ya;
    
    public Particle( int x, int y, int life )
    {
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;        
        this.life = life;
        
        sprite = Sprite.particle_normal;
        
        // Normally distrubited results. Dat bell curve doh.
        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
    }
    
    public Particle( int x, int y, int life, int amount )
    {
        this( x, y, life );
            
        for( int i = 0; i < amount - 1; i++ )
        {
            particles.add( new Particle( x, y, life ) );
        }
        
        particles.add( this ); 
    }
    
    @Override
    public void update()
    {
        this.xx += xa;
        this.yy += ya;
    }
    
    @Override
    public void render( Screen screen )
    {
        screen.renderSprite( (int) xx, (int) yy, sprite, true);
    }
}