import com.example.HttpClient

def call(String url) {
    HttpClient client = new HttpClient();
    HttpClient.HttpResponse response = client.doGetHttpRequest(url)
    echo "response status " + response.getStatusCode()
    echo "response " + response.body
}
