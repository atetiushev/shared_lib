package com.example.http

class JsonHttpClient extends HttpClient {

    private String userName
    private String password

    JsonHttpClient() {
        this(null)
    }

    JsonHttpClient(script) {
        super(script)
        this.responseBodyConverter = new JsonConverter();
    }

    HttpClient withUserName(String userName) {
        this.@userName = userName
        return this
    }

    HttpClient withPassword(String password) {
        this.@password = password
        return this
    }

}