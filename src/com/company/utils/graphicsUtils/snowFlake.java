package com.company.utils.graphicsUtils;

import com.company.utils.interfaces.ILineDrawer;

/**
 * Класс реализующий отрисовку "Снежинки" с помощью LineDrawer
 */
public class snowFlake {
    /**
     *
     * @param ld Интерфейс рисующий линии
     * @param x - Х координата центра
     * @param y - У координата центра
     * @param r - Радиус
     * @param n - Количество лучей
     */
    public static void drawSnowFlake(ILineDrawer ld, int x, int y, int r, int n){
        double da = Math.PI * 2 / n;
        for (int i = 0; i < n; i++) {
            double dx1 = r * Math.cos(da * i), dy1 = r * Math.sin(da * i);
            ld.drawLine(x, y, x + (int)dx1,y +(int)dy1);
        }
    }
}
