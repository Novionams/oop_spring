package com.tts.ecommercepage.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.tts.ecommercepage.models.User;
import com.tts.ecommercepage.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
    class AuthenticationController {
      @Autowired
      private UserService userService;
 
//      @GetMapping("/signin")
//      public String login(@ModelAttribute("user") User user) {
//          return "signin";
//      }
      @GetMapping("/signin")
      public String login(Model model) {
          if (userService.isAuthenticated()) {
              return "redirect:/";
          }
          model.addAttribute("user", new User());
          return "signin";
      }
 
      @PostMapping("/signin")
      public String signup(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           @RequestParam String submit,
                           HttpServletRequest request) throws ServletException {
          if (bindingResult.hasErrors()) {
              return "signin";
          }

          String password = user.getPassword();
          if (submit.equals("up")) {
              if (userService.findByUsername(user.getUsername()) == null) {
                  userService.saveNew(user);
              } else {
                  bindingResult.rejectValue("username", "error.user", "Username is already taken.");
                  return "signin";
              }
          }
          request.login(user.getUsername(), password);
          return "redirect:/";
      }
    }

