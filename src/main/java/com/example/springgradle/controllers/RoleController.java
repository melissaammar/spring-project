package com.example.springgradle.controllers;

import com.example.springgradle.models.Permission;
import com.example.springgradle.models.Role;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    public String editRole(@RequestBody Role role) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return "done";
    }

    @RequestMapping(value = "/role", method = RequestMethod.DELETE)
    public String deleteRole(@RequestBody Role role) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return "done";
    }

    @RequestMapping(value = "/role/permission", method = RequestMethod.PUT)
    public String addRolePermission(@RequestBody Role role) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return "done";
    }

    @RequestMapping(value = "/role/permission", method = RequestMethod.DELETE)
    public String deleteRolePermission(@RequestBody Role role) throws SQLException, ClassNotFoundException, ParseException, UnsupportedEncodingException {
        return "done";
    }
}
