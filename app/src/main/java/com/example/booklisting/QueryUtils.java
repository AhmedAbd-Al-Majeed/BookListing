package com.example.booklisting;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    public static List<Book> fetchBookData(String requestUrl) {
        Log.i(LOG_TAG, "TEST: fetchBookData() ....");
        // create url object
        URL url = createUrl(requestUrl);
        // perform HTTP request to the url and receive a JSON response back
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        List<Book> books = extractFeatureFromJson(jsonResponse);
        return books;

    }

    // create url object
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {

            //opening http connection
            urlConnection = (HttpURLConnection) url.openConnection();
            //fire off the connection and be ready to get the results
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            //actual establish of the http connection
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                //input stream containing the results
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            //allows you to read a single character at a time
            //charset how to translate raw data into readable data one byte at a time
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            //read a larger chunk of data
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<Book> extractFeatureFromJson(String bookJson) {

        if (TextUtils.isEmpty(bookJson)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding earthquakes to
        List<Book> books = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(bookJson);
            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray itemsArray = baseJsonResponse.getJSONArray("items");
            // For each book in the bookArray, create an {@link Earthquake} object

            for (int i = 0; i < itemsArray.length(); i++) {
                // Get a single book at position i within the list of books
                JSONObject currentBook = itemsArray.getJSONObject(i);

                // For a given book, extract the JSONObject associated with the
                // key called "volumeInfo", which represents a list of all properties
                // for that book.
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                // For a given book, extract the JSONObject associated with the
                // key called "imageLinks", which represents a list of all properties
                // for that book.
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                // Extract the value for the key called "previewLink"
                String previewLink = volumeInfo.getString("previewLink");
                // Extract the value for the key called "publisher"
                String id = currentBook.getString("id");
                // Extract the value for the key called "thumbnail"
                String thumbnail = imageLinks.getString("thumbnail");
                // Extract the value for the key called "title"
                String title = volumeInfo.getString("title");


                // Create a new {@link Book} object with the title, publisher
                //from the JSON response.
                Book book = new Book(title, id, previewLink, thumbnail);
                // Add the new {@link Book} to the list of earthquakes.
                books.add(book);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        // Return the list of earthquakes
        return books;


    }


}
