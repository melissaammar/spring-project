package com.example.springgradle.controllers;

import com.example.springgradle.models.Role;
import org.springframework.stereotype.Component;
import com.example.springgradle.models.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Melissa.
 */

@RestController
@Component
public class UserController extends BaseController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return dataBaseService.findAllUsers();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(@RequestParam String username) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return dataBaseService.findUserByUsername(username);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody User user, @RequestParam String roleName) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        String [] roles = null;
        System.out.println("roleName==> " + roleName);
        if (roleName != null && roleName.contains(",")) {
            roles = roleName.split(",");
        }
        else {
            roles = new String[] {roleName};
        }
        System.out.println("roles==> " + roles.toString());
        List<Role> userRole = new ArrayList<>();
        for (int i = 0; i <roles.length; i++) {
            userRole.add(dataBaseService.findRoleByName(roles[i]));
        }
        System.out.println("userRole==> " + userRole);
        user.setRoles(userRole);
        Timestamp time = getDateTime();
        System.out.println("time==> " + time);
        user.setCreated_at(time);
        user.setUpdated_at(time);
        return dataBaseService.updateUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User editUser(@RequestBody User user, @RequestParam String username) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        User oldUser = dataBaseService.findUserByUsername(username);
        if (oldUser.getUsername() == null)
            return oldUser;
        else
        {
            Timestamp time = getDateTime();
            System.out.println("time==> " + time);
            user.setUpdated_at(time);
            if (user.getUsername() == null || user.getUsername() == "")
                user.setUsername(oldUser.getUsername());
            if (user.getEmail() == null || user.getEmail() == "")
                user.setEmail(oldUser.getEmail());
            if (user.getName() == null || user.getName() == "")
                user.setName(oldUser.getName());
            if (user.getPassword() == null || user.getPassword() == "")
                user.setPassword(oldUser.getPassword());
            user.setId(oldUser.getId());
            user.setCreated_at(oldUser.getCreated_at());
            user.setRoles(oldUser.getRoles());
            return dataBaseService.updateUser(user);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam String username) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        dataBaseService.deleteUser(username);
        return "done";
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    public User addUserRole(@RequestBody User user) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return new User();
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.DELETE)
    public String deleteUserRole(@RequestBody User user) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        //dataBaseService.createUser(user);
        return "done";
    }
}