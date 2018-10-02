package com.aisera.jsonanalyzer;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class JsonIterator<T> implements Iterator<T>, Closeable {
	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd'T'HH:mm:ssXXX").create();
	private final JsonReader reader;
	private final Class<T> clazz;
	
	public JsonIterator(final String fileName, Class<T> clazz) throws IOException {
    	reader = new JsonReader(new InputStreamReader(new FileInputStream(fileName)));
    	this.clazz = clazz;
    	reader.beginArray();
    }
	
    public boolean hasNext() {
        try {
			return reader.hasNext();
		} catch (IOException e) {
			close();
			throw new IllegalStateException();
		}
    }

    public T next() {
    	return gson.fromJson(reader, clazz);
    }
    
    public void close() {
        try {
            reader.close();
        } catch (final IOException ioe) {
        }
    }
}