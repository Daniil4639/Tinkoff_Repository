package edu.hw8.Task3;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HackPassword {

    private static final int CODE_OF_LITTLE_A = 97;
    private static final int CODE_OF_LITTLE_Z = 122;
    private static final int MD5_SS = 16;
    private static final int MAX_PASSWORD_LENGTH = 5;
    private static final String HASH_FORMAT = "UTF-8";
    private static final int COMFORT_TIME_FOR_SLEEP = 10;
    private static final int MAX_NUMBER_BOUND = 9;
    private final MessageDigest md5;
    private final Map<String, String> hashMap;
    private final Map<String, String> resultMap;
    private final Queue<String> passwords;
    private final ReadWriteLock lock;

    public HackPassword(Map<String, String> hashes) {
        hashMap = new HashMap<>(hashes);
        resultMap = new HashMap<>();
        lock = new ReentrantReadWriteLock();
        passwords = new ArrayDeque<>();
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException error) {
            throw new RuntimeException();
        }
    }

    public Map<String, String> nextPasswordSingleThread() throws UnsupportedEncodingException {
        passwords.add("");

        while (!hashMap.isEmpty() && !passwords.isEmpty()) {
            String possiblePassword = passwords.poll();

            try {
                String possibleHash = (new BigInteger(1,
                    md5.digest(possiblePassword.getBytes(HASH_FORMAT)))).toString(MD5_SS);

                if (hashMap.containsKey(possibleHash)) {
                    resultMap.put(hashMap.get(possibleHash), possiblePassword);
                    hashMap.remove(possibleHash);
                }

            } catch (UnsupportedEncodingException error) {
                throw new RuntimeException();
            }

            if (possiblePassword.length() < MAX_PASSWORD_LENGTH) {

                for (int code = CODE_OF_LITTLE_A; code <= CODE_OF_LITTLE_Z; code++) {
                    passwords.add(possiblePassword + (char) code);
                }

                for (int code = 0; code <= MAX_NUMBER_BOUND; code++) {
                    passwords.add(possiblePassword + code);
                }
            }
        }

        passwords.clear();
        return resultMap;
    }

    public Map<String, String> nextPasswordMultiThread(int threadCount) {
        passwords.add("");

        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            Thread passwordThread = new Thread(() -> {

                while (!hashMap.isEmpty() && !passwords.isEmpty()) {
                    lock.readLock().lock();
                    String possiblePassword = passwords.poll();
                    lock.readLock().unlock();
                    if (possiblePassword == null) {
                        continue;
                    }

                    try {
                        String possibleHash = (new BigInteger(1,
                            md5.digest(possiblePassword.getBytes(HASH_FORMAT)))).toString(MD5_SS);

                        lock.writeLock().lock();
                        if (hashMap.containsKey(possibleHash)) {
                            resultMap.put(hashMap.get(possibleHash), possiblePassword);
                            hashMap.remove(possibleHash);
                        }
                        lock.writeLock().unlock();

                    } catch (UnsupportedEncodingException error) {
                        throw new RuntimeException();
                    }

                    if (possiblePassword.length() < MAX_PASSWORD_LENGTH) {
                        lock.writeLock().lock();
                        for (int code = CODE_OF_LITTLE_A; code <= CODE_OF_LITTLE_Z; code++) {
                            passwords.add(possiblePassword + (char) code);
                        }

                        for (int code = 0; code <= MAX_NUMBER_BOUND; code++) {
                            passwords.add(possiblePassword + code);
                        }
                        lock.writeLock().unlock();
                    }
                }
            });

            passwordThread.start();
        }

        while (!hashMap.isEmpty()) {
            try {
                Thread.sleep(COMFORT_TIME_FOR_SLEEP);
            } catch (InterruptedException error) {
                throw new RuntimeException();
            }
        }
        passwords.clear();
        return resultMap;
    }
}
