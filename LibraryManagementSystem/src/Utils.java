import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    protected static int changeDecision = 0;

    protected static Scanner scanner = new Scanner(System.in);

    protected static void showVariants() {
        System.out.println(ANSI_GREEN + "-----------------------");
        System.out.println("add");
        System.out.println("delete");
        System.out.println("count");
        System.out.println("change");
        System.out.println("read all");
        System.out.println("get titles");
        System.out.println("get authors");
        System.out.println("get dates");
        System.out.println("variants");
        System.out.println("exit");
        System.out.println("-----------------------" + ANSI_RESET);
    }

    protected static int countLines(String path) {
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader((path)));
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ANSI_GREEN + "-----------------------");
        System.out.println("There are: " + lines + " objects in your library");
        System.out.println("-----------------------" + ANSI_RESET);
        return lines;
    }

    protected static void getParts(String path, String decision) {
        String filter;

        try {
            StringBuilder result = new StringBuilder();
            List<String> db = Files.readAllLines(Paths.get(path));
            for (String line : db) {
                result.append(line);
            }

            switch (decision) {
                case "get titles" -> {
                    filter = "Title";
                    List<String> titles = Utils.extractTitles(String.valueOf(result), filter);
                    System.out.println(ANSI_YELLOW + "-----------------------");
                    for (String title : titles) {
                        System.out.println(title);
                    }
                    System.out.println("-----------------------" + ANSI_RESET);

                }
                case "get authors" -> {
                    filter = "Author";
                    List<String> authors = Utils.extractTitles(String.valueOf(result), filter);
                    System.out.println(ANSI_YELLOW + "-----------------------");
                    for (String author : authors) {
                        System.out.println(author);
                    }
                    System.out.println("-----------------------" + ANSI_RESET);

                }
                case "get dates" -> {
                    filter = "Publishing year";
                    List<String> dates = Utils.extractTitles(String.valueOf(result), filter);
                    System.out.println(ANSI_YELLOW + "-----------------------");
                    for (String date : dates) {
                        System.out.println(date);
                    }
                    System.out.println("-----------------------" + ANSI_RESET);

                }
                default -> System.err.println("Something went wrong");
            }
        } catch (IOException e) {
            System.err.println("Wrong file path");
            e.printStackTrace();
        }
    }

    private static List<String> extractTitles(String result, String filter) {

        List<String> titles = new ArrayList<>();
        List<String> authors = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        Pattern pattern = Pattern.compile(filter + ": ([^,]+)");

        Matcher matcher = pattern.matcher(result);

        if (filter.equals("Title")) {
            while (matcher.find()) {
                titles.add(matcher.group(1));
            }
            return titles;
        }

        if (filter.equals("Author")) {
            while (matcher.find()) {
                authors.add(matcher.group(1));
            }
            return authors;
        }

        if (filter.equals("Publishing year")) {
            while (matcher.find()) {
                dates.add(matcher.group(1));
            }
        }
        return dates;
    }

    protected static void changeBooksInfo(String path) {
        System.out.println("What you want to change?");
        showChangeVariants();
        System.out.println(changeDecision);
        switch (changeDecision) {
            case 1 -> Library.changeBookTitle(path);
            case 2 -> Library.changeBookAuthor(path);
            case 3 -> Library.changeBookPublishingYear(path);
        }
    }

    private static void showChangeVariants() {
        System.out.println("1) - change book's title");
        System.out.println("2) - change book's author");
        System.out.println("3) - change book's publishing year");

        changeDecision = scanner.nextInt();
    }

    protected static void exit() {
    }
}
