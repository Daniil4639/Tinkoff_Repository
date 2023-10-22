package edu.hw3;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Task6 {

    static class Stock {

        private final double price;
        private final String name;

        Stock() {
            price = 0;
            name = "";
        }

        Stock(String receivedName, double receivedPrice) {
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

    static class StockComparator implements Comparator<Stock> {
        @Override
        public int compare(Stock stock1, Stock stock2) {
            int value = Double.compare(stock1.price, stock2.price);

            if (value > 0) {
                return -1;
            } else if (value < 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static class StockMarket {

        private PriorityQueue<Stock> stockPriorityQueue = new PriorityQueue<>(new StockComparator());

        void add(Stock stock) {
            stockPriorityQueue.add(stock);
        }

        void remove(Stock stock) {
            PriorityQueue<Stock> newQueue = new PriorityQueue<>(new StockComparator());

            Iterator<Stock> iterator = stockPriorityQueue.iterator();

            while (true) {
                if (iterator.hasNext()) {
                    Stock oldStock = iterator.next();

                    if (oldStock.name.equals(stock.name) && oldStock.price == stock.price) {
                        continue;
                    } else {
                        newQueue.add(oldStock);
                    }
                } else {
                    break;
                }
            }

            stockPriorityQueue = newQueue;
        }

        Stock mostValuableStock() {
            return stockPriorityQueue.peek();
        }

        PriorityQueue<Stock> seeQueue() {
            return stockPriorityQueue;
        }
    }
}
