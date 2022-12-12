import com.example.http.Converter
import com.example.http.HttpClient
import com.example.http.HttpResponse
import com.example.http.JsonConverter
import com.example.http.JsonHttpClient
import com.example.json.converter.ConfigReader

def call(String url, Map headers = [:]) {

    HttpResponse response = new JsonHttpClient(this).
            withUrl(url).withRequestMethod("GET").
            withUserName("user").
            withPassword("password").
            execute()
   // HttpResponse response = client.doGetHttpRequest(url, headers)
    echo "response status " + response.getStatusCode()
    echo "response " + response.body
}

def convertJson() {
    def rawBody = libraryResource 'test-file.json'
    ConfigReader reader = new ConfigReader(this);
    reader.parseConfig(rawBody);
}
