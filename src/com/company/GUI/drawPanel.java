package com.company.GUI;

import com.company.LineDrawers.Bresenham;
import com.company.LineDrawers.DDAILineDrawer;
import com.company.LineDrawers.WuILineDrawer;
import com.company.utils.graphicsUtils.snowFlake;
import com.company.utils.interfaces.IDrawer;
import com.company.utils.interfaces.ILineDrawer;
import com.company.utils.interfaces.IPixelDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class drawPanel extends JPanel implements EventController.RepaintListener {
    private List<ILineDrawer> list;
    private IDrawer graphics;
    private EventController eventController;

    public drawPanel(){
        eventController = new EventController();
        eventController.addRepaintListener(this);
        this.setFocusable(true);
        this.addMouseListener(eventController);
        this.addMouseMotionListener(eventController);
        this.addKeyListener(eventController);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.black);

        IPixelDrawer pd = new IPixelDrawer() {
            @Override
            public void drawPixel(int x, int y, Color color) {
                gr.setColor(color);
                gr.fillRect(x, y, 1, 1);
            }
        };
        graphics = new Bresenham(pd);
        graphics.setColor(Color.green);

        list = new ArrayList<ILineDrawer>();
        list.add(new Bresenham(pd));
        list.add(new DDAILineDrawer(pd));
        list.add(new WuILineDrawer(pd));
        list.get(0).setColor(Color.GREEN);
        list.get(1).setColor(Color.BLUE);
        list.get(2).setColor(new Color(128, 128, 255));
        
        drawAll();

        gr.dispose();
        g.drawImage(bi,0,0, null);
    }

    private void drawAll(){
       if(eventController.control)
        for (int i = 0; i < 3; i++) {
            snowFlake.drawSnowFlake(list.get(i), 180 + 250 * i, 150, 100, 64);
            list.get(i).drawLine(400 + 250 * (i - 1), 400, (int)eventController.currentPosition.getX() + 250 * (i - 1), (int)eventController.currentPosition.getY());
        }
        if(eventController.getPointStart() != null && eventController.getCurrent() != null) {
            if (eventController.drawCircle)
                graphics.drawCircle((int) eventController.pointStart.getX(), (int) eventController.pointStart.getY(),
                        (int) eventController.current.getX(), (int) eventController.current.getY());
            if (eventController.drawOval)
                graphics.drawOval((int) eventController.pointStart.getX(), (int) eventController.pointStart.getY(),
                        (int) eventController.current.getX(), (int) eventController.current.getY());
            if (eventController.fillOval)
                graphics.fillOval((int) eventController.pointStart.getX(), (int) eventController.pointStart.getY(),
                        (int) eventController.current.getX(), (int) eventController.current.getY());
            if (eventController.drawArc)
                graphics.drawArc((int) eventController.pointStart.getX(), (int) eventController.pointStart.getY(),
                        (int) eventController.current.getX(), (int) eventController.current.getY(), 0, 90);
        }
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }
}