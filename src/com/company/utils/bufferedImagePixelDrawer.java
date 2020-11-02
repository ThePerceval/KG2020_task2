package com.company.utils;
import java.awt.*;
import java.awt.image.BufferedImage;

public class bufferedImagePixelDrawer implements  pixelDrawer {
    private BufferedImage bi;

    public bufferedImagePixelDrawer(BufferedImage bi) {
        this.bi = bi;
    }

    @Override
    public void drawPixel(int x, int y, Color color) {
        if(x >= 0 && y >= 0 && x < bi.getWidth() && y < bi.getHeight()){
            bi.setRGB(x, y, color.getRGB());
        }
    }
}
