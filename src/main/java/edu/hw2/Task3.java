package edu.hw2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task3 {

    private Task3() {
    }

    private final static Logger LOGGER = LoggerFactory.getLogger(Task3.class);
    private final static String SUCCESS_CONNECTION = "Connection is established!";
    private final static String CONNECTION_CLOSED = "Connection was closed!";
    private final static int PERCENT_100 = 100;
    private final static int PERCENT_90 = 90;
    private final static int PERCENT_85 = 85;

    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class StableConnection implements Connection {
        @Override
        public void execute(String command) { //всегда срабатывает
            LOGGER.info(SUCCESS_CONNECTION);
        }

        @Override
        public void close() throws Exception {
            LOGGER.info(CONNECTION_CLOSED);
        }
    }

    public static class FaultyConnection implements Connection {
        @Override
        public void execute(String command) {
            double randomConnect = Math.random() * PERCENT_100; //иногда возвращает исключение

            if (randomConnect <= PERCENT_90) { // ~90% шанс потери соединения
                throw new ConnectionException();
            } else {
                LOGGER.info(SUCCESS_CONNECTION);
            }
        }

        @Override
        public void close() throws Exception {
            LOGGER.info(CONNECTION_CLOSED);
        }
    }

    public static class DefaultConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            double randomConnect = Math.random() * PERCENT_100; //иногда возвращает проблемное соединение

            if (randomConnect >= PERCENT_85) { // ~15% получения стабильного соединения
                return new StableConnection();
            } else {
                return new FaultyConnection();
            }
        }
    }

    public static class ConnectionException extends RuntimeException {
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager = new DefaultConnectionManager();
        private final int maxAttempts = 10;

        void updatePackages() {
            tryExecute("apt update && apt upgrade -y");
        }

        private void tryExecute(String command) {
            int attemptCount = 0;
            Connection connect = manager.getConnection();
            boolean limitExceed = true;

            do {
                try {
                    connect = manager.getConnection();
                    connect.execute(command);

                    limitExceed = false;
                    break;
                } catch (ConnectionException connectionException) {

                    LOGGER.info("Connection error!");

                }
                attemptCount++;

                try {
                    connect.close();
                } catch (Exception exception) {
                    LOGGER.info("Connection close was failed!");
                    limitExceed = false;
                    break;
                }
            }
            while (attemptCount < maxAttempts);

            if (limitExceed) {
                LOGGER.info("Limit of attempts was exceeded!");
            }
        }
    }
}
