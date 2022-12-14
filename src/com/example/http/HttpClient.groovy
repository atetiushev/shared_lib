package com.example.http

class HttpClient {
    protected def script
    protected String url
    protected String userName
    protected String password
    protected String userToken
    protected String requestMethod = "GET"
    protected Map headers = [:]
    protected Converter responseBodyConverter;
    protected int timeout = 30000;//in ms

    HttpClient() {
        this(null)
    }

    HttpClient(script) {
        this.@script = script
    }

    HttpClient withUrl(String url) {
        this.@url = url
        return this
    }

    HttpClient withRequestMethod(String requestMethod) {
        this.@requestMethod = requestMethod
        return this
    }

    HttpClient withHeader(String header, String value) {
        this.@headers[header] = value
        return this
    }

    HttpClient withResponseBodyConverter(Converter converter) {
        this.@responseBodyConverter = converter
        return this
    }

    HttpClient withUserName(String userName) {
        this.@userName = userName
        return this
    }

    HttpClient withPassword(String password) {
        this.@password = password
        return this
    }

    HttpClient withUserToken(String userToken) {
        this.@userName = null
        this.@password = null
        return this
    }

    HttpClient withTimeout(int timeout) {
        this.timeout = timeout
        return this
    }

    HttpResponse execute() {
        URL url = new URL(this.@url)
        HttpURLConnection connection = url.openConnection()
        connection.setRequestMethod(this.@requestMethod)
        connection.setConnectTimeout(this.timeout)
        connection.setReadTimeout(this.timeout)
        setRequestHeaders(connection)

        connection.connect()

        HttpResponse resp = new HttpResponse(connection)
        if (responseBodyConverter != null && !resp.failure) {
            resp.body = responseBodyConverter.convert(resp.body)
        }
        return resp
    }

    def setRequestHeaders(HttpURLConnection connection) {
        setBasicAuth()
        headers.each { header, value ->
            connection.setRequestProperty(header, value)
        }
    }

    def setBasicAuth() {
        String token = this.@userToken
        if (this.@userName && this.@password) {
            token = this.@userName + ":"+ this.@password
        }
        if(token) {
            String encoded =  Base64.getEncoder().encode(token.getBytes("UTF-8"))
            this.@headers["Authorization"] = "Basic " + encoded
        }
    }
}