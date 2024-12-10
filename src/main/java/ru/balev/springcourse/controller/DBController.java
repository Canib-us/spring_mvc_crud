

package ru.balev.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.balev.springcourse.model.User;
import ru.balev.springcourse.service.DBService;

import java.util.List;

@Controller
@RequestMapping("/db")
public class DBController {


    @Qualifier("serviceImpl")
    @Autowired
    DBService dbService;

    @GetMapping("/hello")
    public String hello() {
        return "db/hello";
    }

    @GetMapping("/listOfUsers")
    public String listOfUsersPage(Model model) {
        List<User> users = dbService.getUsers();
        model.addAttribute("users", users);
        return "db/listOfUsers";
    }

    @GetMapping("/addUser")
    public String addUserPage(@RequestParam("name") String name,
                              @RequestParam("surname") String surname,
                              @RequestParam("email") String email,
                              @RequestParam("city") String city,
                              Model model) {
        User user = new User(name, surname, email, city);
        dbService.addUser(user);
        model.addAttribute("user", user);
        return "db/addUser";
    }

    @GetMapping("/getUser")
    public String getUserByIdPage(@RequestParam("id") Long id, Model model) {
        User user = dbService.getUser(id);
        model.addAttribute("user", user);
        return "db/getUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUserPage(@RequestParam("id") Long id, Model model) {
        dbService.deleteUser(id);
        return "redirect:/db/listOfUsers";
    }

    @GetMapping("/updateUser")
    public String updateUserPage(@RequestParam("id") Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("email") String email,
                                 @RequestParam("city") String city,
                                 Model model) {
        User user = dbService.getUser(id);
        if (user != null) {
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setCity(city);
            dbService.updateUser(user);
            model.addAttribute("user", user);
            return "db/updateUser";
        } else {
            model.addAttribute("error", "Пользователь не найден");
            return "db/error";
        }
    }
}




