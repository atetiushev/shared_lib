package com.example.json.converter


import java.net.HttpURLConnection
import java.net.URL

class HttpClient {
    def steps
    HttpClient(steps) {
        this.steps = steps
    }
    HttpResponse doGetHttpRequest(String requestUrl, Map headers = [:]) {
        steps.env.withewe
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