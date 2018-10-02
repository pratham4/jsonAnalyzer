package com.aisera.jsonanalyzer;

import static com.aisera.jsonanalyzer.operation.Operations.mean;
import static com.aisera.jsonanalyzer.operation.Operations.median;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.aisera.jsonanalyzer.entity.User;
import com.aisera.jsonanalyzer.iterator.JsonIterator;

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
        while (iterator.hasNext()) {
            collector.add(iterator.next());
            index++;
            if (index % chunkSize == 0) {
                System.out.println("---------- Chunk Number - " + index / chunkSize + " ----------");
                printMedianAge(collector);
                printMedianFriendCount(collector);
                // printMeanUnreadMessagesOfFemale(collector);
                collector.clear();
            }
        }
    }

    private void printMedianAge(List<User> collector) {
        System.out.println("Median Age: " + median(collector, user -> user.getAge()));
    }

    private void printMedianFriendCount(List<User> collector) {
        System.out.println("Median Freind Count: " + median(collector, user -> new Double(user.getFriends().size())));
    }

    private void printMeanUnreadMessagesOfFemale(List<User> collector) {
        System.out.println("Mean Unread Messages of Female: " + median(
                collector.stream().filter(user -> user.getGender().equals("Female")).collect(Collectors.toList()),
                user -> Double.parseDouble(user.getGreeting().split("!")[1].replaceAll("[^0-9]+", ""))));
    }

}
