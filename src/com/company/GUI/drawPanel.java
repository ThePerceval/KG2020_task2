package com.company.GUI;

import com.company.LineDrawers.Bresenhaim;
import com.company.LineDrawers.DDALineDrawer;
import com.company.LineDrawers.WuLineDrawer;
import com.company.grapicsDrawer.graphicsDrawer;
import com.company.utils.bufferedImagePixelDrawer;
import com.company.utils.graphicsUtils;
import com.company.utils.lineDrawer;
import com.company.utils.pixelDrawer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class drawPanel extends JPanel implements MouseMotionListener, MouseListener {
    private List<lineDrawer> list;
    private Point currentPosition = new Point(0, 0);

    public drawPanel(){
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gr = bi.createGraphics();
        gr.setColor(Color.white);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.black);
        gr.dispose();
        pixelDrawer pd = new bufferedImagePixelDrawer(bi);

        list = new ArrayList<lineDrawer>();
        list.add(new Bresenhaim(pd));
        list.add(new DDALineDrawer(pd));
        list.add(new WuLineDrawer(pd));


        drawAll();

        g.drawImage(bi,0,0, null);
    }

    private void drawAll(){
        for (int i = 0; i < 3; i++) {
            graphicsUtils.drawSnowFlake(list.get(i), 150 + 250 * i, 150, 100, 64);
            list.get(i).drawLine(400 + 250 * (i - 1), 400, (int)currentPosition.getX() + 250 * (i - 1), (int)currentPosition.getY());
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentPosition = e.getPoint();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentPosition = e.getPoint();
    }
}
