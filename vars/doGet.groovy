import com.example.http.HttpClient
import com.example.http.HttpResponse
import com.example.json.converter.ConfigReader

def call(String url, Map headers = [:]) {
    HttpClient client = new HttpClient(this);
    HttpResponse response = client.doGetHttpRequest(url, headers)
    echo "response status " + response.getStatusCode()
    echo "response " + response.body
}

def convertJson() {
    def rawBody = libraryResource 'test-file.json'
    ConfigReader reader = new ConfigReader(this);
    reader.parseConfig(rawBody);
}
