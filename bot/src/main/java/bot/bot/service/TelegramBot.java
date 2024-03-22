package bot.bot.service;

import bot.bot.config.BotConfig;
import bot.bot.model.User;
import bot.bot.model.UserRepository;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    UserRepository userRepository;

    final BotConfig config;

    static final String HELP_TEXT = "I'm here to help you!\n " +
            "Push menu button to see all possible commands";

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "start the bot"));
        listOfCommands.add(new BotCommand("/end", "close the bot"));
        listOfCommands.add(new BotCommand("/mydata", "check my data"));
        listOfCommands.add(new BotCommand("/deletedata", "delete my data"));
        listOfCommands.add(new BotCommand("/help", "i need help!"));
        listOfCommands.add(new BotCommand("/settings", "settings"));
        listOfCommands.add(new BotCommand("/register", "registration"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage() + "error setting commands in menu");
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        boolean checkOfTextAvailability = update.hasMessage() && update.getMessage().hasText();
        if (checkOfTextAvailability) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" -> {
                    registerUser(update.getMessage());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                }
                case "/end" -> endCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                case "/mydata" -> myDataCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                case "/deletedata" -> deleteDataCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                case "/help" -> sendMessage(chatId, HELP_TEXT);
                case "/settings" -> settingsCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                case "/register" -> register(chatId);
                default -> sendMessage(chatId, "Command not found");
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            if (callbackData.equals("YES_BUTTON")) {
                registrationYesCallback(chatId, messageId);
            }
            else if (callbackData.equals("NO_BUTTON")) {
                registrationNoCallback(chatId, messageId);
            }
        }
    }

    private void registrationNoCallback(long chatId, long messageId) {
        String text = "You pressed 'no' button!";
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(String.valueOf(chatId));
        editMessageText.setText(text);
        editMessageText.setMessageId((int) messageId);
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registrationYesCallback(long chatId, long messageId) {
        String text = "You pressed 'yes' button!";
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(String.valueOf(chatId));
        editMessageText.setText(text);
        editMessageText.setMessageId((int) messageId);
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerUser(Message message) {
        if (userRepository.findById(message.getChatId()).isEmpty()) {
            Long chatId = message.getChatId();
            var chat = message.getChat();
            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUsername(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
        }
    }

    private void register(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Do you want to register?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = getLists();

        inlineKeyboardMarkup.setKeyboard(rowsInline);

        message.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<List<InlineKeyboardButton>> getLists() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton yesButton = new InlineKeyboardButton();
        yesButton.setText("yes!");
        yesButton.setCallbackData("YES_BUTTON");

        InlineKeyboardButton noButton = new InlineKeyboardButton();
        noButton.setText("no!");
        noButton.setCallbackData("NO_BUTTON");

        rowInline.add(yesButton);
        rowInline.add(noButton);

        rowsInline.add(rowInline);
        return rowsInline;
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        ReplyKeyboardMarkup keyboardMarkup = getReplyKeyboardMarkup();

        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println(("Error occurred: " + e.getMessage()));
        }
    }

    private static ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("weather");
        row.add("get random joke");

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("register");
        row.add("check my data");
        row.add("delete my data");
        row.add("/start");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }

    public void startCommandReceived(long chatId, String firstName) {
        String response = EmojiParser.parseToUnicode("Hi, " + firstName + " , your chatId is: " + chatId + ":fire:");
        System.out.println("replied to the user with the start command! " + firstName);
        sendMessage(chatId, response);
    }

    public void endCommandReceived(long chatId, String firstName) {
        String response = "Okay, " + firstName + " goodbye. Your chat id was: " + chatId;
        sendMessage(chatId, response);
    }

    public void myDataCommandReceived(long chatId, String firstName) {
        String response = firstName + " we have your data";
        sendMessage(chatId, response);

    }

    public void deleteDataCommandReceived(long chatId, String firstName) {
        String response = firstName + " now you are able to delete certain personal data";
        sendMessage(chatId, response);
    }

    public void settingsCommandReceived(long chatId, String firstName) {
        String response = firstName + " now, you are in settings";
        sendMessage(chatId, response);
    }
}
