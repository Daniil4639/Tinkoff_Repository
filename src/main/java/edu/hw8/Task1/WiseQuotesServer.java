package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WiseQuotesServer {

    private volatile String n = "";
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_MESSAGE = "Server is closed!";
    private final static int PORT_NUMBER = 4004;
    private final ExecutorService executorService;
    private boolean serverOk;

    public WiseQuotesServer() {
        serverOk = false;
        executorService = Executors.newFixedThreadPool(2);
    }

    public void open() {
        serverOk = true;
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            while (serverOk) {
                Socket client = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String message = input.readLine();
                if (message.equals("/end")) {
                    serverOk = false;
                }

                executorService.execute(new WiseQuoteRequest(client, message));
            }

            executorService.shutdown();
        } catch (IOException error) {
            LOGGER.info(ERROR_MESSAGE);
            throw new RuntimeException();
        }
    }
}
