package com.idk.game.entity.projectile;

import com.idk.game.entity.Entity;
import com.idk.game.graphics.Sprite;
import java.util.Random;

public class Projectile extends Entity
{
    // Override Entity's x & y for dat precision
    protected double x;
    protected double y;
    
    protected final int xOrigin;
    protected final int yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double xNext;
    protected double yNext;
    protected double speed;
    protected double rateOfFire;
    protected double range;
    protected double distance;
    protected double damage;
    
    protected final Random random = new Random();
    
    public Projectile( int x, int y, double dir )
    {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        
        this.x = x;
        this.y = y;
    }
    
    public Sprite getSprite()
    {
        return sprite;
    }
    
    public int getSpriteSize()
    {
        return sprite.SIZE;
    }
}