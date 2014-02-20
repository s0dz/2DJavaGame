package com.idk.game.entity.mob;

import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;
import com.idk.game.input.Keyboard;

public class Player extends Mob
{
    private Keyboard input;
    private Sprite sprite;
    
    public Player( Keyboard input )
    {
        this.input = input;
        sprite = Sprite.player_up;
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
    public void render( Screen screen )
    {        
        int flip = 0;
        
        // Load correct direcitonal sprite
        if ( dir == 0 ) sprite = Sprite.player_up;        
        if ( dir == 1 ) sprite = Sprite.player_side;
        if ( dir == 2 ) sprite = Sprite.player_down;
        if ( dir == 3 )
        {
            sprite = Sprite.player_side;
            flip = 1;
        }
        
        // Render sprite for player with offset to center
        screen.renderPlayer( x - 16, y - 16, sprite, flip );
    }
}