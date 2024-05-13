package email.sender.emailSender.controller.getControllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SendEmailController {
    @GetMapping("/send_form")
    public String send_form(Model model, HttpServletRequest request) {
        model.addAttribute("title","send email form");

        String currentUrl = request.getRequestURL().toString();
        String cssClassActive = currentUrl.equals("http://localhost:8080/send_form") ? "active" : "";

        model.addAttribute("active_status_email_form", cssClassActive);
        return "send_form";
    }
}
