package com.aisera.jsonanalyzer;

import static com.aisera.jsonanalyzer.operation.Operations.mean;
import static com.aisera.jsonanalyzer.operation.Operations.median;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.aisera.jsonanalyzer.entity.User;

public class App {
	public static void main(String[] args) throws IOException {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Input filename:");

		String filename = userInput.nextLine();

		analyze(filename);

	}

	private static void analyze(String fileName) throws IOException {
		JsonIterator<User> iterator = new JsonIterator<User>(fileName, User.class);
		int index = 0;
		int chunkSize = 2;
		List<User> collector = new ArrayList<>();
		while (iterator.hasNext()) {
			collector.add(iterator.next());
			index++;
			if (index % chunkSize == 0) {
				printMedianAge(collector);
				printMedianFriendCount(collector);
				collector.clear();
			}
		}
	}

	private static void printMedianAge(List<User> collector) {
		System.out.println(median(collector, user -> user.getAge()));
		// System.out.println(median(collector, user -> user.getAge()));
		// System.out.println(median(collector, user -> user.getAge()));
		// System.out.println(median(collector, user -> user.getAge()));

	}

	private static void printMedianFriendCount(List<User> collector) {
		System.out.println(median(collector, user -> new Double(user.getFriends().size())));
	}

	private static void printMeanUnreadMessagesOfFemale(List<User> collector) {
		System.out.println(median(
				collector.stream().filter(user -> user.getGender().equals("Female")).collect(Collectors.toList()),
				user -> {
					return 0.0;

					// Parse the greeting and return number
				}));
	}

}
