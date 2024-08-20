package io.github.px86.springbootbloggingapp.controller;

import io.github.px86.springbootbloggingapp.model.User;
import io.github.px86.springbootbloggingapp.service.UserService;
import jakarta.validation.Valid;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

  @Autowired private UserService userService;
  @Autowired private PasswordEncoder passwordEncoder;

  @GetMapping("/signup")
  public String signup(Model model) {
    User user = new User();
    model.addAttribute("user", user);

    return "signup";
  }

  @PostMapping(path = "/signup", consumes = "application/x-www-form-urlencoded")
  public String createUser(@ModelAttribute @Valid User user, Model model) {
    if (this.userService.existsByUsername(user.getUsername())) {
      // model.addAttribute("alert", "username taken!");
      return "redirect:/signup";

    } else if (this.userService.existsByEmail(user.getEmail())) {

      // model.addAttribute("alert", "another user alreay exists with given email address!");
      return "redirect:/signup";
    }

    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    user.setRoles(Collections.emptySet()); // Fixme

    this.userService.save(user);

    return "redirect:/login";
  }
}
