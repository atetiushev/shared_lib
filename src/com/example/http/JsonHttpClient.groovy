package com.example.http

class JsonHttpClient extends HttpClient {
    JsonHttpClient() {
        this(null)
    }
    JsonHttpClient(script) {
        super(script)
        setResponseBodyConverter(new JsonConverter());
    }
}