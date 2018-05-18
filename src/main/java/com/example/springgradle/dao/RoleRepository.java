package com.example.springgradle.dao;

import com.example.springgradle.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleById (int id);
    @Override
    <S extends Role> S save(S entity);
}