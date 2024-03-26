package bot.bot.config;

import bot.bot.model.Joke;
import bot.bot.model.Repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    JokeRepository jokeRepository;


    @Override
    public void run(String... args) throws Exception {
        jokeRepository.saveAll(Arrays.asList(
                new Joke(1L, "joke 1", "topic 1"),
                new Joke(2L, "joke 2", "topic 2"))
        );
    }
}
