package com.idk.game.entity.mob;

import com.idk.game.Game;
import com.idk.game.entity.projectile.Projectile;
import com.idk.game.entity.projectile.TestProjectile;
import com.idk.game.graphics.AnimatedSprite;
import com.idk.game.graphics.Screen;
import com.idk.game.graphics.Sprite;
import com.idk.game.graphics.SpriteSheet;
import com.idk.game.input.Keyboard;
import com.idk.game.input.Mouse;

public class Player extends Mob
{
    private Keyboard input;
    private Sprite sprite;
    private int animation = 0;
    private boolean walking = false;
    private AnimatedSprite down = new AnimatedSprite( SpriteSheet.player_down, 32, 32, 3 );
    private AnimatedSprite up = new AnimatedSprite( SpriteSheet.player_up, 32, 32, 3 );
    private AnimatedSprite left = new AnimatedSprite( SpriteSheet.player_left, 32, 32, 3 );
    private AnimatedSprite right = new AnimatedSprite( SpriteSheet.player_right, 32, 32, 3 );
    
    // This will "point" to the desired directional AnimatedSprite instance
    private AnimatedSprite animSprite = down;
    
    private int fireRate = 0;
    
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
        fireRate = TestProjectile.FIRE_RATE;
    }
    
    @Override
    public void update()
    {
        if( walking ) animSprite.update();
        else animSprite.setFrame( 0 ); // This can be removed to "store" the current frame
        
        if( fireRate > 0 ) fireRate--;
        
        int xChange = 0;
        int yChange = 0;
        
        // Check input and set change
        if( input.up )
        {
            animSprite = up;
            yChange--;            
        }
        else if( input.down )
        {
            animSprite = down;
            yChange++;
        }
        if( input.left )
        {
            animSprite = left;
            xChange--;
        }        
        else if( input.right )
        {
            animSprite = right;
            xChange++;
        }
        
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
        
        clear();
        updateShooting();
    }
    
    private void clear()
    {
        for( int i = 0; i < level.getProjectiles().size(); i++ )
        {
            Projectile p = level.getProjectiles().get(i);
            
            if( p.isRemoved() ) level.getProjectiles().remove( i );
        }
    }
    
    private void updateShooting()
    {
        if( Mouse.getButton() == 1 && fireRate <= 0 )
        {
            // Distance from mouse coords to center of screen
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            
            // This will calculate the angle
            double dir = Math.atan2( dy, dx ); // atan2 handles dividing by zero
            
            shoot( x, y, dir );
            
            fireRate = TestProjectile.FIRE_RATE;
        }
    }
    
    @Override
    public void render( Screen screen )
    {        
        int flip = 0;
        
        sprite = animSprite.getSprite();
        
        // Render sprite for player with offset to center
        screen.renderMob( x - 16, y - 16, sprite, flip );
    }
}