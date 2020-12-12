package com.company.utils.interfaces;

import java.awt.*;

public interface IDrawer {
    void setColor(Color color);
    void drawLine(int x1, int y1, int x2, int y2);
    void drawCircle(int x1, int y1, int x2, int y2);
    void drawArc(int x1, int y1, int x2, int y2, int startAngle, int arcAngle);
    void drawOval(int x1, int y1, int x2, int y2);
    void drawPie(int x1, int y1, int x2, int y2, int startAngle, int arcAngle);
    void fillOval(int x1, int y1, int x2, int y2);
    void fillPie(int x1, int y1, int x2, int y2, int startAngle, int arcAngle);
}
