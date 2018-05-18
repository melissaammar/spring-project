package com.example.springgradle.dao;

import com.example.springgradle.models.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Permission findPermissionById (int id);
}