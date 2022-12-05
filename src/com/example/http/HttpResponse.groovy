package com.example.http

import java.net.HttpURLConnection

class HttpResponse {

    String body;
    String message;
    Integer statusCode;
    boolean failure = false;

    public HttpResponse(HttpURLConnection connection) {
        this.statusCode = connection.responseCode;
        this.message = connection.responseMessage;
        def stringStatusCode = statusCode.toString()
        println "Request statusCode: ${statusCode}"
        println "Request responseMessage: ${message}"
        if (stringStatusCode.startsWith("20")) {
            this.body = connection.content.text;
        } else {
            this.failure = true;
            this.body = connection.getErrorStream().text;
        }
    }
}