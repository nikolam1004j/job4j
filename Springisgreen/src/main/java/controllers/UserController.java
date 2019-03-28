package controllers;

import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class UserController {

    private final List<User> users = new CopyOnWriteArrayList<>();

    @RequestMapping(name = "/users", method = RequestMethod.GET)
    public String showItems(ModelMap map) {
        map.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(name = "/users", method = RequestMethod.POST)
    public String addItem(@ModelAttribute User user) {
        return "redirect:users.do";
    }
}