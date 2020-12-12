package com.company.LineDrawers;

import com.company.utils.interfaces.IDrawer;
import com.company.utils.interfaces.ILineDrawer;
import com.company.utils.interfaces.IPixelDrawer;

import java.awt.*;

/**
 * Класс реализующий логику рисования примитивов с помощью алгоритма Брезенхэма
 */

public class Bresenham implements ILineDrawer, IDrawer {
    private IPixelDrawer pd;
    private Color color = Color.BLACK;

    public Bresenham(IPixelDrawer pd) {
        this.pd = pd;
    }

    /**
     * Метод изменения текущего цвета отрисовки
     * @param color - цвет с помощью которого будет отрисовываться
     */
    @Override
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
        int     dx = x2 - x1,
                dy = Math.abs(y2 - y1),
                error = dx / 2,
                yStep = (y2 > y1 ? 1 : -1),
                y = y1;
        for (int x = x1; x <= x2; x++) {
            error -= dy;
            pd.drawPixel(step ? y : x, step ? x : y, color);
            if(error < 0){
                y += yStep;
                error += dx;
            }
        }
    }

    /**
     * Скрытый метод отрисовки окружности
     * @param xC - Х координата центра окружности
     * @param yC - У координата центра окружности
     * @param radius - радиус окружности
     */
    private void drawCircle(int xC, int yC, int radius) {
        int x = -radius;
        int y = 0;
        int err = 2 - 2 * radius;
        while (x <= 0){
            pd.drawPixel(xC + x, yC + y, color);
            pd.drawPixel(xC + x, yC - y, color);
            pd.drawPixel(xC - x, yC + y, color);
            pd.drawPixel(xC - x, yC - y, color);
            int t = err;
            if (t <= y)           err += ++y * 2 + 1;
            if (t > x || err > y) err += ++x * 2 + 1;
        }
    }

    /**
     * Метод отрисовки окружности вписанной в квадрат с координатами диагональных точек
     * @param x1 - Х координата первой точки
     * @param y1 - У координата первой точки
     * @param x2 - Х координата второй точки
     * @param y2 - У координата второй точки
    */
    @Override
    public void drawCircle(int x1, int y1, int x2, int y2) {
        int radius = Math.min(Math.abs(x1 - x2), Math.abs(y1 - y2)) / 2;
        drawCircle(x1 + radius * (x2 < x1 ? -1 : 1), y1 + radius * (y1 > y2 ? -1 : 1), radius);
    }

    /**
     * Метод отрисовки части эллипса
     * @param x  - Х координата первой точки
     * @param y  - У координата первой точки
     * @param x2 - Х координата второй точки
     * @param y2 - У координата второй точки
     * @param startAngle - значение начального угла
     * @param arcAngle - значение угла дуги
     */
    @Override
    public void drawArc(int x, int y, int x2, int y2, int startAngle, int arcAngle) {
        int width = Math.abs(x2 - x),
                height = Math.abs(y2 - y);
        y = Math.min(y, y2);
        x = Math.min(x, x2);
        int b1 = height & 1;
        long dx = 4 * (1 - width) * height * height;
        long dy = 4 * (b1 + 1) * width * width;
        long err = dx + dy + b1 * width * width;
        long e2;

        int y0 = y + (height + 1) / 2;
        int y1 = y0 - b1;
        int x0 = x;
        int x1 = x + width;

        double cx = 0.5 * (x + x + width);
        double cy = 0.5 * (y + y + height);

        width *= 8 * width;
        b1 = 8 * height * height;

        startAngle %= 360;
        arcAngle %= 360;
        int angleFinish = startAngle + arcAngle;
        while(x0 <= x1){
            double theta = Math.toDegrees(-Math.atan2(y1 - cy, x1 - cx));
            if (theta < 0) { theta += 360; }
            /* I четверть */
            if ((theta >= startAngle && theta <= angleFinish || theta + 360 >= startAngle && theta + 360 <= angleFinish))
                pd.drawPixel(x1, y1, color);
            /* II четверть */
            if (180 - theta >= startAngle && 180 - theta <= angleFinish || 540 - theta >= startAngle && 540 - theta <= angleFinish)
                pd.drawPixel(x0, y1, color);
            /* III четверть */
            if (180 + theta >= startAngle && 180 + theta <= angleFinish || 540 + theta >= startAngle && 540 + theta <= angleFinish)
                pd.drawPixel(x0, y0, color);
            /* IV четверть */
            if (360 - theta >= startAngle && 360 - theta <= angleFinish || 720 - theta >= startAngle && 720 - theta <= angleFinish)
                pd.drawPixel(x1, y0, color);
            e2 = 2 * err;
            if (e2 <= dy) {
                y0++;
                y1--;
                err += dy += width;
            }
            if (e2 >= dx || 2 * err > dy) {
                x0++;
                x1--;
                err += dx += b1;
            }
        }
    }

    @Override
    public void drawOval(int x, int y, int x2, int y2) {
        int width = Math.abs(x2 - x), height = Math.abs(y2 - y);
        y = Math.min(y, y2);
        x = Math.min(x, x2);

        int b1 = height & 1;
        long dx = 4 * (1 - width) * height * height;
        long dy = 4 * (b1 + 1) * width * width;
        long err = dx + dy + b1 * width * width;
        long e2;

        int y0 = y + (height + 1) / 2;
        int y1 = y0 - b1;
        int x0 = x;
        int x1 = x + width;

        width *= 8 * width;
        b1 = 8 * height * height;

        while ((x0 <= x1)){
            pd.drawPixel(x1, y0, color);
            pd.drawPixel(x0, y0, color);
            pd.drawPixel(x0, y1, color);
            pd.drawPixel(x1, y1, color);
            e2 = 2 * err;
            if (e2 <= dy) {
                y0++;
                y1--;
                err += dy += width;
            }
            if (e2 >= dx || e2 > dy) {
                x0++;
                x1--;
                err += dx += b1;
            }
        }
    }

    @Override
    public void drawPie(int x1, int y1, int x2, int y2, int startAngle, int arcAngle) {
        drawArc(x1, y1, x2, y2, startAngle, arcAngle);
        int xC = (x1 + x2) / 2, yC = (y1 + y2) / 2;
//        int deltaY =
//        int DELTAY = 1D / Math.cos(Math.toRadians(startAngle));
        drawLine(xC, yC, xC + (int)Math.round((x2 - x1) / 2D * Math.cos(Math.toRadians(startAngle))), yC - (int)Math.round((y2 - y1) / 2D *Math.sin(Math.toRadians(startAngle))));
        drawLine(xC, yC, xC + (int)Math.round((x2 - x1) / 2D * Math.cos(Math.toRadians(startAngle + arcAngle))), yC - (int)Math.round((y2 - y1) / 2D *Math.sin(Math.toRadians(startAngle + arcAngle))));
//        System.out.println(x1 + " " + (yC + (1 / Math.cos(Math.toRadians(startAngle))))+ " " + x2 + " " + (yC - ));
    }

    @Override
    public void fillOval(int x, int y, int x2, int y2) {
        int width = Math.abs(x2 - x), height = Math.abs(y2 - y);
        y = Math.min(y, y2);
        x = Math.min(x, x2);

        int b1 = height & 1;
        long dx = 4 * (1 - width) * height * height;
        long dy = 4 * (b1 + 1) * width * width;
        long err = dx + dy + b1 * width * width;
        long e2;

        int y0 = y + (height + 1) / 2;
        int y1 = y0 - b1;
        int x0 = x;
        int x1 = x + width;

        width *= 8 * width;
        b1 = 8 * height * height;

        while ((x0 <= x1)){
            drawLine(x0, y0, x1, y0);
            drawLine(x0, y1, x1, y1);
            e2 = 2 * err;
            if (e2 <= dy) {
                y0++;
                y1--;
                err += dy += width;
            }
            if (e2 >= dx || 2 * err > dy) {
                x0++;
                x1--;
                err += dx += b1;
            }
        }
    }

    @Override
    public void fillPie(int x, int y, int x2, int y2, int startAngle, int arcAngle) {
        int width = Math.abs(x2 - x),
                height = Math.abs(y2 - y);
        y = Math.min(y, y2);
        x = Math.min(x, x2);
        int b1 = height & 1;
        long dx = 4 * (1 - width) * height * height;
        long dy = 4 * (b1 + 1) * width * width;
        long err = dx + dy + b1 * width * width;
        long e2;

        int y0 = y + (height + 1) / 2;
        int y1 = y0 - b1;
        int x0 = x;
        int x1 = x + width;

        double cx = 0.5 * (x + x + width);
        double cy = 0.5 * (y + y + height);

        width *= 8 * width;
        b1 = 8 * height * height;

        startAngle %= 360;
        arcAngle %= 360;
        int angleFinish = startAngle + arcAngle;

        while(x0 <= x1){
            double theta = Math.toDegrees(-Math.atan2(y1 - cy, x1 - cx));
            if (theta < 0) { theta += 360; }
            /* I четверть */
            if ((theta >= startAngle && theta <= angleFinish || theta + 360 >= startAngle && theta + 360 <= angleFinish))
                pd.drawPixel(x1, y1, color);
            /* II четверть */
            if (180 - theta >= startAngle && 180 - theta <= angleFinish || 540 - theta >= startAngle && 540 - theta <= angleFinish)
                pd.drawPixel(x0, y1, color);
            /* III четверть */
            if (180 + theta >= startAngle && 180 + theta <= angleFinish || 540 + theta >= startAngle && 540 + theta <= angleFinish)
                pd.drawPixel(x0, y0, color);
            /* IV четверть */
            if (360 - theta >= startAngle && 360 - theta <= angleFinish || 720 - theta >= startAngle && 720 - theta <= angleFinish)
                pd.drawPixel(x1, y0, color);
            e2 = 2 * err;
            if (e2 <= dy) {
                y0++;
                y1--;
                err += dy += width;
            }
            if (e2 >= dx || 2 * err > dy) {
                x0++;
                x1--;
                err += dx += b1;
            }
        }
    }
}