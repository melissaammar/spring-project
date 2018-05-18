package com.example.springgradle.controllers;

import com.example.springgradle.dto.request.user.UserCreateRequest;
import com.example.springgradle.dto.request.user.UserEditRequest;
import com.example.springgradle.dto.response.user.GetUserResponse;
import com.example.springgradle.dto.response.user.UserCreateResponse;
import com.example.springgradle.dto.response.user.UserEditResponse;
import com.example.springgradle.dto.response.user.UserResponse;
import com.example.springgradle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.springgradle.models.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Melissa.
 * This class is created in order to handle the User CRUD operations
 */

@RestController
@Component
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public GetUserResponse getUser(@RequestParam int id) {
        //This API returns a specific user by its ID
        try {
            User user = userService.getUserById(id);
            if (user == null)
                return new GetUserResponse("-1", "Unable to find user.");//User not found, returning error
            return new GetUserResponse("1", "Successful Operation.", user);//User found and returned
        }
        catch (Exception e){
            return new GetUserResponse("-1", "Error: " + e);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest, @RequestParam String roles) {
        /* create a new user and assign the roles mentioned in "roles"
         * which can be one or several roles comma separated
        */
        if (userCreateRequest == null || roles == null)
            return new UserCreateResponse("-1","Please check the structure of your request.");//one of the parameters missing
        try {
            UserResponse user = userService.createUser(userCreateRequest, roles);//creating the user roles and saving in DB
            if (user == null)
                return new UserCreateResponse("-1", "Could not create the user.");//an error occurred while creating the user
            return new UserCreateResponse("1", "User successfully created.", user);//user successfully created
        }
        catch (Exception e) {
            return new UserCreateResponse ("-1", "Error: " + e);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public UserEditResponse editUser(@RequestBody UserEditRequest userEditRequest) {
        //API for editing a specific user
        if (userEditRequest == null)
            return new UserEditResponse("-1","Please check the structure of your request.");//Missing payload
        try {
            UserResponse user = userService.editUser(userEditRequest);//getting the user from DB and editing it
            if (user == null)
                return new UserEditResponse("-1", "Could not edit user.");
            return new UserEditResponse("1", "User successfully edited.", user);
        }
        catch (Exception e) {
            return new UserEditResponse("-1", "Error: " + e);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public UserEditResponse deleteUser(@RequestParam int id) {
        //API for deleting users
        try {
            String response = userService.deleteUser(id);//calling the delete method in the user service
            if (response == null)
                return new UserEditResponse("-1", "Could not delete user.");
            return new UserEditResponse("1", "User successfully deleted.");
        }
        catch (Exception e) {
            return new UserEditResponse("-1", "Error: " + e);
        }
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    public UserEditResponse addUserRole(@RequestParam int id, String roleIds) {
        //adding new roles to the user
        if (id <= 0 || roleIds == null)
            return new UserEditResponse("-1","Please check the structure of your request.");
        try {
            UserResponse user = userService.editUserRole(id, roleIds, "addRole");//editing user roles and adding new roles to the user
            if (user == null)
                return new UserEditResponse("-1", "Could not add roles.");
            return new UserEditResponse("1", "Roles successfully added.", user);
        }
        catch (Exception e) {
            return  new UserEditResponse("-1", "Error: " + e);
        }
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.DELETE)
    public UserEditResponse deleteUserRole(@RequestParam int id, String roleIds) {
        //deleting existing roles from the user
        if (id <= 0 || roleIds == null)
            return new UserEditResponse("-1","Please check the structure of your request.");
        try {
            UserResponse user = userService.editUserRole(id, roleIds, "deleteRole");//editing user roles and deleting mentioned roles from the user
            if (user == null)
                return new UserEditResponse("-1", "Could not delete roles.");
            return new UserEditResponse("1", "Roles successfully deleted.", user);
        }
        catch (Exception e) {
            return new UserEditResponse("-1", "Error: " + e);
        }
    }
}