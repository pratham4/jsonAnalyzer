package com.aisera.jsonanalyzer.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class GreetingCountDeserializer  extends JsonDeserializer<Double> {
	@Override
	public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		String data = jsonParser.getText();
		List<String> numbers = new ArrayList<String>();
		
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(data); 
		while (m.find()) {
		   numbers.add(m.group());
		}
		if (numbers.size() != 1) {
			throw new NumberFormatException("The data doesn't contain valid numbers");
		}
		return Double.parseDouble(numbers.get(0));
	}

}
