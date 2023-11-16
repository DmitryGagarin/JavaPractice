import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private static HttpsURLConnection connection;

    public static void main(String[] args) {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        String genre = "";

        System.out.println("Choose genre");
        System.out.println("1) - animation, 2) - classic, 3) - comedy, 4) - drama, 5) - horror");

        Scanner scanner = new Scanner(System.in);

        genre = getGenreAndValidateIt(genre, scanner);

        String link = "https://api.sampleapis.com/movies/" + genre;

        try {
            URL url = new URL(link);
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("Connection status is: " + status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            for (int i = 0; i < responseContent.length(); i++) {
                char currentChar = responseContent.charAt(i);
                if (currentChar == '}' || currentChar == '{' || currentChar == '[' || currentChar == ']') {
                    responseContent.setCharAt(i, '\n');
                }
            }

            getTitle(responseContent);

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(genre + ".text"));
            writer.write(String.valueOf(responseContent));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    private static String getGenreAndValidateIt(String genre, Scanner scanner) {
        int decision;
        do {
            if (scanner.hasNextInt()) {
                decision = scanner.nextInt();
                if (decision >= 1 && decision <= 5) {
                    switch (decision) {
                        case 1 -> genre = "animation";
                        case 2 -> genre = "classic";
                        case 3 -> genre = "comedy";
                        case 4 -> genre = "drama";
                        case 5 -> genre = "horror";
                        default -> System.out.println("Please enter a number between 1 and 5");
                    }
                } else {
                    System.out.println("Be sure that you input number from 1 to 5");
                }
            } else {
                System.out.println("Be sure that you input int number");
                scanner.next();
                decision = 0;
            }
        } while (decision < 1 || decision > 5);
        return genre;
    }

    private static void getTitle(StringBuilder responseContent) {
        String[] parts = responseContent.toString().split("\\{");
        for (String part : parts) {
            if (part.contains("title")) {
                String[] fields = part.split(",");
                for (String field : fields) {
                    if (field.contains("title")) {
                        String[] titleArray = field.split(":");
                        String title = titleArray[1].replaceAll("\"", "").trim();
                        System.out.println("Title is: " + title);
                    }
                }
            }
        }
    }
}
