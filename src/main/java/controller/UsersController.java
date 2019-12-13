package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import data.*;
import java.util.Optional;
import model.*;

@Controller
public class UsersController {
    @Autowired
    private UserRepo urepo;
    
    @PostMapping("/user")
    public String saveUser(@ModelAttribute User usr, Model model){
        urepo.save(usr);
        return "index";
    }
    @GetMapping("/user/{id}")
    public String getUserId(@PathVariable Integer id, Model model)
    {
        Optional<User> usr=urepo.findById(id);
        model.addAttribute("user",usr);
        return "/user/delete";
    }
    @GetMapping("/users")
    public String getUsers(Model model)
    {
        Iterable<User> users=urepo.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @PostMapping("/user/update/{id}")
    public String updateUser(User usr, Model model)
    {
        urepo.save(usr);
        return "redirect:/users";
    }
    @DeleteMapping("user/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Model model)
    {
        urepo.deleteById(id);
        return "users";
    }
}
