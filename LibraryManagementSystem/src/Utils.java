import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    protected static void showVariants() {
        System.out.println("-----------------------");
        System.out.println("add");
        System.out.println("delete");
        System.out.println("read all");
        System.out.println("get titles");
        System.out.println("get authors");
        System.out.println("get dates");
        System.out.println("exit");
        System.out.println("-----------------------");
    }

    protected static List<String> extractTitles(String result, String filter) {

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

        if (filter.equals("Publishing year")){
            while (matcher.find()) {
                dates.add(matcher.group(1));
            }
        }
        return dates;
    }

    protected static void exit() {
    }

}
