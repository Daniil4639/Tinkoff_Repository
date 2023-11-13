package edu.hw6.Task6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task6 {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern PROCESS_PATTERN = Pattern.compile("<td>(<.*>)?([a-zA-Z0-9 _-]+)");
    private static final int MAXIMUM_PORT_NUMBER = 49151;
    private static final String SPACE_BETWEEN_PARAM = "    ";

    private Task6() {}

    public static void portCheck() {
        LOGGER.info("Протокол  Порт   Сервис");
        for (int portNumber = 0; portNumber <= MAXIMUM_PORT_NUMBER; portNumber++) {
            StringBuilder resultString = new StringBuilder();
            boolean serverOk = true;

            try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

                serverSocket.setReuseAddress(true);

            } catch (IOException ignored) {
                resultString.append("TCP       ").append(portNumber).append(SPACE_BETWEEN_PARAM);
                serverOk = false;
            }

            if (serverOk) {
                try (DatagramSocket datagramSocket = new DatagramSocket(portNumber)) {

                    datagramSocket.setReuseAddress(true);
                    continue;
                } catch (IOException ignored) {
                    resultString.append("UDP       ").append(portNumber).append(SPACE_BETWEEN_PARAM);
                }
            }

            Pattern portPattern = Pattern.compile("^<td>" + portNumber + ".*</td>$");
            Matcher portMatcher;
            boolean wasFound = false;

            String portTypes;
            try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("Port_List_TCP_and_UDP.txt"))) {

                while ((portTypes = bufferedReader.readLine()) != null) {
                    portMatcher = portPattern.matcher(portTypes);
                    if (portMatcher.find()) {
                        portTypes = bufferedReader.readLine();

                        Matcher processMatcher = PROCESS_PATTERN.matcher(portTypes);
                        if (processMatcher.find()) {
                            resultString.append(processMatcher.group(2));
                            wasFound = true;
                        }
                        break;
                    }
                }
            } catch (IOException ignored) {
            }

            if (!wasFound) {
                resultString.append("?");
            }

            LOGGER.info(resultString.toString());
        }
    }
}
