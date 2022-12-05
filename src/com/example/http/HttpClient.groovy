package com.example.http

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.net.HttpURLConnection

class HttpClient {

    HttpResponse doGetHttpRequest(String requestUrl, Map headers = [:]) {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = url.openConnection();
        setRequestHeaders(connection, headers)

        connection.setRequestMethod("GET");

        //get the request
        connection.connect();

        //parse the response
        HttpResponse resp = new HttpResponse(connection);

        if (resp.isFailure()) {
            error("\nGET from URL: $requestUrl\n  HTTP Status: $resp.statusCode\n  Message: $resp.message\n  Response Body: $resp.body");
        }

        return resp;
    }

    private def setRequestHeaders(HttpURLConnection connection, Map headers = [:]) {
        headers.each { header, value ->
            connection.setRequestProperty(header, value)
        }
    }
}