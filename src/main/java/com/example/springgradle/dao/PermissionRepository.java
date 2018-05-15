package com.example.springgradle.dao;

import com.example.springgradle.models.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long>, PagingAndSortingRepository<Permission, Long> {
    Permission findPermissionByName (String name);
    @Override
    List<Permission> findAll();
}