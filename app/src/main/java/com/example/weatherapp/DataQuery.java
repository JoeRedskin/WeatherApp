package com.example.weatherapp;

import android.util.Log;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DataQuery {
    public static City fetchCityData(String cityQuery, String tempType) {
        City city = null;

        String JSON_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
        String TYPE = "&units=" + (tempType.equals("C") ? "metric" : "imperial");
        String URL_TAIL = "&appid=cf7ef9156622ecccc8decb4a00a549b1";
        try {
            String stringUrl = JSON_WEATHER_URL+cityQuery+TYPE+URL_TAIL;
            URL url = new URL(stringUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                String jsonResponse = readFromStream(inputStream);
                JSONObject baseJsonResponse = new JSONObject(jsonResponse);

                JSONObject tempMain = baseJsonResponse.getJSONObject("main");
                Double temp = tempMain.getDouble("temp");
                String name = baseJsonResponse.getString("name");

                city = new City(name,tempType,temp);
            }
        } catch (Exception e) {
            Log.e("fetchCityData", "Problem parsing the city JSON results", e);
        }

        return city;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
