package com.company.utils;

public class graphicsUtils {
    public static void drawSnowFlake(lineDrawer ld, int x, int y, int r, int n){
        double da = Math.PI * 2 / n;
        for (int i = 0; i < n; i++) {
            double dx1 = r * Math.cos(da * i), dy1 = r * Math.sin(da * i);
            ld.drawLine(x, y, x + (int)dx1,y +(int)dy1);
        }
    }
}
