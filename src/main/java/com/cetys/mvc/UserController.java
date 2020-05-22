package com.cetys.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on May, 2020
 */
@Controller
public class UserController {
    // "Model" Layer
    List<User> users = new ArrayList<>();
    {
        users.add(new User(1, "Edwin", "edwn@gmail.com", 30));
        users.add(new User(2, "Dania", "dania@gmail.com", 27));
        users.add(new User(3, "Daniel", "daniel@gmail.com", 23));
    }
    // end "Model Layer

    @GetMapping("/")
    String listUsers(Model model){
        model.addAttribute("users", users);
        return "users-list";
    }

    @GetMapping("/register")
    String registration(User user, Model model){
        var id = users.size() + 1;
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "user-add";
    }

    @PostMapping("/add")
    String addUser(
            User user,
            Model model
    ) {
        users.add(user);
        model.addAttribute("users", users);
        return "users-list";
    }

    @GetMapping("/edit/{id}")
    String editUser(
            @PathVariable("id") int id,
            Model model
    ) {
        var user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .get();
        model.addAttribute("user", user);

        return "user-edit";
    }

    @PostMapping("/update/{id}")
    String updateUser(
            @PathVariable("id") int id,
            User editedUser,
            Model model
    ) {
        var user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .get();
        user.setName(editedUser.getName());
        user.setEmail(editedUser.getEmail());
        user.setAge(editedUser.getAge());

        model.addAttribute("users", users);
        return "users-list";
    }

    @GetMapping("/delete/{id}")
    String deleteUser(
            @PathVariable("id") int id,
            Model model
    ) {
        var user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .get();
        users.remove(user);

        model.addAttribute("users", users);
        return "users-list";
    }
}
