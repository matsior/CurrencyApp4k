package com.fourk.kursywalut4k.domain.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class APIConnections {

    public static String createStringFromJson(String urlAddress) throws IOException {
        URL request = new URL(urlAddress);
        URLConnection connection = request.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String data = bufferedReader.readLine();
        bufferedReader.close();
        return data;
    }
}
