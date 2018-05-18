package com.example.springgradle.dao;

import com.example.springgradle.models.Role;
import com.example.springgradle.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById (int id);
    List<User> findUserByRoles(Role role);
    @Override
    <S extends User> S save(S entity);
}