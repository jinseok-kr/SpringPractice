package chap11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/member/login")
    public String form() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login() {
        return "hello";
    }
}
