package com.example

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLConnection

class HttpClient {

    HttpResponse doGetHttpRequest(String requestUrl){
        URL url = new URL(requestUrl);
        HttpURLConnection connection = url.openConnection();

        connection.setRequestMethod("GET");

        //get the request
        connection.connect();

        //parse the response
        HttpResponse resp = new HttpResponse(connection);

        if(resp.isFailure()){
            error("\nGET from URL: $requestUrl\n  HTTP Status: $resp.statusCode\n  Message: $resp.message\n  Response Body: $resp.body");
        }

        this.printDebug("Request (GET):\n  URL: $requestUrl");
        this.printDebug("Response:\n  HTTP Status: $resp.statusCode\n  Message: $resp.message\n  Response Body: $resp.body");

        return resp;
    }

    class HttpResponse {

        String body;
        String message;
        Integer statusCode;
        boolean failure = false;

        public HttpResponse(HttpURLConnection connection) {
            this.statusCode = connection.responseCode;
            this.message = connection.responseMessage;

            if (statusCode == 200 || statusCode == 201) {
                this.body = connection.content.text;//this would fail the pipeline if there was a 400
            } else {
                this.failure = true;
                this.body = connection.getErrorStream().text;
            }

            connection = null; //set connection to null for good measure, since we are done with it
        }
    }
}