package com.company.LineDrawers;

import com.company.utils.lineDrawer;
import com.company.utils.pixelDrawer;

import java.awt.*;

public class WuLineDrawer implements lineDrawer {
    private pixelDrawer pd;
    private Color color = Color.BLACK;

    public WuLineDrawer(pixelDrawer pd) {
        this.pd = pd;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        boolean step = Math.abs(x2 - x1) < Math.abs(y2 - y1);
        if(step){
            int tmp = x1; x1 = y1; y1 = tmp;
            tmp = x2; x2 = y2; y2 = tmp;
        }
        if(x1 > x2){
            int tmp = x1; x1 = x2; x2 = tmp;
            tmp = y1; y1 = y2; y2 = tmp;
        }
        int red = color.getRed(), green = color.getGreen(),blue = color.getBlue();

        double dx = x2 - x1, dy = y2 - y1;
        double gradient = dy / dx, y = y1 + gradient;

        pd.drawPixel(step ? y1 : x1, step ? x1 : y1, color);
        pd.drawPixel(step ? y2 : x2, step ? x2 : y2, color);

        for (int x = x1 + 1; x < x2 ; x++) {
            float c1 = (float) ((y - (int)y) < 0 ? 0 : (y - (int)y) > 255 ? 255 : (y - (int)y));
            pd.drawPixel(step ? (int) y + 1: x, step ? x : (int) y + 1, new Color(red, green, blue, (int)(255 * c1)));
            float c2 = (float) (1 - ((y - (int)y) < 0 ? 0 : (y - (int)y) > 255 ? 255 : (y - (int)y)));
            pd.drawPixel(step ? (int) y : x, step ? x : (int) y, new Color(red, green, blue, (int)(255 * c2)));
            y += gradient;
        }
    }
}
