package com.company.LineDrawers;

import com.company.utils.interfaces.ILineDrawer;
import com.company.utils.interfaces.IPixelDrawer;

import java.awt.*;

/**
 * Класс реализующий логику рисования линий с помощью алгоритма Xiaolin Wu
 */
public class WuILineDrawer implements ILineDrawer {
    private IPixelDrawer pd;
    private Color color = Color.BLACK;

    public WuILineDrawer(IPixelDrawer pd) { this.pd = pd; }
    /**
     * Метод изменения текущего цвета отрисовки
     * @param color - цвет с помощью которого будет отрисовываться
     */
    @Override
    public void setColor(Color color) { this.color = color; }

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
            float deltaY = (float) (y - (int) y);
            float v = deltaY < 0 ? 0 : deltaY > 255 ? 255 : deltaY;
            pd.drawPixel(step ? (int) y + 1 : x, step ? x : (int) y + 1, new Color(red, green, blue, (int) (255 * v)));
            pd.drawPixel(step ? (int) y : x, step ? x : (int) y, new Color(red, green, blue, (int) (255 * (1 - v))));
            y += gradient;
        }
    }
}
