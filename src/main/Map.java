package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    public static int imgWidth;
    public static int imgHeight;
    public static ArrayList<ArrayList<Integer>> bufferedMap = new ArrayList<ArrayList<Integer>>();
    public ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
    public Image wall_v;
    public Image wall_h;
    public Image corner_down_left;
    public Image corner_down_right;
    public Image corner_up_left;
    public Image corner_up_right;


    public Map (int level) {
        wall_h = new ImageIcon("resources/assets/map/wall_h.png").getImage();
        wall_v = new ImageIcon("resources/assets/map/wall_v.png").getImage();
        corner_down_left = new ImageIcon("resources/assets/map/corner_down_left.png").getImage();
        corner_down_right = new ImageIcon("resources/assets/map/corner_down_right.png").getImage();
        corner_up_left = new ImageIcon("resources/assets/map/corner_up_left.png").getImage();
        corner_up_right = new ImageIcon("resources/assets/map/corner_up_right.png").getImage();

        imgWidth = PacmanApp.WIDTH / 28;
        imgHeight = PacmanApp.HEIGHT / 30;

        generateBufferedMap(level);
        generateTiles();
    }

    private void generateBufferedMap(int level) {
        try {
            FileReader fileReader = new FileReader("resources/data/level"+level);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();

            while (line != null) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < line.length(); i++) {
                    list.add(Character.getNumericValue(line.charAt(i)));
                }
                bufferedMap.add(list);
                line = reader.readLine();

            }
            reader.close();
            System.out.println("rows : " + bufferedMap.size());
            System.out.println("columns : " + bufferedMap.get(0).size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateTiles() {
        ArrayList<ArrayList<Integer>> rows = bufferedMap;

        for (int row = 0; row < rows.size(); row++) {
            ArrayList<Integer> columns = rows.get(row);
            for (int column = 0; column < columns.size(); column++) {
                Tile tile = new Tile(row, column);
                if (row == 0)
                    if (column == 0)
                        tile.setImage(corner_up_left);
                    else if (column == columns.size() - 1)
                        tile.setImage(corner_up_right);
                    else
                        tile.setImage(wall_h);
            }
        }
    }

    // public Image getImage(int row, int column) {
    // Tile tile = new Tile(row, column);
    // int value = bufferedMap.get(row).get(column);
    // if (value == 0) {
    //     if (row == 0) {
    //         if (column == 0)
    //             return corner_up_left;
    //         else if (column == bufferedMap.get(0).size() - 1)
    //             return corner_up_right;
    //         else
    //             return wall_h;
    //     } else if (row == bufferedMap.size() - 1) {
    //         if (column == 0)
    //             return corner_down_left;
    //         else if (column == bufferedMap.get(0).size() - 1)
    //             return corner_down_right;
    //         else return wall_h;
    //     } else {
    //         if (column == 0) {
    //             if (bufferedMap.get(row - 1).get(column) == 0 && bufferedMap.get(row + 1).get(column) == 0 && bufferedMap.get(row).get(column + 1) == 1)
    //                 return wall_v;
    //              else if (map(row - 1, column) == 0 && map(row, column + 1) == 0 && map(row + 1, column) == 1 && map(row - 1, column + 1) == 1)
    //                  return corner_down_left;
    //              else if (map(row + 1, column) == 1 && map(row - 1, column) == 1 && map(row, column + 1) == 0)
    //                  return wall_h;
    //              else if (map(row + 1, column) == 0 && map(row - 1, column) == 1 && map(row, column + 1) == 0 && map(row + 1, column + 1) == 1)
    //                  return corner_up_left;
    //         } else if (column == bufferedMap.get(0).size() - 1) {
    //             if (map(row - 1, column) == 0 && map(row + 1, column) == 0 && map(row, column - 1) == 0)
    //                 return wall_v;
    //             else if (map(row + 1, column) == 1 && map(row, column - 1) == 0 && map(row - 1, column) == 0 && map(row - 1, column - 1) == 1)
    //                 return corner_down_right;
    //         }
    //     }
    // }
    // return null;
    //
    // }
}
