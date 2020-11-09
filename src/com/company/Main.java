package com.company;

import com.company.GUI.GUI;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(Math.toDegrees(Math.atan2(-30, -30)));
        System.out.println(Math.toDegrees(Math.atan2(30, -30)));
        System.out.println(Math.toDegrees(Math.atan2(-30, 30)));
        System.out.println(Math.toDegrees(Math.atan2(30, 30)));


        GUI window = new GUI();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(new Dimension(800, 600));
        window.setVisible(true);
    }
}
