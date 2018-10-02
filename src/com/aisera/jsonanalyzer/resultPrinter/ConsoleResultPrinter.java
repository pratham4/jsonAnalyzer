package com.aisera.jsonanalyzer.resultPrinter;

import static com.aisera.jsonanalyzer.operation.Operations.mean;
import static com.aisera.jsonanalyzer.operation.Operations.median;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.aisera.jsonanalyzer.entity.User;

public class ConsoleResultPrinter {

    public void printQueryResults(int index, int chunkSize, List<User> collector, boolean areRemaining) {
        if (!areRemaining) {
            System.out.println("\n---------- Chunk Number - " + index / chunkSize + " ----------");
        } else {
            System.out.println("\n---------- Remaining " + collector.size() + " no. of records ----------");
        }
        printMedianAge(collector);
        printMedianFriendCount(collector);
        printMeanBalanceAmount(collector);
        printMeanUnreadMessagesOfFemale(collector);
        printUsersRegisteredEachYear(collector);
        collector.clear();
    }

    private void printMedianAge(List<User> collector) {
        System.out.println("Median Age: " + median(collector, user -> user.getAge()));
    }

    private void printMedianFriendCount(List<User> collector) {
        System.out.println("Median Freind Count: " + median(collector, user -> new Double(user.getFriends().size())));
    }

    private void printMeanUnreadMessagesOfFemale(List<User> collector) {
        System.out.println("Mean Unread Messages of Female: " + mean(
                collector.stream().filter(user -> user.getGender().equals("female")).collect(Collectors.toList()),
                user -> Double.parseDouble(user.getGreeting().split("!")[1].replaceAll("[^0-9]+", ""))));
    }

    private void printMeanBalanceAmount(List<User> collector) {
        System.out.println("Mean Balance Amount: "
                + mean(collector, user -> Double.parseDouble(user.getBalance().replace("$", "").replace(",", ""))));
    }

    private void printUsersRegisteredEachYear(List<User> collector) {
        HashMap<Integer, Long> byYear = (HashMap<Integer, Long>) collector.stream()
                .collect(Collectors.groupingBy(p -> p.getRegisteredYear(), Collectors.counting()));

        byYear.forEach((year, count) -> System.out.println(count + " no. of users were registered for year " + year));
    }
}
