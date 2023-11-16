import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class FetchJoke extends Hello {
    public static HttpsURLConnection connection;

    public static String fetchJoke() {
        BufferedReader reader;
        String line;
        StringBuilder response = new StringBuilder();
        String joke = "";
        String link = "https://api.sampleapis.com/jokes/goodJokes";
        String setup = "";

        try {
            URL url = new URL(link);
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("Connection status: " + status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            for (int i = 0; i < response.length(); i++) {
                char currentChar = response.charAt(i);
                if (currentChar == '}' || currentChar == '{' || currentChar == '[' || currentChar == ']') {
                    response.setCharAt(i, '\n');
                }
            }

            getTitle(response, setup);

            //System.out.println(response);

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return joke;
    }

    public static void getTitle(StringBuilder response, String setup) {
        String[] parts = response.toString().split("\\{");
        for (String part : parts) {
            if (part.contains("setup")) {
                String[] fields = part.split(",");
                for (String field : fields) {
                    if (field.contains("setup")) {
                        String[] titleArray = field.split(":");
                        setup = titleArray[1].replaceAll("\"", "").trim();
                    }
                    //System.out.println("Setup is: " + setup);
                }
            }
        }
    }
}
