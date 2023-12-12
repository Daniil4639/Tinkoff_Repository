package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.PersonDatabaseLock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Проверка работы PersonDatabase")
    void personDatabaseTest() {
        PersonDatabase personDatabase = new PersonDatabase();

        Person vlad = new Person(1, "Vlad", "Moscow", "4482");
        Person alex = new Person(2, "Alex", "London", "9012");

        personDatabase.add(vlad);
        personDatabase.add(alex);

        Thread researchingVladThread = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                boolean ok = false;

                if (personDatabase.findByName("Vlad") != null) {
                    if (personDatabase.findByAddress("Moscow") != null && personDatabase.findByPhone("4482") != null) {
                        ok = true;
                    }
                } else {
                    ok = true;
                }

                assertThat(ok).isTrue();
            }
        });

        Thread deleteAndAddThread = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                switch (i%4) {
                    case 0 -> personDatabase.delete(1);
                    case 1 -> personDatabase.delete(2);
                    case 2 -> personDatabase.add(alex);
                    case 3 -> personDatabase.add(vlad);
                }
            }
        });

        Thread researchingAlexThread = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                boolean ok = false;

                if (personDatabase.findByName("Alex") != null) {
                    if (personDatabase.findByAddress("London") != null && personDatabase.findByPhone("9012") != null) {
                        ok = true;
                    }
                } else {
                    ok = true;
                }

                assertThat(ok).isTrue();
            }
        });

        researchingVladThread.start();
        researchingAlexThread.start();
        deleteAndAddThread.start();

        try {
            researchingVladThread.join();
            researchingAlexThread.join();
            deleteAndAddThread.join();
        } catch (InterruptedException e) {
            assertThat(true).isFalse();
        }
    }

    @Test
    @DisplayName("Проверка работы PersonDatabaseLock")
    void personDatabaseLockTest() {
        PersonDatabaseLock personDatabase = new PersonDatabaseLock();

        Person vlad = new Person(1, "Vlad", "Moscow", "4482");
        Person alex = new Person(2, "Alex", "London", "9012");

        personDatabase.add(vlad);
        personDatabase.add(alex);

        Thread researchingVladThread = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                boolean ok = false;

                if (personDatabase.findByName("Vlad") != null) {
                    if (personDatabase.findByAddress("Moscow") != null && personDatabase.findByPhone("4482") != null) {
                        ok = true;
                    }
                } else {
                    ok = true;
                }

                assertThat(ok).isTrue();
            }
        });

        Thread deleteAndAddThread = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                switch (i%4) {
                    case 0 -> personDatabase.delete(1);
                    case 1 -> personDatabase.delete(2);
                    case 2 -> personDatabase.add(alex);
                    case 3 -> personDatabase.add(vlad);
                }
            }
        });

        Thread researchingAlexThread = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                boolean ok = false;

                if (personDatabase.findByName("Alex") != null) {
                    if (personDatabase.findByAddress("London") != null && personDatabase.findByPhone("9012") != null) {
                        ok = true;
                    }
                } else {
                    ok = true;
                }

                assertThat(ok).isTrue();
            }
        });

        researchingVladThread.start();
        researchingAlexThread.start();
        deleteAndAddThread.start();

        try {
            researchingVladThread.join();
            researchingAlexThread.join();
            deleteAndAddThread.join();
        } catch (InterruptedException e) {
            assertThat(true).isFalse();
        }
    }
}
