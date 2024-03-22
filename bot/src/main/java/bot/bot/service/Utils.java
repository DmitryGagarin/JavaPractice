//package bot.bot.service;
//
//import com.vdurmont.emoji.EmojiParser;
//import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Utils {
//
//    public static InlineKeyboardMarkup inlineKeyboardMarkup;
//    public static ReplyKeyboardMarkup keyboardMarkup;
//    public static List<BotCommand> listOfCommands;
//    protected static List<BotCommand> CommandsInMenu() {
//        List<BotCommand> listOfCommands = new ArrayList<>();
//        listOfCommands.add(new BotCommand("/start", "start the bot"));
//        listOfCommands.add(new BotCommand("/end", "close the bot"));
//        listOfCommands.add(new BotCommand("/mydata", "check my data"));
//        listOfCommands.add(new BotCommand("/deletedata", "delete my data"));
//        listOfCommands.add(new BotCommand("/help", "i need help!"));
//        listOfCommands.add(new BotCommand("/settings", "settings"));
//        listOfCommands.add(new BotCommand("/register", "registration"));
//        return listOfCommands;
//    }
//
//    public static void createInlineKeyboardMarkup() {
//        inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//        InlineKeyboardButton yesButton = new InlineKeyboardButton();
//        yesButton.setText("yes!");
//        yesButton.setCallbackData("YES_BUTTON");
//
//        InlineKeyboardButton noButton = new InlineKeyboardButton();
//        noButton.setText("no!");
//        noButton.setCallbackData("NO_BUTTON");
//
//        rowInline.add(yesButton);
//        rowInline.add(noButton);
//
//        rowsInline.add(rowInline);
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//    }
//
//    public static void createReplyKeyboardMarkup() {
//        keyboardMarkup = new ReplyKeyboardMarkup();
//        keyboardMarkup.setResizeKeyboard(true);
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//        row.add("weather");
//        row.add("get random joke");
//
//        keyboardRows.add(row);
//
//        row = new KeyboardRow();
//        row.add("register");
//        row.add("check my data");
//        row.add("delete my data");
//        row.add("/start");
//
//        keyboardRows.add(row);
//
//        keyboardMarkup.setKeyboard(keyboardRows);
//    }
//}
