package com.company.LineDrawers;

import com.company.utils.lineDrawer;
import com.company.utils.pixelDrawer;

import java.awt.*;

public class Bresenhaim implements lineDrawer {
    private pixelDrawer pd;
    private Color color = Color.BLACK;

    public Bresenhaim(pixelDrawer pd) {
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
            tmp = y2; y2 = x2; x2 = tmp;
        }
        if(x1 > x2){
            int tmp = x1; x1 = x2; x2 = tmp;
            tmp = y1; y1 = y2; y2 = tmp;
        }
        int     dx = x2 - x1,
                dy = Math.abs(y2 - y1),
                error = dx / 2,
                yStep = (y2 > y1 ? 1 : -1),
                y = y1;
        for (int x = x1; x < x2; x++) {
            error -= dy;
            pd.drawPixel(step ? y : x, step ? x : y, color);
            if(error < 0){
                y += yStep;
                error += dx;
            }
        }
    }
}