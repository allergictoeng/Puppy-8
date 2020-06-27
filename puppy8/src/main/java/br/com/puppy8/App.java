package br.com.puppy8;

import br.com.puppy8.devices.Frame;
import br.com.puppy8.devices.Panel;
import br.com.puppy8.devices.Screen;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {			
    	
    	
    			Screen scr = new Screen(0x800);
    			scr.writePixelValue(0, 1111);
    			Frame frame = new Frame();
				Panel panel = new Panel(scr.getScreen());
				frame.setup(panel);
    }
}
