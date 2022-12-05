import com.example.http.HttpClient
import com.example.http.HttpResponse

def call(String url, Map headers = [:]) {
    HttpClient client = new HttpClient();
    HttpResponse response = client.doGetHttpRequest(url, headers)
    echo "response status " + response.getStatusCode()
    echo "response " + response.body
}
