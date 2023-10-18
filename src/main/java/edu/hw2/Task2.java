package edu.hw2;

public class Task2 {

    public static class Rectangle {
        private int width;
        private int height;

        public Rectangle setWidth(int width) {
            this.width = width;
            return this;
        }

        public Rectangle setHeight(int height) {
            this.height = height;
            return this;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        @Override
        public Rectangle setWidth(int width) { //изменение длины квадрата превращает
            Rectangle newRec = new Rectangle(); //его в прямоугольник
            newRec.setWidth(width);
            newRec.setHeight(this.getHeight());
            return newRec;
        }

        @Override
        public Rectangle setHeight(int height) { //изменение ширины квадрата превращает
            Rectangle newRec = new Rectangle(); //его в прямоугольник
            newRec.setHeight(height);
            newRec.setHeight(this.getWidth());
            return newRec;
        }

        public void setSideLength(int sideLength) {
            setWidth(sideLength);
            setHeight(sideLength);
        }
    }
}
