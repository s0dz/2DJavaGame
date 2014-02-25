package com.idk.game.entity.projectile;

import com.idk.game.entity.Entity;
import com.idk.game.graphics.Sprite;

public class Projectile extends Entity
{
    protected final int xOrigin;
    protected final int yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double xNext;
    protected double yNext;
    protected double speed;
    protected double rateOfFire;
    protected double range;
    protected double damage;
    
    public Projectile( int x, int y, double dir )
    {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        
        this.x = x;
        this.y = y;
    }
}