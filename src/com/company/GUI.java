package com.company;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI() throws HeadlessException {
        add(new drawPanel());
    }
}
