package com.company.GUI;

import com.company.LineDrawers.Bresenhaim;
import com.company.LineDrawers.DDALineDrawer;
import com.company.LineDrawers.WuLineDrawer;
import com.company.grapicsDrawer.graphicsDrawer;
import com.company.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class drawPanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener{
    private List<lineDrawer> list;
    private Point currentPosition = new Point(0, 0);
    private List<graphicsDrawerInterface> listFigure = new ArrayList<graphicsDrawerInterface>();
    private boolean control = true;
    private boolean drawOval = false;
    private boolean fillOval = false;
    private boolean drawArc = false;
    private boolean drawCircle = false;


    public drawPanel(){
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.black);
        gr.dispose();
        pixelDrawer pd = new bufferedImagePixelDrawer(bi);

        graphicsDrawerInterface graphics = new graphicsDrawer(pd);
        graphics.setColor(Color.green);

        list = new ArrayList<lineDrawer>();
        list.add(new Bresenhaim(pd));
        list.add(new DDALineDrawer(pd));
        list.add(new WuLineDrawer(pd));
        list.get(0).setColor(Color.GREEN);
        list.get(1).setColor(Color.BLUE);
        list.get(2).setColor(new Color(128, 128, 255));

        drawAll();
        if( pointStart != null && current != null){
            if(drawOval)
                graphics.drawOval((int) pointStart.getX(), (int) pointStart.getY(), (int) current.getX(), (int)current.getY());
            if(fillOval)
                graphics.fillOval((int) pointStart.getX(), (int) pointStart.getY(), (int) current.getX(), (int) current.getY());
            if(drawArc)
                graphics.drawArc((int) pointStart.getX(), (int) pointStart.getY(),  (int) current.getX(),  (int)current.getY(), 0, 90);
            if(drawCircle)
                graphics.drawCircle((int) pointStart.getX(), (int) pointStart.getY(), Math.max((int)Math.abs((pointStart.getX() - current.getX())), (int) Math.abs(pointStart.getY()-current.getY())));
        }

        g.drawImage(bi,0,0, null);
    }

    private void drawAll(){
        if(control)
        for (int i = 0; i < 3; i++) {
            graphicsUtils.drawSnowFlake(list.get(i), 150 + 250 * i, 150, 100, 64);
            list.get(i).drawLine(400 + 250 * (i - 1), 400, (int)currentPosition.getX() + 250 * (i - 1), (int)currentPosition.getY());
        }
    }

    private Point pointStart = null;
    private Point current = null;
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            pointStart = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        current = e.getPoint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(pointStart != null){
            current = e.getPoint();
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentPosition = e.getPoint();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown()){
            control = !control;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_1){
            setAllFalse();
            drawCircle = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            setAllFalse();
            drawOval = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_3){
            setAllFalse();
            fillOval = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_4){
            setAllFalse();
            drawArc = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_5){
            setAllFalse();
            repaint();
        }
    }

    private void setAllFalse(){
        drawCircle = false;
        drawOval = false;
        drawArc = false;
        fillOval = false;
        pointStart = null;
        current = null;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}