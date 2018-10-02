package com.aisera.jsonanalyzer;

import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Input filename: ");
		String filename = userInput.nextLine();

		System.out.print("Input size of chunks: ");
		int chunSize = Integer.parseInt(userInput.nextLine());

		Analyzer analyzer = new Analyzer(filename, chunSize);
		analyzer.analyze();

	}
}
