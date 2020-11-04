package com.company.LineDrawers;

import com.company.utils.lineDrawer;
import com.company.utils.pixelDrawer;

import java.awt.*;

public class DDALineDrawer implements lineDrawer{
    private pixelDrawer pd;

    public DDALineDrawer(pixelDrawer pd) {
        this.pd = pd;
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


        double dx = x2 - x1, dy = Math.abs(y2 - y1);
        double k = dy / dx, y = y1;
        for (int x = x1; x < x2; x++) {
            pd.drawPixel(step ? (int) Math.round(y) : x, step ? x : (int) Math.round(y), Color.black);
            y = y + (y2 > y1 ? k : -k);
        }
    }
}
