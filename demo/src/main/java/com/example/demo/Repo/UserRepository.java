package com.example.demo.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findByUsername(String userName);
}

