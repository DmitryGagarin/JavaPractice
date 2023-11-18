import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class FetchQuote extends FetchJoke{

    private static HttpsURLConnection connection;
    public static BufferedReader reader;
    public static String line;
    public static Random random;
    public static int randomQuote;
    public static int status;
    public static String link;
    public static StringBuilder response = new StringBuilder();
    public static ArrayList<String> listOfQuotes = new ArrayList<>();
    protected static void fetchQuote() {
        try {
            connectionSettings();

            getStatus();

            deleteRedundantChars();

            getQuotes(response, listOfQuotes);

            reader.close();

            generateRandom();

            showResults(listOfQuotes, randomQuote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteRedundantChars() {
        for (int i = 0; i < response.length(); i++) {
            char currentChar = response.charAt(i);
            if (currentChar == '}' || currentChar == '{' || currentChar == '[' || currentChar == ']') {
                response.setCharAt(i, '\n');
            }
        }
    }

    private static void getQuotes(StringBuilder response, ArrayList<String> listOfQuotes) {
        String[] parts = response.toString().split("}");
        for (String part : parts) {
            if (part.contains("text")) {
                String[] fields = part.split(",");
                for (String field : fields) {
                    if (field.contains("text")) {
                        String[] quoteArray = field.split(":");
                        String quote = quoteArray[1].replaceAll("\"", "").trim();
                        listOfQuotes.add(quote);
                    }
                }
            }
        }
    }

    private static void showResults(ArrayList<String> listOfQuotes, int randomQuote) {
        System.out.println(listOfQuotes.get(randomQuote));
    }

    private static void getStatus() {
        try {
            status = connection.getResponseCode();
            if (status != 200) {
                System.out.println("Connection status: " + status);
            }
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connectionSettings() {
        try {
            link = "https://type.fit/api/quotes";
            URL url = new URL(link);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateRandom() {
        random = new Random();
        randomQuote = random.nextInt(listOfQuotes.size());
    }
}
