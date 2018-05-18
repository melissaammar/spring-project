package com.example.springgradle.services;

import com.example.springgradle.dao.PermissionRepository;
import com.example.springgradle.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    private PermissionService() { }

    public List<Permission> getPermissions (String [] permissionId) {
        //Getting the permissions from the DB
        List<Permission> rolePermissions = new ArrayList<>();
        for (String id : permissionId)
            rolePermissions.add(permissionRepository.findPermissionById(Integer.parseInt(id)));//retrieving permissions by id
        return rolePermissions;//returning the list of permissions retrieved
    }
}
