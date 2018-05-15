package com.example.springgradle.dao;

import com.example.springgradle.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    User findUserByUsername (String username);
    void deleteByUsername(String username);
    @Override
    List<User> findAll();
    @Override
    <S extends User> S save(S entity);
}