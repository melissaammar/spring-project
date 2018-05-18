package com.example.springgradle.controllers;

import com.example.springgradle.dto.request.role.RoleCreateRequest;
import com.example.springgradle.dto.request.role.RoleEditRequest;
import com.example.springgradle.dto.response.role.GetRoleResponse;
import com.example.springgradle.dto.response.role.RoleCreateResponse;
import com.example.springgradle.dto.response.role.RoleEditResponse;
import com.example.springgradle.dto.response.role.RoleResponse;
import com.example.springgradle.models.Role;
import com.example.springgradle.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public GetRoleResponse getRole(@RequestParam int id) {
        //This API returns a specific role by its ID
        try {
            Role role = roleService.getRoleById(id);
            if (role == null)
                return new GetRoleResponse("-1", "Unable to find role.");//role not found, returning error
            return new GetRoleResponse("1", "Successful Operation.", role);//Role found
        }
        catch (Exception e) {
            return new GetRoleResponse("-1", "Error: " + e);
        }
    }
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public RoleCreateResponse createRole(@RequestBody RoleCreateRequest roleCreateRequest, @RequestParam String permissionId) {
        /* This is API creates a new role and assigns the permissions mentioned in "permissionId"
         * which can be one or several permissions comma separated
        */
        if (roleCreateRequest == null || permissionId == null)
            return new RoleCreateResponse("-1","Please check the structure of your request.");
        try {
            RoleResponse role = roleService.createRole(roleCreateRequest, permissionId);//creating the role's permissions and saving it in DB
            if (role == null)
                return new RoleCreateResponse("-1", "Could not create the role.");//an error occurred while creating the role
            return new RoleCreateResponse("1", "Role successfully created.", role);//role successfully created
        }
        catch (Exception e) {
            return new RoleCreateResponse("-1","Error: " + e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public RoleEditResponse editRole(@RequestBody RoleEditRequest roleEditRequest) {
        //API for editing a specific role
        if (roleEditRequest == null)
            return new RoleEditResponse("-1","Please check the structure of your request.");//Missing payload
        try {
            RoleResponse role = roleService.editRole(roleEditRequest);//getting the role from DB and editing it
            if (role == null)
                return new RoleEditResponse("-1", "Could not edit role.");
            return new RoleEditResponse("1", "Role successfully edited.", role);
        }
        catch (Exception e)
        {
            return new RoleEditResponse("-1", "Error: " + e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.DELETE)
    public RoleEditResponse deleteRole(@RequestParam int id) {
        //API for deleting roles
        try {
            String response = roleService.deleteRole(id);//calling the delete method in the role service
            if (response == null)
                return new RoleEditResponse("-1", "Could not delete role.");
            if (response.equalsIgnoreCase("success"))
                return new RoleEditResponse("1", "Role successfully deleted.");
            return new RoleEditResponse("-1", "" + response);
        }
        catch (Exception e)
        {
            return new RoleEditResponse("-1", "Error: " + e);
        }
    }

    @RequestMapping(value = "/role/permission", method = RequestMethod.PUT)
    public RoleEditResponse addRolePermission(@RequestParam int roleId, String permissionIds) {
        //adding new permissions to the roles
        if (roleId <= 0 || permissionIds == null)
            return new RoleEditResponse("-1","Please check the structure of your request.");
        try {
            RoleResponse role = roleService.editRolePermission(roleId, permissionIds, "addPermission");//editing role permissions and adding new permissions to the roles
            if (role == null)
                return new RoleEditResponse("-1", "Could not update permissions.");
            return new RoleEditResponse("1", "Permissions successfully updated.", role);
        }
        catch (Exception e) {
            return new RoleEditResponse("-1", "Error: " + e);
        }
    }

    @RequestMapping(value = "/role/permission", method = RequestMethod.DELETE)
    public RoleEditResponse deleteRolePermission(@RequestParam int roleId, String permissionIds) {
        //deleting existing permissions from the role
        if (roleId <= 0 || permissionIds == null)
            return new RoleEditResponse("-1", "Please check the structure of your request.");
        try {
            RoleResponse role = roleService.editRolePermission(roleId, permissionIds, "deletePermission");//editing role permissions and deleting mentioned permissions from the role
            if (role == null)
                return new RoleEditResponse("-1", "Could not delete permissions.");
            return new RoleEditResponse("1", "Permissions successfully deleted.", role);
        }
        catch (Exception e) {
            return new RoleEditResponse("-1", "Error: " + e);
        }
    }
}
