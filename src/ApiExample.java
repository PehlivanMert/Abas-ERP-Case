import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class ApiExample {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        try {
            System.out.println("GET isteği örneği:");
            getExample();

            System.out.println("\nPOST isteği örneği:");
            postExample();
        } catch (Exception e) {
            System.err.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getExample() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/1"))
                .GET()  // GET isteği olduğunu belirtiyoruz (varsayılan)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Durum Kodu: " + response.statusCode());
        System.out.println("Yanıt Gövdesi: " + response.body());
    }

    public static void postExample() throws IOException, InterruptedException {
        String postData = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(postData))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Durum Kodu: " + response.statusCode());
        System.out.println("Yanıt Gövdesi: " + response.body());
    }
}