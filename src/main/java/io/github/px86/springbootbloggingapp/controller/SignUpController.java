package io.github.px86.springbootbloggingapp.controller;

import io.github.px86.springbootbloggingapp.model.User;
import io.github.px86.springbootbloggingapp.service.UserSignUpService;
import io.github.px86.springbootbloggingapp.service.exception.EmailAlreadyRegisteredException;
import io.github.px86.springbootbloggingapp.service.exception.UsernameNotAvailableException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

  @Autowired private UserSignUpService userSignUpService;

  @GetMapping("/signup")
  public String signup(Model model) {
    User user = new User();
    model.addAttribute("user", user);

    return "signup";
  }

  @PostMapping(path = "/signup", consumes = "application/x-www-form-urlencoded")
  public ResponseEntity<String> createUser(@ModelAttribute @Valid User user) {
    try {
      this.userSignUpService.registerNewUser(user);

      HttpHeaders headers = new HttpHeaders();
      headers.set("HX-Retarget", ".wrapper");

      String html =
          """
          <h2>Email verification pending!</h2>
          <p>Please check you inbox and verify your email to complete the registration process.</p>
          <p>Thank You!</p>
          """;

      return new ResponseEntity<String>(html, headers, HttpStatus.CREATED);

    } catch (UsernameNotAvailableException | EmailAlreadyRegisteredException e) {
      return new ResponseEntity<String>(e.getMessage(), null, HttpStatus.OK);
    }
  }
}
