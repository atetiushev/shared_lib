package com.example.http

import java.net.HttpURLConnection

class HttpResponse {

    String body;
    String message;
    Integer statusCode;
    boolean failure = false;

    HttpResponse(HttpURLConnection connection) {
        this.statusCode = connection.responseCode;
        this.message = connection.responseMessage;
        def stringStatusCode = statusCode.toString()
        println 'Just a string - is this working at all - println'
        println "Just a string - is this working at all println 2"
        println "Just a string - is this working at all println with var ${stringStatusCode}"
        echo 'Just a string - is this working at all'
        echo "Just a string - is this working at all 2"

        echo('Just a string - is this working at all '+ stringStatusCode)
        echo 'Just a string - is this working at all with variable ${stringStatusCode}'
        println "Just a string - is this working at all println with var ${stringStatusCode}"
        echo "Request statusCode: " + statusCode
        echo "Request responseMessage: " + message
        if (stringStatusCode.startsWith("20")) {
            this.body = connection.content.text;
        } else {
            this.failure = true;
            this.body = connection.getErrorStream().text;
        }
    }
}