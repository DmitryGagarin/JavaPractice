import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class FetchJoke extends Hello {
    public static HttpsURLConnection connection;

    public static void fetchJoke() {
        BufferedReader reader;
        String line;
        StringBuilder response = new StringBuilder();
        ArrayList<String> listOfJokesSetups= new ArrayList<>();
        ArrayList<String> listOfJokesPunchlines = new ArrayList<>();
        String link = "https://api.sampleapis.com/jokes/goodJokes";

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

            getSetups(response, listOfJokesSetups);
            getPunchline(response, listOfJokesPunchlines);

            reader.close();

            Random random = new Random();

            int randomJokeSetupAndPunchline = random.nextInt(listOfJokesPunchlines.size());

            showResults(listOfJokesSetups, listOfJokesPunchlines, randomJokeSetupAndPunchline);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSetups(StringBuilder response, ArrayList<String> listOfJokesSetups) {
        String[] parts = response.toString().split("\\{");
        for (String part : parts) {
            if (part.contains("setup")) {
                String[] fields = part.split(",");
                for (String field : fields) {
                    if (field.contains("setup")) {
                        String[] setupArray = field.split(":");
                        String setup = setupArray[1].replaceAll("\"", "").trim();
                        listOfJokesSetups.add(setup);
                    }
                }
            }
        }
    }

    public static void getPunchline(StringBuilder response, ArrayList<String> listOfJokesPunchlines){
        String[] parts = response.toString().split("\\{");
        for (String part : parts) {
            if (part.contains("punchline")) {
                String[] fields = part.split(",");
                for (String field : fields) {
                    if (field.contains("punchline")) {
                        String[] punchlinesArray = field.split(":");
                        String punchline = punchlinesArray[1].replaceAll("\"", "").trim();
                        listOfJokesPunchlines.add(punchline);
                    }
                }
            }
        }
    }

    private static void showResults(ArrayList<String> listOfJokesSetups, ArrayList<String> listOfJokesPunchlines, int randomJokeSetupAndPunchline ){
        System.out.println(listOfJokesSetups.get(randomJokeSetupAndPunchline));
        System.out.println(listOfJokesPunchlines.get(randomJokeSetupAndPunchline));
    }
}
