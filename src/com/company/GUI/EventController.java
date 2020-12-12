package com.company.GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class EventController implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    private List<RepaintListener> listeners = new ArrayList<RepaintListener>();

    public interface RepaintListener {
        void shouldRepaint();
    }

    public void addRepaintListener(RepaintListener repaintListener){
        listeners.add(repaintListener);
    }

    public void removeRepaintListener(RepaintListener repaintListener){
        listeners.remove(repaintListener);
    }

    private void onRepaint(){
        for(RepaintListener listener : listeners){
            listener.shouldRepaint();
        }
    }

    protected Point pointStart = null;
    protected Point current = null;
    protected Point currentPosition = new Point(0, 0);

    protected boolean control = true;
    protected boolean drawOval = false;
    protected boolean fillOval = false;
    protected boolean drawArc = false;
    protected boolean drawCircle = false;

    public Point getPointStart() { return pointStart; }
    public Point getCurrent() { return current; }

    private void setAllFalse(){
        drawCircle = false;
        drawOval = false;
        drawArc = false;
        fillOval = false;
        pointStart = null;
        current = null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown()){
            control = !control;
            onRepaint();
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
            onRepaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

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
        onRepaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentPosition = e.getPoint();
        onRepaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
