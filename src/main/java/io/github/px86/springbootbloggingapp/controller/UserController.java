package io.github.px86.springbootbloggingapp.controller;

import io.github.px86.springbootbloggingapp.model.User;
import io.github.px86.springbootbloggingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/user/me")
  public String userDetails(Model model) {
    UserDetails userDetails =
        (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    User user = this.userService.findByUsername(userDetails.getUsername()).get();
    model.addAttribute("user", user);

    return "user";
  }
}
