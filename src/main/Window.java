package main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public JFrame frame;
    PacmanApp pacmanApp;

    public Window(PacmanApp app) {
        pacmanApp = app;

        Dimension screenSize = new Dimension(PacmanApp.WIDTH, PacmanApp.HEIGHT);
        frame = new JFrame("Pacman");

        frame.setLocation(new Point(0, 600));
        frame.setResizable(false);
        frame.add(pacmanApp);
        pacmanApp.setPreferredSize(screenSize);

        frame.pack();
        frame.setVisible(true);
    }
}
