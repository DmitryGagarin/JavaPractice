package registration.example.reg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import registration.example.reg.model.entity.Message;
import registration.example.reg.model.entity.User;
import registration.example.reg.model.repository.MessageRepository;
import registration.example.reg.model.repository.UserRepository;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String homeView(Model model) {
        model.addAttribute("title", "Home Page");
        return "home";
    }

    @GetMapping("/registration")
    public String registrationView(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("process_registration")
    public String processRegistration(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/message")
    public String messageForm(Model model) {
        model.addAttribute("title", "Message");
        model.addAttribute("message", new Message());
        return "message";
    }

    @PostMapping("/process_message")
    public String sendMessage(Message message) {
        messageRepository.save(message);
        return "redirect:/users";
    }
}
