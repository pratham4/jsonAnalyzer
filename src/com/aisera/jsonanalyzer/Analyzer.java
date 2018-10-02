package com.aisera.jsonanalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aisera.jsonanalyzer.entity.User;
import com.aisera.jsonanalyzer.iterator.JsonIterator;
import com.aisera.jsonanalyzer.resultPrinter.ConsoleResultPrinter;

public class Analyzer {

    private String filename;
    private int chunkSize;

    public Analyzer(String filename, int chunkSize) {
        this.filename = filename;
        this.chunkSize = chunkSize;
    }

    public void analyze() throws IOException {
        JsonIterator<User> iterator = new JsonIterator<User>(filename, User.class);
        int index = 0;
        List<User> collector = new ArrayList<>();

        ConsoleResultPrinter consoleResultPrinter = new ConsoleResultPrinter();

        while (iterator.hasNext()) {
            collector.add(iterator.next());
            index++;
            if (index % chunkSize == 0) {
                consoleResultPrinter.printQueryResults(index, chunkSize, collector, false);
            }
        }

        iterator.close();

        if (index % chunkSize != 0) {
            consoleResultPrinter.printQueryResults(index, chunkSize, collector, true);
        }
    }
}
