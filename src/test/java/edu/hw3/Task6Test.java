package edu.hw3;

import edu.hw3.Task6.Task6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.PriorityQueue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    @DisplayName("Проверка работы StockMarket")
    void stockMarketTest() {
        Task6.StockMarket stockMarket = new Task6.StockMarket();

        stockMarket.add(new Task6.Stock("stock1", 3000));
        stockMarket.add(new Task6.Stock("stock2", 2002));
        stockMarket.add(new Task6.Stock("stock3", 9384));

        assertThat(stockMarket.mostValuableStock().getName()).isEqualTo("stock3");

        stockMarket.add(new Task6.Stock("stock1", 1500));

        PriorityQueue<Task6.Stock> stockPriorityQueue1 = stockMarket.seeQueue();
        assertThat(stockPriorityQueue1.size()).isEqualTo(4);

        stockMarket.remove(new Task6.Stock("stock1", 3000));

        PriorityQueue<Task6.Stock> stockPriorityQueue2 = stockMarket.seeQueue();
        assertThat(stockPriorityQueue2.size()).isEqualTo(3);
        assertThat(stockPriorityQueue2.poll().getPrice()).isEqualTo(9384);
        assertThat(stockPriorityQueue2.poll().getPrice()).isEqualTo(2002);
        assertThat(stockPriorityQueue2.poll().getPrice()).isEqualTo(1500);
    }
}
