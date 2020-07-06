package br.com.puppy8;

import br.com.puppy8.peripherals.Frame;
import br.com.puppy8.peripherals.Panel;
import br.com.puppy8.peripherals.Screen;
import br.com.puppy8.peripherals.Sound;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {			
    	
    			Sound sound = new Sound();
    			sound.play();
    			sound.stop();
    			Screen scr = new Screen(0x800);
    			scr.writePixelValue(0, 1111);
    			Frame frame = new Frame();
				Panel panel = new Panel(scr.getScreen());
				frame.setup(panel);
				frame.repaint();
    }
}
