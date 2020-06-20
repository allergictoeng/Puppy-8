package br.com.puppy8;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JFrame;

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
    			scr.writePixelValue(1850, 1);
    			Frame frame = new Frame();
				Panel panel = new Panel(scr.getScreen());
				frame.setup(panel);
    }
}
