package com.company.LinaDrawers;

import com.company.utils.lineDrawer;
import com.company.utils.pixelDrawer;

import java.awt.*;

public class Bresenhaim implements lineDrawer {
    private pixelDrawer pd;

    public Bresenhaim(pixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1, dy = y2 - y1;
        double gradient = 0;
        if(Math.abs(dx) >= Math.abs(dy)){
            if(x2 < x1){
                int tmp = x1; x1 = x2; x2 = tmp;
                tmp = y1; y1 = y2; y2 = tmp;
            }
            gradient = (double) dy / dx;
            double interY = y1;
            for (int x = x1; x < x2; x++) {
                pd.drawPixel(x, (int)interY, Color.BLACK);
                interY += gradient;
            }
        }
        else {
            if(y2 < y1){
                int tmp = x1; x1 = x2; x2 = tmp;
                tmp = y1; y1 = y2; y2 = tmp;
            }
            gradient = (double) dx / dy;
            double interX = x1 + gradient;
            for (int y = y1; y < y2; y++) {
                pd.drawPixel((int)interX, y, Color.BLACK);
                interX += gradient;
            }
        }

        /*int deltaX = x2 - x1, deltaY = y2 - y1;
        boolean direction = Math.abs(deltaX) >= Math.abs(deltaY);
        int start = direction ? Math.min(x1, x2) : Math.min(y1, y2);
        double incline = (double) Math.min(Math.abs(deltaX), Math.abs(deltaY)) / Math.max(Math.abs(deltaX), Math.abs(deltaY)),
               inter = direction ? Math.min(x1, x2) == x1 ? y1 : y2 : Math.min(y1, y2) == y1 ? x1 : x2;

        incline = sign(deltaX) ^ sign(deltaY) ? -incline : incline;
        inter += incline;

        for (int i = start; i < (direction ? Math.max(x1, x2) : Math.max(y1, y2)); i++){
            pd.drawPixel(direction ? i :(int) inter, direction ? (int) inter : i, Color.BLACK);
            inter += incline;
        }*/
    }

    private boolean sign(int delta){
        return delta >= 0;
    }
}
