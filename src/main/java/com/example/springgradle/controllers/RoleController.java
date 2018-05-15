package com.example.springgradle.controllers;

import com.example.springgradle.models.Permission;
import com.example.springgradle.models.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
public class RoleController extends BaseController {

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRoles() throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return dataBaseService.findAllRoles();
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Role getRole(@RequestParam String name) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return dataBaseService.findRoleByName(name);
    }
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Role createRole(@RequestBody Role role,  @RequestParam String permissionName) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        String [] permissions = null;
        System.out.println("permissionName==> " + permissionName);
        if (permissionName != null && permissionName.contains(",")) {
            permissions = permissionName.split(",");
        }
        else {
            permissions = new String[] {permissionName};
        }
        System.out.println("permissions==> " + permissions.toString());
        List<Permission> rolePermission = new ArrayList<>();
        for (int i = 0; i <permissions.length; i++) {
            rolePermission.add(dataBaseService.findPermissionByName(permissions[i]));
        }
        System.out.println("rolePermission==> " + rolePermission);
        role.setPermissions(rolePermission);
        Timestamp time = getDateTime();
        System.out.println("time==> " + time);
        role.setCreated_at(time);
        role.setUpdated_at(time);
        return dataBaseService.updateRole(role);
    }

    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public Role editRole(@RequestBody Role role, @RequestParam String name) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        Role oldRole = dataBaseService.findRoleByName(name);
        if (oldRole.getName() == null)
            return oldRole;
        else {
            Timestamp time = getDateTime();
            System.out.println("time==> " + time);
            role.setUpdated_at(time);
            if (role.getName() == null || role.getName() == "")
                role.setName(oldRole.getName());
            if (role.getDisplay_name() == null || role.getDisplay_name() == "")
                role.setDisplay_name(oldRole.getDisplay_name());
            role.setId(oldRole.getId());
            role.setCreated_at(oldRole.getCreated_at());
            role.setPermissions(oldRole.getPermissions());
            return dataBaseService.updateRole(role);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.DELETE)
    public String deleteRole(@RequestParam String roleName) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        dataBaseService.deleteRole(roleName);
        return "done";
    }

    @RequestMapping(value = "/role/permission", method = RequestMethod.PUT)
    public Role addRolePermission(@RequestParam String roleName, String permission) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        Role role = null;
        role = dataBaseService.findRoleByName(roleName);
        if (role.getName() == null)
            return role;
        else {
            List<Permission> oldPermissions = role.getPermissions();
            String [] permissions = null;
            System.out.println("permission==> " + permission);
            if (permission != null && permission.contains(",")) {
                permissions = permission.split(",");
            }
            else {
                permissions = new String[] {permission};
            }
            System.out.println("permissions==> " + permissions.toString());
            for (int i = 0; i <permissions.length; i++) {
                Permission newPermission = dataBaseService.findPermissionByName(permissions[i]);
                if (!oldPermissions.contains(newPermission))
                    oldPermissions.add(newPermission);
            }
            role.setPermissions(oldPermissions);
            Timestamp time = getDateTime();
            role.setUpdated_at(time);
            return dataBaseService.updateRole(role);
        }
    }

    @RequestMapping(value = "/role/permission", method = RequestMethod.DELETE)
    public Role deleteRolePermission(@RequestParam String roleName, String permission) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        Role role = null;
        role = dataBaseService.findRoleByName(roleName);
        if (role.getName() == null)
            return role;
        else {
            List<Permission> oldPermissions = role.getPermissions();
            String [] permissions = null;
            System.out.println("permission==> " + permission);
            if (permission != null && permission.contains(",")) {
                permissions = permission.split(",");
            }
            else {
                permissions = new String[] {permission};
            }
            System.out.println("permissions==> " + permissions.toString());
            for (int i = 0; i <permissions.length; i++) {
                Permission newPermission = dataBaseService.findPermissionByName(permissions[i]);
                if (oldPermissions.contains(newPermission))
                    oldPermissions.remove(newPermission);
            }
            role.setPermissions(oldPermissions);
            Timestamp time = getDateTime();
            role.setUpdated_at(time);
            return dataBaseService.updateRole(role);
        }
    }
}
