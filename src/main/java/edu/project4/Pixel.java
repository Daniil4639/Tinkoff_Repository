package edu.project4;

public class Pixel {

    int xCoordinate;
    int yCoordinate;
    byte rChanel;
    byte gChanel;
    byte bChanel;
    long hitCount;
    double normal;

    public Pixel(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
        hitCount = 0;
        rChanel = 0;
        gChanel = 0;
        bChanel = 0;
        normal = 0;
    }
}
