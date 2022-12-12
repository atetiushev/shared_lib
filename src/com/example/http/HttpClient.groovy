package com.example.http

class HttpClient {
    def script
    private String url;
    private Map<String, String> headers = [:]
    protected Converter responseBodyConverter;

    HttpClient() {
    }

    HttpClient withUrl(String url) {
        this.url = url
        return this
    }

    HttpClient withHeader(String header, String value) {
        this.headers[header] = value
        return this
    }

    HttpClient withResponseBodyConverter(Converter converter) {
        this.responseBodyConverter = converter
        return this
    }

    HttpClient(script) {
        this.script = script
    }

    HttpResponse execute() {
        URL url = new URL(this.url);
        HttpURLConnection connection = url.openConnection();
        connection.setRequestMethod("GET");
        setRequestHeaders(connection, headers)
        connection.connect();

        HttpResponse resp = new HttpResponse(connection);
        if(responseBodyConverter!=null && !resp.failure) {
            resp.body = responseBodyConverter.convert(resp.body)
        }
        return resp
    }


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