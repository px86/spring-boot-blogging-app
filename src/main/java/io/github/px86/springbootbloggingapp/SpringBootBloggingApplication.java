package io.github.px86.springbootbloggingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SpringBootBloggingApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootBloggingApplication.class, args);
  }

  @GetMapping("/")
  public String homePage(Model model) {
    return "home";
  }

  @GetMapping("/hello")
  public String helloPage(Model model) {
    return "hello";
  }

  @GetMapping("/login")
  public String loginPage(Model model) {
    return "login";
  }
}
