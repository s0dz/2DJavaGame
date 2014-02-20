package com.idk.game.entity.mob;

import com.idk.game.input.Keyboard;

public class Player extends Mob
{
    private Keyboard input;
    
    public Player( Keyboard input )
    {
        this.input = input;
    }
    
    public Player( int x, int y, Keyboard input )
    {
        this.x = x;
        this.y = y;
        this.input = input;
    }
    
    @Override
    public void update()
    {
        int xChange = 0;
        int yChange = 0;
        
        // Check input and set change
        if( input.up ) yChange--;
        if( input.down ) yChange++;
        if( input.left ) xChange--;
        if( input.right ) xChange++;
        
        // Move! Move! Move!
        if( xChange != 0 || yChange != 0 ) move( xChange, yChange );
    }
    
    @Override
    public void render()
    {
        
    }
}