package com.example.springgradle.controllers;

import com.example.springgradle.dao.PermissionRepository;
import com.example.springgradle.dao.RoleRepository;
import com.example.springgradle.dao.UserRepository;
import com.example.springgradle.models.Permission;
import com.example.springgradle.models.Role;
import com.example.springgradle.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseService {

    private static final DataBaseService instance = new DataBaseService();
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    private DataBaseService() {
    }

    public static DataBaseService getInstance() {
        return instance;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> findAllUsers() { return userRepository.findAll(); }

    public User updateUser (User user) { return userRepository.save(user); }

    public void deleteUser (String username) { userRepository.delete(userRepository.findUserByUsername(username)); }

    public List<Role> findAllRoles() { return roleRepository.findAll(); }

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public Role updateRole (Role role) { return roleRepository.save(role); }

    public void deleteRole (String name) { roleRepository.delete(roleRepository.findRoleByName(name)); }

    public Permission findPermissionByName(String name) {
        return permissionRepository.findPermissionByName(name);
    }
}