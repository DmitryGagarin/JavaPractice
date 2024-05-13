package email.sender.emailSender.controller;

import email.sender.emailSender.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final MailSenderService mailSenderService;

    @PostMapping("/send_form_process")
    public String sendMessage(
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text) {
        mailSenderService.sendNewEmail(email, subject, text);
        return "redirect:/home";
    }
}
