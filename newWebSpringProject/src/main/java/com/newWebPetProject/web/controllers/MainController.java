package com.newWebPetProject.web.controllers;

import com.newWebPetProject.web.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    private final UserService userService;

    private UserRegService userRegService;

    @Autowired
    public MainController(UserService userService, UserRegServiceImpl userRegService) {
        this.userService = userService;
        this.userRegService = userRegService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title","home");
        return "home";
    }

    @GetMapping("/data")
    public String data(Model model) {
        model.addAttribute("title", "user data");
        return "data";
    }

    @GetMapping("/data/{userId}")
    public String userData(@PathVariable("userId") int userId, Model model) {
        Users user;
        String message;
        String title;
        switch (userId) {
            case 1:
                user = userService.firstUser();
                message = "Data about first user";
                title = "First user data";
                break;
            case 2:
                user = userService.secondUser();
                message = "Data about second user";
                title = "Second user data";
                break;
            case 3:
                user = userService.thirdUser();
                message = "Data about third user";
                title = "Third user data";
                break;
            default:
                return "redirect:/";
        }
        model.addAttribute("title", title);
        model.addAttribute("message", message);
        model.addAttribute("id", user.getId());
        model.addAttribute("first_name", user.getFirstName());
        model.addAttribute("second_name", user.getSecondName());
        model.addAttribute("gender", user.getGender());
        model.addAttribute("status", user.getStatus());
        model.addAttribute("username", user.getUsername());
        return "user_data";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("title", "registration");
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userRegService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/registration";
        }

        userRegService.saveUser(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/users")
    private String users(Model model) {
        List<UserDto> users = userRegService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
