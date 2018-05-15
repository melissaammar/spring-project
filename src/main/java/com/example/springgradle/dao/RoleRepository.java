package com.example.springgradle.dao;

import com.example.springgradle.models.Role;
import com.example.springgradle.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {
    Role findRoleByName (String name);
    @Override
    List<Role> findAll();
    @Override
    <S extends Role> S save(S entity);
}