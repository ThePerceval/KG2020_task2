package com.company.LineDrawers;

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
        boolean steep = Math.abs(x2 - x1) < Math.abs(y2 - y1);
        if(steep){
            int tmp = x1; x1 = y1; y1 = tmp;
            tmp = x2; x2 = y2; y2 = tmp;
        }

        if(x2 < x1){
            int tmp = x1; x1 = x2; x2 = tmp;
            tmp = y1; y1 = y2; y2 = tmp;
        }

        double dx = x2 - x1, dy = y2 - y1;
        double gradient = dy / dx, y = y1 + gradient;

        pd.drawPixel(x1, y1, Color.black);
        pd.drawPixel(x2, y2, Color.black);

        for (int x = x1 + 1; x < x2 ; x++) {
            pd.drawPixel(steep ? (int) y + 1: x, steep ? x : (int) y + 1, new Color(0,0,0, (float) (y - (int)y)));
            pd.drawPixel(steep ? (int) y : x, steep ? x : (int) y, new Color(0,0,0, (float)(1 - (y - (int)y))));
            y += gradient;
        }
    }
}
