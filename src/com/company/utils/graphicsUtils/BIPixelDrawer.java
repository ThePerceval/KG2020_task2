package com.company.utils.graphicsUtils;

import com.company.utils.interfaces.IPixelDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BIPixelDrawer implements IPixelDrawer {
    private BufferedImage bi;

    public BIPixelDrawer(BufferedImage bi) { this.bi = bi; }


    @Override
    public void drawPixel(int x, int y, Color color) {
        if(x >= 0 && y >= 0 && x < bi.getWidth() && y < bi.getHeight()){
            bi.setRGB(x, y, color.getRGB());
        }
    }
}
