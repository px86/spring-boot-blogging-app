package io.github.px86.springbootbloggingapp.controller;

import io.github.px86.springbootbloggingapp.model.User;
import io.github.px86.springbootbloggingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/user/me")
  public String userDetails(Authentication authentication, Model model) {

    if (authentication == null) {
      model.addAttribute("errorMessage", "authentication object is null");
      return "error";
    }

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    User user =
        this.userService
            .findByUsername(userDetails.getUsername())
            .orElseThrow(RuntimeException::new);
    model.addAttribute("user", user);
    return "user";
  }
}
