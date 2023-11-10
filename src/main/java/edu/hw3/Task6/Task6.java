package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Task6 {

    public static class Stock {

        private final double price;
        private final String name;

        public Stock(String receivedName, double receivedPrice) {
            name = receivedName;
            price = receivedPrice;
        }

        public double getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }
    }

    public static class StockComparator implements Comparator<Stock> {
        @Override
        public int compare(Stock stock1, Stock stock2) {
            return -Double.compare(stock1.price, stock2.price);
        }
    }

    public static class StockMarket {

        private PriorityQueue<Stock> stockPriorityQueue = new PriorityQueue<>(new StockComparator());

        public void add(Stock stock) {
            stockPriorityQueue.add(stock);
        }

        public void remove(Stock stock) {
            PriorityQueue<Stock> newQueue = new PriorityQueue<>(new StockComparator());

            for (Stock oldStock : stockPriorityQueue) {

                if (!oldStock.name.equals(stock.name) || oldStock.price != stock.price) {
                    newQueue.add(oldStock);
                }
            }

            stockPriorityQueue = newQueue;
        }

        public Stock mostValuableStock() {
            return stockPriorityQueue.peek();
        }

        public PriorityQueue<Stock> seeQueue() {
            return stockPriorityQueue;
        }
    }
}
