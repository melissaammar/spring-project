package com.example.springgradle.services;

import com.example.springgradle.dao.UserRepository;
import com.example.springgradle.dto.request.user.UserCreateRequest;
import com.example.springgradle.dto.request.user.UserEditRequest;
import com.example.springgradle.dto.response.user.UserResponse;
import com.example.springgradle.models.Role;
import com.example.springgradle.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/*   Added by Melissa
 *   This class is used to handle all the operations related to the user
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    private UserService() { }

     public User getUserById (int id) {
        //Getting the user from the DB
        User user = userRepository.findUserById(id);
         return user;
     }

     public UserResponse createUser (UserCreateRequest userCreateRequest, String roles) {
         String [] roleId = roles.contains(",") ? roles.split(",") : new String[] {roles};//checking if we have one or several roles
         List<Role> userRole = roleService.getRoles(roleId);//getting roles from DB
         User user = new User(userCreateRequest);
         user.setRoles(userRole);//creating the user and adding its roles
         return new UserResponse(userRepository.save(user));//returning the User response to the controller
     }

     public UserResponse editUser (UserEditRequest userEditRequest) {
        User newUser = new User (userEditRequest);
        User currentUser = userRepository.findUserById(newUser.getId());//find user from DB by id
        if (currentUser == null)//this means that user does not exist==> should return error
            return null;
        //checking if any of the newUser fields is missing and replacing it with the current value
        if (newUser.getUsername() == null || newUser.getUsername().equals(""))
            newUser.setUsername(currentUser.getUsername());
        if (newUser.getEmail() == null || newUser.getEmail().equals(""))
            newUser.setEmail(currentUser.getEmail());
        if (newUser.getName() == null || newUser.getName().equals(""))
            newUser.setName(currentUser.getName());
        if (newUser.getPassword() == null || newUser.getPassword().equals(""))
            newUser.setPassword(currentUser.getPassword());
         newUser.setRoles(currentUser.getRoles());//setting the role for the edited user because we can not change the role in this API
        return new UserResponse(userRepository.save(newUser));
     }

     public String deleteUser (int id) {
        User user = userRepository.findUserById(id);//retrieving the user from the DB
         if (user == null)//this means that user does not exist==> should return error
             return null;
         user.setRoles(new ArrayList<>());//resetting the user's roles
         user = userRepository.save(user);//saving the user with no roles to delete its roles in the DB
         if (user == null)//this means that an error occurred==> should return error
             return null;
         userRepository.delete(user);//deleting the user from the DB
         return "success";
     }

     public UserResponse editUserRole (int id, String roleIds, String action) {
        User user = userRepository.findUserById(id);//getting user from DB to get the roles
         if (user == null)
             return null;
         String [] roles = roleIds.contains(",") ? roleIds.split(",") : new String[] {roleIds};//checking if we have one or several roles
         List<Role> userRoles = user.getRoles();
         List<Role> newRoles = roleService.getRoles(roles);//getting new roles from the DB
         if (newRoles == null)
             return null;//no new roles retrieved from DB
         for (Role role: newRoles)//looping over the retrieved roles and comparing them with the current ones
             //if we're adding a role if it does not exist in the current ones we'll add it
             if (action.equalsIgnoreCase("addRole")) {
                 if (!userRoles.contains(role))
                     userRoles.add(role);
             }//if we're deleting a role if it does exist in the current ones we'll delete it
             else {
                 if (userRoles.contains(role))
                     userRoles.remove(role);
             }
         user.setRoles(userRoles);//adding the retrieved new roles
         return new UserResponse(userRepository.save(user));//updating the user with its new roles in the DB
     }

     public List<User> getUserByRole (Role role) {
        return userRepository.findUserByRoles(role);//fetching a user by his roles to check if this role has users
     }
}