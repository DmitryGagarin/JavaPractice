import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private static HttpsURLConnection connection;

    public static void main (String[] args) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        String endPartOfLink = "";

        System.out.println("Choose genre");
        System.out.println("1) - animation, 2) - classic");

        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        switch (decision){
            case 1 -> endPartOfLink = "animation";
            case 2 -> endPartOfLink = "classic";
            default -> System.out.println("something went wrong");
        }

        String link = "https://api.sampleapis.com/movies/" + endPartOfLink;

        try {
            URL url = new URL(link);
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("Connection status is: " + status);

            if (status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while((line = reader.readLine()) != null ){
                responseContent.append(line);
            }

            for (int i = 0; i < responseContent.length(); i++){
                char currentChar = responseContent.charAt(i);
                if (currentChar == '}' || currentChar == '{' || currentChar == '[' || currentChar == ']'){
                    responseContent.setCharAt(i, '\n');
                }
            }
            reader.close();

            System.out.println(responseContent);

            if (decision == 1) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("animation.text"));
                writer.write(String.valueOf(responseContent));
                writer.close();
            }
            if (decision == 2){
                BufferedWriter writer = new BufferedWriter(new FileWriter("classic.text"));
                writer.write(String.valueOf(responseContent));
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }
}
