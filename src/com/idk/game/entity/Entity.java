package com.idk.game.entity;

import com.idk.game.graphics.Screen;
import com.idk.game.level.Level;
import java.util.Random;

public class Entity
{
    public int x;
    public int y;
    
    protected Level level;
    protected final Random random = new Random();
    
    private boolean removed = false;
    
    public void update()
    {
        
    }
    
    public void render( Screen screen )
    {
        
    }
    
    public void remove()
    {
        // Remove from level
        removed = true;
    }
    
    public boolean isRemoved()
    {
        return removed;
    }
    
    public void init( Level level )
    {
        this.level = level;
    }
}