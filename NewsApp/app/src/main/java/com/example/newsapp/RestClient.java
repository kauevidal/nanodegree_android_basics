package com.example.newsapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static com.example.newsapp.JsonParser.parseJson;
import static java.net.HttpURLConnection.HTTP_OK;

final class RestClient {

    private static final String LOGGER = RestClient.class.getSimpleName();
    private static final String EMPTY_STRING = "";
    private static final String GET_METHOD = "GET";
    private static final Integer READ_TIMEOUT = 10000;
    private static final Integer CONNECTION_TIMEOUT = 15000;


    static List<News> getNews(String apiUrl) throws MalformedURLException {

        final URL url = new URL(apiUrl);

        try {
            return parseJson(get(url));
        } catch (IOException e) {
            Log.e(LOGGER, "Could not connect to News API.", e);
            return Collections.emptyList();
        }
    }

    private static String get(URL url) throws IOException {

        final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(CONNECTION_TIMEOUT);
        urlConnection.setConnectTimeout(READ_TIMEOUT);
        urlConnection.setRequestMethod(GET_METHOD);

        try (InputStream inputStream = urlConnection.getInputStream()) {
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HTTP_OK) {
                return readStream(inputStream);
            } else {
                Log.e(LOGGER, "News API returned errorCode=" + urlConnection.getResponseCode() + " and message=" + urlConnection.getResponseMessage());
            }
        } finally {
            urlConnection.disconnect();
        }
        return EMPTY_STRING;
    }


    private static String readStream(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())));
        StringBuilder builder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            builder.append(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return builder.toString();
    }
}
