package com.idk.game.entity.mob;

import com.idk.game.entity.Entity;
import com.idk.game.graphics.Sprite;

public abstract class Mob extends Entity
{
    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;
    
    public void move()
    {
        
    }
    
    @Override
    public void update()
    {
        
    }
    
    public void render()
    {
        
    }
    
    private boolean collision()
    {
        return false;
    }
}