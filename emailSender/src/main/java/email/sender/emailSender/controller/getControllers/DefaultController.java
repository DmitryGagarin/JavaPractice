package email.sender.emailSender.controller.getControllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/"})
public class DefaultController {
    @GetMapping("/")
    public String defaultRedirect() {
        return "redirect:/home";
    }
}
