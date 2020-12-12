package com.company.LineDrawers;

import com.company.utils.interfaces.ILineDrawer;
import com.company.utils.interfaces.IPixelDrawer;

import java.awt.*;

/**
 * Класс реализующий логику рисования линий с помощью алгоритма DDA
 */
public class DDAILineDrawer implements ILineDrawer {
    private IPixelDrawer pd;
    private Color color = Color.BLACK;

    public DDAILineDrawer(IPixelDrawer pd) {
        this.pd = pd;
    }
    /**
     * Метод изменения текущего цвета отрисовки
     * @param color - цвет с помощью которого будет отрисовываться
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Метод рисования линий по 2 точкам
     * @param x1 - Х координата начала отрезка
     * @param y1 - У координата начала отрезка
     * @param x2 - Х координата конца отрезка
     * @param y2 - У координата конца отрезка
     */
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
            pd.drawPixel(step ? (int) Math.round(y) : x, step ? x : (int) Math.round(y), color);
            y = y + (y2 > y1 ? k : -k);
        }
    }
}
