package br.com.puppy8.peripherals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Panel extends JPanel {

    private static final int PIXEL_SIZE = 10;

    private byte[] screen;


    public Panel(byte[] screen) {
        super(true);
        this.screen = screen;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics offGraphics;
        Dimension offScreenImageDimension = getSize();
        Image offScreenImage = createImage(offScreenImageDimension.width, offScreenImageDimension.height);

        offGraphics = offScreenImage.getGraphics();
        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0, 0, offScreenImageDimension.width, offScreenImageDimension.height);
        offGraphics.setColor(getForeground());

        for (int i = 0; i < this.screen.length; i++) {
            Color pixelColor = this.screen[i] == 0 ? Color.BLACK : Color.WHITE;
            offGraphics.setColor(pixelColor);

            int positionX = i % 64;
            int positionY = (int) Math.floor(i/64);

            offGraphics.fillRect(positionX * PIXEL_SIZE, positionY * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
        }

        graphics.drawImage(offScreenImage, 0, 0, this);
    }

}
