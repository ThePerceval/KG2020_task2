package com.company.LinaDrawers;

import com.company.utils.lineDrawer;
import com.company.utils.pixelDrawer;

import java.awt.*;

public class WuLineDrawer implements lineDrawer {
    pixelDrawer pd;

    public WuLineDrawer(pixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        /*if(x2 < x1){
            int tmp = x1; x1 = x2; x2 = tmp;
            tmp = y1; y1 = y2; y2 = tmp;
        }*/
        int dx = x2 - x1, dy = y2 - y1;
        double gradient = 0;
        /*if(dx == 0){
            for (int y = Math.min(y1, y2); y < Math.max(y1, y2); y++) {
                pd.drawPixel(x1, y, new Color(0, 0, 0));
            }
        } else if(dy == 0){
            for (int x = Math.min(x1, x2); x < Math.max(x1, x2); x++) {
                pd.drawPixel(x, y1, new Color(0, 0, 0));
            }
        } else */
        if(Math.abs(dx) >= Math.abs(dy)){
            if(x2 < x1){
                int
                        tmp = x1; x1 = x2; x2 = tmp;
                tmp = y1; y1 = y2; y2 = tmp;
            }
            gradient = (double) dy / dx;
            double interY = y1 + gradient;
            for (int x = x1; x < x2; x++) {
                pd.drawPixel(x, (int)interY, new Color(0, 0, 0, (int) (255 - fractionalPart(interY) * 255)));
                pd.drawPixel(x,(int)interY + 1, getNewColor(interY + 1));
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
                pd.drawPixel((int)interX, y, new Color(0, 0, 0, (int) (255 - fractionalPart(interX) * 255)));
                pd.drawPixel((int)interX + 1,y, getNewColor(interX));
                interX += gradient;
            }
        }
    }

    private Color getNewColor(double inter) {
        double fp = fractionalPart(inter);
        if(fp == 0){
            return Color.white;
        }
        return new Color(0, 0, 0, (int)  (255 - (1D - fractionalPart(inter + 1)) * 255));
    }

    private double fractionalPart(double x){
        int tmp = (int) x;
        return x - tmp;
    }
}
