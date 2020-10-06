package com.HuibVanStraten;

public class GameOfLife {

    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    int width;
    int height;
    int[][] life;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        this.life = new int[width][height];
    }

    public void setState(int x, int y, int state) {
        if (x < 0 || x >= width) {
            return;
        }
        if (y < 0 || y >= height) {
            return;
        }
        this.life[x][y] = state;
    }

    public int countAliveNeighbours(int x, int y) {
        int count = 0;

        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);
        count += getState(x - 1, y);
        count += getState(x + 1, y);
        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }

    public void nextGeneration() {
        int[][] tempLife = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbours = countAliveNeighbours(x, y);

                if (getState(x, y) == ALIVE) {
                    if (aliveNeighbours < 2) {
                        tempLife[x][y] = DEAD;
                    } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        tempLife[x][y] = ALIVE;
                    } else {
                        tempLife[x][y] = DEAD;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        tempLife[x][y] = ALIVE;
                    }
                }
            }
        }
        this.life = tempLife;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }
        if (y < 0 || y >= height) {
            return 0;
        }
        return this.life[x][y];
    }
}