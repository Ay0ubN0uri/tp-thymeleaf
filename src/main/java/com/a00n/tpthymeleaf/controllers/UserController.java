package com.a00n.tpthymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a00n.tpthymeleaf.entities.User;
import com.a00n.tpthymeleaf.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(@RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message, Model model) {
        if (status != null) {
            model.addAttribute("status", status);
        }
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/users/create")
    public String create(@Valid User user, BindingResult result, Model model, HttpSession session) {
        System.out.println(user);
        if (result.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("user", user);
            System.out.println(result);
            return "index";
        }
        userRepository.save(user);
        var status = "success";
        var message = "User created successfully";
        return "redirect:/?status=" + status + "&message=" + message;
    }

    @PostMapping("/users/update")
    public String update(@Valid User user) {
        System.out.println(user);
        userRepository.save(user);
        var status = "success";
        var message = "User updated successfully";
        return "redirect:/?status=" + status + "&message=" + message;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        System.out.println(id);
        try {
            userRepository.deleteById(id);
            model.addAttribute("status", "success");
            model.addAttribute("message", "User with ID " + id + " deleted");
        } catch (Exception e) {
            model.addAttribute("status", "danger");
            model.addAttribute("message", "User with ID " + id + " not found");
            e.printStackTrace();
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User());
        return "index";
    }

}
