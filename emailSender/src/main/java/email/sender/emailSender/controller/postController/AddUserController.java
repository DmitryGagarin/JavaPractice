package email.sender.emailSender.controller.postController;

import email.sender.emailSender.entity.User;
import email.sender.emailSender.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {
    @Autowired
    public UserRepository userRepository;

    @PostMapping("/add_user")
    public String addUser(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }
}
