package email.sender.emailSender.controller.getControllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("title","home page");

        String currentUrl = request.getRequestURL().toString();
        String cssClassActive = currentUrl.equals("http://localhost:8080/home") ? "active" : "";

        model.addAttribute("active_status_home", cssClassActive);
        return "home";
    }
}
