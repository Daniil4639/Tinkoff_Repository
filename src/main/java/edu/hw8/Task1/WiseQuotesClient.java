package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WiseQuotesClient {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static Scanner IN = new Scanner(System.in);
    private final static String ERROR_MESSAGE = "Request went wrong!";
    private final static String END_LINE = "/end";
    private final static int PORT_NUMBER = 4004;

    private WiseQuotesClient() {}

    public static void makeRequest(String message) {
        try (Socket client = new Socket("localhost", PORT_NUMBER)) {
            try (PrintWriter output = new PrintWriter(client.getOutputStream())) {
                try (BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                    output.println(message);
                    output.flush();

                    if (!message.equals(END_LINE)) {
                        LOGGER.info(input.readLine());
                    }
                }
            }
        } catch (IOException error) {
            LOGGER.info(ERROR_MESSAGE);
            throw new RuntimeException();
        }
    }

    public static void closeClient() {
        makeRequest(END_LINE);
    }
}
