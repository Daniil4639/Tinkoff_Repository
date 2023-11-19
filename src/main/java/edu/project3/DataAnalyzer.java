package edu.project3;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAnalyzer {

    public final Map<String, Integer> resources;
    public final Map<Integer, Integer> codes;
    public final Map<String, Integer> ipCount;
    public final Map<String, Integer> userAgent;

    public BigInteger requestCount;
    public BigInteger requestSize;
    public final LocalDate dateFrom;
    public final LocalDate dateTo;
    public final String format;
    public List<String> fileNames;

    public DataAnalyzer(LocalDate dateFrom, LocalDate dateTo, String format) {
        requestCount = BigInteger.valueOf(0);
        requestSize = BigInteger.valueOf(0);
        ipCount = new HashMap<>();
        codes = new HashMap<>();
        userAgent = new HashMap<>();
        resources = new HashMap<>();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;

        if (format != null && format.equals("markdown")) {
            this.format = "md";
        } else {
            this.format = format;
        }
        fileNames = new ArrayList<>();
    }

    public void makeLinksAnalysis(List<String> urlList, List<String> localPath) {

        for (String url: urlList) {
            URLChecker.checkLink(this, url);
        }

        for (String path: localPath) {
            LocalPathChecker.checkPath(this, path);
        }

        printAnalysis();
    }

    private void printAnalysis() {
        StatisticPrinter statisticPrinter = new StatisticPrinter(this);
        statisticPrinter.printData();
    }
}
