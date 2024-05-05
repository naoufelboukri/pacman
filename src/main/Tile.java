package main;

import java.awt.*;
import java.util.ArrayList;

public class Tile {
    public int row;
    public int col;
    public Image image;
    private ArrayList<ArrayList<Integer>> tiles = Map.bufferedMap;

    public Tile(int r, int c) {
        row=r;
        col=c;
    }

    public void setImage(Image img) {
        image = img;
    }
}
