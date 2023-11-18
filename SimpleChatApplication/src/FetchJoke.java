import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class FetchJoke extends Hello {
    public static HttpsURLConnection connection;
    public static BufferedReader reader;
    public static String line;
    public static Random random;
    public static int randomJokeSetupAndPunchline;
    public static int status;
    public static StringBuilder response = new StringBuilder();
    public static ArrayList<String> listOfJokesSetups = new ArrayList<>();
    public static ArrayList<String> listOfJokesPunchlines = new ArrayList<>();
    public static String link;

    protected static void fetchJoke() {
        try {
            connectionSettings();

            getStatus();

            deleteRedundantChars();

            extractInfo(response, listOfJokesSetups, "setup");
            extractInfo(response, listOfJokesPunchlines, "punchline");

            reader.close();

            generateRandom();

            showResults(listOfJokesSetups, listOfJokesPunchlines, randomJokeSetupAndPunchline);
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

    private static void extractInfo(StringBuilder response, ArrayList<String> infoList, String key) {
        String[] parts = response.toString().split(key.equals("setup") ? "}" : "\\{");
        for (String part : parts) {
            if (part.contains(key)) {
                String[] fields = part.split(",");
                for (String field : fields) {
                    if (field.contains(key)) {
                        String[] infoArray = field.split(":");
                        String info = infoArray[1].replaceAll("\"", "").trim();
                        infoList.add(info);
                    }
                }
            }
        }
    }

    private static void showResults(ArrayList<String> listOfJokesSetups, ArrayList<String> listOfJokesPunchlines, int randomJokeSetupAndPunchline) {
        System.out.println(listOfJokesSetups.get(randomJokeSetupAndPunchline));
        System.out.println(listOfJokesPunchlines.get(randomJokeSetupAndPunchline + 1));
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
            link = "https://api.sampleapis.com/jokes/goodJokes";
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
        randomJokeSetupAndPunchline = random.nextInt(listOfJokesPunchlines.size());
    }
}
