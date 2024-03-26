package bot.bot.model.Repository;

import bot.bot.model.Joke;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JokeRepository extends CrudRepository<Joke, Long> {
}
