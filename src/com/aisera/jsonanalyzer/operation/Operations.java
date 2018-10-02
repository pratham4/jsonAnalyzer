package com.aisera.jsonanalyzer.operation;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Operations {
	public static <T> Double mean(List<T> items, Function<T, Double> selector) {
		Double sum = items.stream().mapToDouble(item -> selector.apply(item)).sum();
		return sum / items.size();
	}

	public static <T> Double median(List<T> items, Function<T, Double> selector) {
		List<Double> values = items.stream().map(item -> selector.apply(item)).sorted().collect(Collectors.toList());
		return values.get(values.size() / 2);
	}

	public static <T> Double count(List<T> items, Function<T, Double> selector) {
		return items.stream().map(item -> selector.apply(item)).mapToDouble((item) -> (Double) item).sum();
	}
}
