package razilov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import razilov.models.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class UserController {
    private final List<User> users = new CopyOnWriteArrayList<>();

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showItems(ModelMap map) {
        map.addAttribute("users");
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addItem(@ModelAttribute User user) {
        users.add(user);
        return "redirect:users.do";
    }
}
