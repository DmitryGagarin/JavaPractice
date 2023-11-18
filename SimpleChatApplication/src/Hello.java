import java.util.Random;

public class Hello extends Utils {

    public static Random random;

    protected static void sayHello() {

        String[] greetings = new String[3];
        greetings[0] = """
                ╔┓┏╦━━╦┓╔┓╔━━╗
                ║┗┛║┗━╣┃║┃║╯╰║
                ║┏┓║┏━╣┗╣┗╣╰╯║
                ╚┛┗╩━━╩━╩━╩━━╝
                """;

        greetings[1] = """
                 H   H  EEEEE  L      L       OOO
                 H   H  E      L      L      O   O
                 HHHHH  EEEE   L      L      O   O
                 H   H  E      L      L      O   O
                 H   H  EEEEE  LLLLL  LLLLL   OOO
                """;

        greetings[2] = """
                hhhhhhh                                lllllll lllllll                 \s
                h:::::h                                l:::::l l:::::l                 \s
                h:::::h                                l:::::l l:::::l                 \s
                h:::::h                                l:::::l l:::::l                 \s
                h::::h hhhhh           eeeeeeeeeeee    l::::l  l::::l    ooooooooooo  \s
                h::::hh:::::hhh      ee::::::::::::ee  l::::l  l::::l  oo:::::::::::oo\s
                h::::::::::::::hh   e::::::eeeee:::::eel::::l  l::::l o:::::::::::::::o
                h:::::::hhh::::::h e::::::e     e:::::el::::l  l::::l o:::::ooooo:::::o
                h::::::h   h::::::he:::::::eeeee::::::el::::l  l::::l o::::o     o::::o
                h:::::h     h:::::he:::::::::::::::::e l::::l  l::::l o::::o     o::::o
                h:::::h     h:::::he::::::eeeeeeeeeee  l::::l  l::::l o::::o     o::::o
                h:::::h     h:::::he:::::::e           l::::l  l::::l o::::o     o::::o
                h:::::h     h:::::he::::::::e         l::::::ll::::::lo:::::ooooo:::::o
                h:::::h     h:::::h e::::::::eeeeeeee l::::::ll::::::lo:::::::::::::::o
                h:::::h     h:::::h  ee:::::::::::::e l::::::ll::::::l oo:::::::::::oo\s
                hhhhhhh     hhhhhhh    eeeeeeeeeeeeee llllllllllllllll   ooooooooooo\s
                  """;

        random = new Random();
        int randomGreeting = random.nextInt(greetings.length);

        System.out.println(greetings[randomGreeting]);
    }
}
