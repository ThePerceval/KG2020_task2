package com.company.grapicsDrawer;

import com.company.utils.graphicsDrawerInterface;
import com.company.utils.pixelDrawer;

import java.awt.*;

public class graphicsDrawer implements graphicsDrawerInterface {
    private pixelDrawer pd;
    private Color color;

    public graphicsDrawer(pixelDrawer pd) {
        this.pd = pd;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    @Override
    public void drawArc(int x1, int y1, int width, int height, int startAngle, int arcAngle) {

    }

    @Override
    public void drawOval(int x1, int y1, int width, int height) {

    }

    @Override
    public void fillOval(int x1, int y1, int width, int height) {

    }

    @Override
    public void fillPie(int x2, int y1, int width, int height, int startAngle, int arcAngle) {

    }
}
