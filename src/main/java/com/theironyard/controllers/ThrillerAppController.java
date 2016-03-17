package com.theironyard.controllers;

import com.theironyard.entities.Thrill;
import com.theironyard.entities.User;
import com.theironyard.services.ThrillRepository;
import com.theironyard.services.UserRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by drewmunnerlyn on 3/17/16.
 */
@RestController
public class ThrillerAppController {
    @Autowired
    UserRepository users;

    @Autowired
    ThrillRepository thrills;
    Server dbui;

    @PostConstruct
    public void construct() throws SQLException, SQLException {
        dbui = Server.createWebServer().start();
    }

    @PreDestroy
    public void destroy() {
        dbui.stop();
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user, HttpSession session) {
        User existingUser = users.findByName(user.getName());
        if (existingUser == null) {
            existingUser = user;
            users.save(user);
        }
        session.setAttribute("name", existingUser.getName());
        return existingUser;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public User getLogin(HttpSession session) {
        String name = (String) session.getAttribute("name");
        return users.findByName(name);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getUsers() {
        return (List<User>) users.findAll();
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        users.save(user);
    }
    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user, @PathVariable("id") int id) {
        users.save(user);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) {
        users.delete(id);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") int id) {
        return users.findOne(id);
    }
    @RequestMapping(path = "/thrill", method = RequestMethod.POST)
    public void addThrill(HttpSession session, @RequestBody Thrill thrill) {
        String name = (String) session.getAttribute("name");
        User user = users.findByName(name);
        thrill.setUser(user);
        thrills.save(thrill);
    }
    @RequestMapping(path = "/thrill", method = RequestMethod.GET)
    public List<Thrill> getThrills() {
        return (List<Thrill>) thrills.findAll();
    }

    @RequestMapping(path = "/thrill", method = RequestMethod.GET)
    public Thrill getThrill (int id) {
        return thrills.findOne(id);
    }
    @RequestMapping(path = "/thrill", method = RequestMethod.PUT)
    public void editThrill (@RequestBody Thrill thrill) {
        thrills.save(thrill);
    }

}
