package com.aisera.jsonanalyzer;

import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		Scanner userInput = new Scanner(System.in);
		System.out.print("\nInput filename: ");
		String filename = userInput.nextLine();

		System.out.print("\nInput size of chunks: ");
		int chunSize = Integer.parseInt(userInput.nextLine());

		userInput.close();

		Analyzer analyzer = new Analyzer(filename, chunSize);
		analyzer.analyze();

	}
}
