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
    
    public Particle( int x, int y, int life )
    {
        this.x = x;
        this.y = y;
        this.life = life;
        
        sprite = Sprite.particle_normal;
        
        particles.add( this );
    }
    
    public Particle( int x, int y, int life, int amount )
    {
        this( x, y, life );
        
        for( int i = 0; i < amount - 1; i++ )
        {
            particles.add( new Particle( x, y, life ) );
        }
    }
    
    public void update()
    {
        
    }
    
    public void render( Screen screen )
    {
        
    }
}