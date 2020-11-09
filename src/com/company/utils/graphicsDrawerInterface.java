package com.company.utils;

import java.awt.*;

public interface graphicsDrawerInterface {
    void setColor(Color color);
    void drawLine(int x, int y, int x2, int y2);
    void drawCircle(int x, int y, int size);
    void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle);
    void drawOval(int x, int y, int width, int height);
    void fillOval(int x, int y, int width, int height);
    void fillPie(int x, int y, int width, int height, int startAngle, int arcAngle);
}
