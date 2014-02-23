package com.idk.game.entity.mob;

import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;
import com.idk.game.input.Keyboard;
import com.idk.game.input.Mouse;

public class Player extends Mob
{
    private Keyboard input;
    private Sprite sprite;
    private int animation = 0;
    private boolean walking = false;
    
    public Player( Keyboard input )
    {
        this.input = input;
        sprite = Sprite.player_up; // Since dir is initialized at 0, not a big deal
    }
    
    // Sets a spawn location
    public Player( int x, int y, Keyboard input )
    {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_up; // Since dir is initialized at 0, not a big deal
    }
    
    @Override
    public void update()
    {
        int xChange = 0;
        int yChange = 0;
        
        // Increase animation, but don't let it crash the game.
        if( animation < 7500 ) animation++;
        else animation = 0;
        
        // Check input and set change
        if( input.up ) yChange--;
        if( input.down ) yChange++;
        if( input.left ) xChange--;
        if( input.right ) xChange++;
        
        // Move! Move! Move!
        if( xChange != 0 || yChange != 0 )
        {
            move( xChange, yChange );
            walking = true;
        }
        else
        {
            walking = false;
        }
        
        updateShooting();
    }
    
    private void updateShooting()
    {
        if( Mouse.getButton() == 1 )
        {
            // Distance from mouse coords to center of screen
            double dx = Mouse.getX() - 300 / 2; //TODO:
            double dy = Mouse.getY() - 168 / 2; // Get rid of magic numbers!
            
            // This will calculate the angle
            double dir = Math.atan2( dy, dx );
            
            shoot( x, y, dir );
        }
    }
    
    @Override
    public void render( Screen screen )
    {        
        int flip = 0;
        
        // Load correct direcitonal sprite and animation
        if( dir == 0 )
        {
            sprite = Sprite.player_up;
            
            if( walking )
            {
                if( animation % 20 > 10 )
                {
                    sprite = Sprite.player_up_1;
                }
                else
                {
                    sprite = Sprite.player_up_2;
                }
            }
        }        
        if( dir == 1 )
        {
            sprite = Sprite.player_side;
            
            if( walking )
            {
                if( animation % 20 > 10 )
                {
                    sprite = Sprite.player_side_1;
                }
                else
                {
                    sprite = Sprite.player_side_2;
                }
            }
        }
        if( dir == 2 )
        {
            sprite = Sprite.player_down;
            
            if( walking )
            {
                if( animation % 20 > 10 )
                {
                    sprite = Sprite.player_down_1;
                }
                else
                {
                    sprite = Sprite.player_down_2;
                }
            }
        }
        if( dir == 3 )
        {
            sprite = Sprite.player_side;
            flip = 1;
            
            if( walking )
            {
                if( animation % 20 > 10 )
                {
                    sprite = Sprite.player_side_1;
                }
                else
                {
                    sprite = Sprite.player_side_2;
                }
            }
        }
        
        // Render sprite for player with offset to center
        screen.renderPlayer( x - 16, y - 16, sprite, flip );
    }
}