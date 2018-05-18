package com.example.springgradle.services;

import com.example.springgradle.dao.RoleRepository;
import com.example.springgradle.dto.request.role.RoleCreateRequest;
import com.example.springgradle.dto.request.role.RoleEditRequest;
import com.example.springgradle.dto.response.role.RoleResponse;
import com.example.springgradle.models.Permission;
import com.example.springgradle.models.Role;
import com.example.springgradle.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*   Added by Melissa
 *   This class is used to handle all the operations related to the role
 */

@Service
public class RoleService {

    private static final RoleService instance = new RoleService();
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    private RoleService() { }

    public static RoleService getInstance() {
        return instance;
    }

    public Role getRoleById (int id) {
        //Getting the role from the DB
        Role role = roleRepository.findRoleById(id);
        return role;
    }

    public List<Role> getRoles (String [] roleId) {
        //Getting the roles from the DB
        List<Role> userRole = new ArrayList<>();
        for (String id : roleId)//looping over the role ids provided and retrieving them from the DB
            userRole.add(getRoleById(Integer.parseInt(id)));
        return userRole;
    }

    public RoleResponse createRole (RoleCreateRequest roleCreateRequest, String permissionId) {
        String [] permissionIds = permissionId.contains(",") ? permissionId.split(",") : new String[] {permissionId};//checking if we have one or several roles
        List<Permission> rolePermissions = permissionService.getPermissions(permissionIds);//getting permissions from DB
        Role role = new Role(roleCreateRequest);
        role.setPermissions(rolePermissions);//creating the role and adding its permissions
        return new RoleResponse(roleRepository.save(role));//returning the Role response to the controller
    }

    public RoleResponse editRole (RoleEditRequest roleEditRequest) {
        Role newRole = new Role (roleEditRequest);
        Role currentRole = roleRepository.findRoleById(newRole.getId());//find role from DB by id
        if (currentRole == null)//this means that role does not exist==> should return error
            return null;
        //checking if any of the newRole fields is missing and replacing it with the current value
        if (newRole.getName() == null || newRole.getName().equals(""))
            newRole.setName(currentRole.getName());
        if (newRole.getDisplay_name() == null || newRole.getDisplay_name().equals(""))
            newRole.setDisplay_name(currentRole.getDisplay_name());
        newRole.setPermissions(currentRole.getPermissions());//setting the role for the edited user because we can not change the role in this API
        return new RoleResponse(roleRepository.save(newRole));//finally updating the role in the DB and returning it to the controller
    }

    public String deleteRole (int id) {
        Role role = getRoleById(id);//retrieving the role from the DB
        if (role == null)//this means that role does not exist==> should return error
            return null;
        //checking if the role has any users to see whether it can be deleted or not
        List<User> users = userService.getUserByRole(role);
        if (users != null && users.size() > 0)
            return "This role is linked to users. It can not be deleted";
        role.setPermissions(new ArrayList<>());//role is not linked to users so resetting its permissions
        role = roleRepository.save(role);//saving the role with no permissions to delete its permissions in the DB
        if (role == null)//this means that an error occurred==> should return error
            return null;
        roleRepository.delete(role);//deleting the role from the DB
        return "success";
    }

    public RoleResponse editRolePermission (int id, String permissionIds, String action) {
        Role role = roleRepository.findRoleById(id);//getting role from DB to get the roles
        if (role == null)
            return null;
        String [] permissions = permissionIds.contains(",") ? permissionIds.split(",") : new String[] {permissionIds};//checking if we have one or several permissions
        List<Permission> rolePermissions = role.getPermissions();
        List<Permission> newPermissions = permissionService.getPermissions(permissions);//getting new roles from the DB
        if (newPermissions == null)
            return null;//no new permissions retrieved from DB
        for (Permission permission: newPermissions)//looping over the retrieved permissions and comparing them with the current ones
            //if we're adding a permission if it does not exist in the current ones we'll add it
            if (action.equalsIgnoreCase("addPermission")) {
                if (!rolePermissions.contains(permission))
                    rolePermissions.add(permission);
            }//if we're deleting a permission if it does exist in the current ones we'll delete it
            else {
                if (rolePermissions.contains(permission))
                    rolePermissions.remove(permission);
            }
        role.setPermissions(rolePermissions);//adding the retrieved new permissions
        return new RoleResponse(roleRepository.save(role));//updating the role with its new permissions in the DB
    }
}
