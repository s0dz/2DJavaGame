package com.idk.game;

import com.idk.game.entity.mob.Player;
import com.idk.game.graphics.Screen;
import com.idk.game.graphics.SpriteSheet;
import com.idk.game.input.Keyboard;
import com.idk.game.input.Mouse;
import com.idk.game.level.Level;
import com.idk.game.level.TileCoordinate;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1l;
    
    private static int width = 300;
    private static int height =  width / 16 * 9;
    private static int scale = 3;
    public static String title = "Game";
    
    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;
    
    private Screen screen;
    
    private BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
    private int[] pixels = ( (DataBufferInt) image.getRaster().getDataBuffer() ).getData();
    
    public Game()
    {
        Dimension size = new Dimension( width * scale, height * scale );
        setPreferredSize( size );
        
        screen = new Screen( width, height );
        frame = new JFrame();
        key = new Keyboard();
        // level = new RandomLevel( 64, 64 ); // 64x64 tiles in size
        level = Level.spawn;
        
        TileCoordinate playerSpawn = new TileCoordinate( 16, 62 );
        player = new Player( playerSpawn.x(), playerSpawn.y(), key );
        player.init( level );
        
        Mouse mouse = new Mouse();
        addKeyListener( key );
        addMouseListener( mouse );
        addMouseMotionListener( mouse );
    }
    
    public static int getWindowWidth()
    {
        return width * scale;
    }
    
    public static int getWindowHeight()
    {
        return height * scale;
    }
    
    public synchronized void start()
    {
        running = true;
        
        thread = new Thread( this, "Game" );
        thread.start();
    }
    
    public synchronized void stop()
    {
        running = false;
        
        try
        {
            thread.join();
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        
        long timer = System.currentTimeMillis();
        
        int frames = 0;
        int updates = 0;
        
        // Input focus
        requestFocus();
        
        // Game loop
        while( running )
        {
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            
            lastTime = now;
            
            while ( delta >= 1 )
            {
                update();
                updates++;
                delta--;
            }
            
            render();
            frames++;
            
            if( System.currentTimeMillis() - timer > 1000 )
            {
                timer += 1000;
                System.out.println( updates + " ups, " + frames + " fps" );
                frame.setTitle( title + " | " + updates + "ups, " + frames + " fps" );
                updates = 0;
                frames = 0;
            }
        }
        
        stop();
    }
        
    public void update()
    {
        key.update();
        player.update();
        level.update();
    }
    
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if( bs == null )
        {
            createBufferStrategy( 3 );
            return;
        }
        
        screen.clear();
        
        // Centered on player coords.
        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.render( xScroll, yScroll, screen );
        player.render( screen );
        
        screen.renderSheet( 40, 40, SpriteSheet.player_down, false );
        
        for( int i = 0; i < pixels.length; i++ )
        {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        g.drawImage( image,  0,  0, getWidth(), getHeight(), null );
        g.setColor( Color.WHITE );
        g.setFont(  new Font( "Verdana", 0, 50 ) );
        // g.drawString( "X: " + player.x + ", Y:" + player.y, 350, 300 );
        // g.fillRect( Mouse.getX() -32, Mouse.getY() - 32, 64, 64 );
        // if( Mouse.getButton() != -1 ) g.drawString( "Button: " + Mouse.getButton(), 80, 80 );
        g.dispose();
        
        bs.show();
    }
    
    public static void main( String[] args )
    {
        Game game = new Game();
        game.frame.setResizable( false );
        game.frame.setTitle( Game.title );
        game.frame.add( game );
        game.frame.pack();
        game.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        game.frame.setLocationRelativeTo( null );
        game.frame.setVisible( true );
        
        game.start();
    }
}