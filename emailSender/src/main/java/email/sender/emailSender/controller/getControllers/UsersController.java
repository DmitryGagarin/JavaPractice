package email.sender.emailSender.controller.getControllers;

import email.sender.emailSender.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @GetMapping("/users")
    public String users(Model model, HttpServletRequest request) {
        model.addAttribute("title","users page");
        model.addAttribute("user", new User());

        String currentUrl = request.getRequestURL().toString();
        String cssClassActive = currentUrl.equals("http://localhost:8080/users") ? "active" : "";

        model.addAttribute("active_status_users", cssClassActive);
        return "users";
    }
}
