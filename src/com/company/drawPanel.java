package com.company;

import com.company.LineDrawers.Bresenhaim;
import com.company.LineDrawers.DDALineDrawer;
import com.company.LineDrawers.WuLineDrawer;
import com.company.grapicsDrawer.graphicsDrawer;
import com.company.utils.bufferedImagePixelDrawer;
import com.company.utils.lineDrawer;
import com.company.utils.pixelDrawer;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class drawPanel extends JPanel {
    List<lineDrawer> list;
    //graphicsDrawer graphics;

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


        int r = 100, n = 64, x = 200, y = 200;
        double da = Math.PI * 2 / n;
        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < n; i++) {
                double dx1 = r * Math.cos(da * i), dy1 = r * Math.sin(da * i);
                list.get(j).drawLine(x, y + 200 * j, x + (int)dx1,y + 200 * j +(int)dy1);
            }
        }

        g.drawImage(bi,0,0, null);
    }
}
