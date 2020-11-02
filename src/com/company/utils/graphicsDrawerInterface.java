package com.company.utils;

import java.awt.*;

public interface graphicsDrawerInterface {
    void drawLine(int x1, int y1, int x2, int y2);
    void drawArc(int x1, int y1, int width, int height, int startAngle, int arcAngle);
    void drawOval(int x1, int y1, int width, int height);
    void fillOval(int x1, int y1, int width, int height);
    void fillPie(int x2, int y1, int width, int height, int startAngle, int arcAngle);
}
