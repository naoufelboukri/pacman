package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class PacmanApp extends Canvas implements Runnable {

    public static int WIDTH = 800;
    public static int HEIGHT = 800;
    public static int DELTA = 20;
    public Map map;

    Window window;
    Thread thread;

    public PacmanApp() {
        window = new Window(this);
        thread = new Thread(this);

        map = new Map(1);

        thread.start();
    }

    @Override
    public void run() {
        long timer = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() > timer + DELTA) {
                timer += DELTA;
                Tick();
                Render();
            }
        }
    }

    public void Tick() {

    }

    public void Render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2g = (Graphics2D) g;

        g2g.setColor(Color.BLACK);
        g2g.fillRect(0, 0, WIDTH, HEIGHT);

        ArrayList<ArrayList<Tile>> rows = map.tiles;
        for (int row = 0; row < rows.size(); row++) {
            ArrayList<Integer> columns = map.bufferedMap.get(row);
            for (int column = 0; column < columns.size(); column++) {
                Tile tile = map.tiles.get(row).get(column);
                g2g.drawImage(tile.image, column * Map.imgWidth, row * Map.imgHeight, Map.imgWidth, Map.imgHeight, null);
            }
        }

        g2g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        new PacmanApp();
    }
}
