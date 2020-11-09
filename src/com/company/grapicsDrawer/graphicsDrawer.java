package com.company.grapicsDrawer;

import com.company.utils.graphicsDrawerInterface;
import com.company.utils.pixelDrawer;

import java.awt.*;

public class graphicsDrawer implements graphicsDrawerInterface {
    private pixelDrawer pd;
    private Color color = Color.BLACK;

    public graphicsDrawer(pixelDrawer pd) {
        this.pd = pd;
    }


    @Override
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

    @Override
    public void drawCircle(int xC, int yC, int radius) {
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

    @Override
    public void drawArc(int x, int y, int x2, int y2, int startAngle, int arcAngle) {
        int width = Math.abs(x2 - x), height = Math.abs(y2 - y);
        y = Math.min(y, y2);
        x = Math.min(x, x2);
        int b1 = height & 1;
        long dx = 4 * (1 - width) * height * height;
        long dy = 4 * (b1 + 1) * width * width;
        long err = dx + dy + b1 * width * width;
        long e2 = 0;

        int y0 = y + (height + 1) / 2;
        int y1 = y0 - b1;
        int x0 = x;
        int x1 = x + width;

        double cx = 0.5 * (x + x + width);
        double cy = 0.5 * (y + y + height);

        width *= 8 * width;
        b1 = 8 * height * height;

        int angleFinish = startAngle + arcAngle;
        while(x0 <= x1){
            /** I четверть **/
            double theta = Math.toDegrees(-Math.atan2(y1 - cy, x1 - cx));
            //if (theta < 0) { theta += 360; }
            if (theta >= startAngle && theta <= angleFinish) { pd.drawPixel(x1, y1, color); }
            /** II четверть **/
            theta = Math.toDegrees(-Math.atan2(y1 - cy, x0 - cx));
            //if (theta < 0) { theta += 360; }
            if (theta >= startAngle && theta <= angleFinish) { pd.drawPixel(x0, y1, color); }
            /** III четверть **/
            theta = Math.toDegrees(-Math.atan2(y0 - cy, x0 - cx));
            if (theta < 0) { theta += 360; }
            if (theta >= startAngle && theta <= angleFinish) { pd.drawPixel(x0, y0, color); }
            /** IV четверть **/
            theta = Math.toDegrees(-Math.atan2(y0 - cy, x1 - cx));
            if (theta < 0) { theta += 360; }
            if (theta >= startAngle && theta <= angleFinish) { pd.drawPixel(x1, y0, color); }
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
        long e2 = 0;

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
            if (e2 >= dx || 2 * err > dy) {
                x0++;
                x1--;
                err += dx += b1;
            }
        }
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
        long e2 = 0;

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
    public void fillPie(int x, int y, int width, int height, int startAngle, int finishAngle) { }
}
