import com.example.http.HttpClient
import com.example.http.HttpResponse

def call(String url) {
    HttpClient client = new HttpClient();
    HttpResponse response = client.doGetHttpRequest(url)
    echo "response status " + response.getStatusCode()
    echo "response " + response.body
}
